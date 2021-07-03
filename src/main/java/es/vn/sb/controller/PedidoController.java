package es.vn.sb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import brave.Span;
import brave.Tracer;
import es.vn.sb.model.CustomError;
import es.vn.sb.model.Pedido;
import es.vn.sb.service.PedidoService;

@RestController
@RequestMapping("/api")
public class PedidoController {

	private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);

	@Autowired
	PedidoService pedidoService;

	@Value("${spring.application.name}")
	private String appName;

	@Autowired
	Tracer tracer;

	@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.OPTIONS })
	@RequestMapping(path = "/pedido", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<Object> createPedido(@RequestBody Pedido pedido) {

		Span span = tracer.currentSpan();
		span.tag("controller", "entrada al controller");
		logger.info("pedido recibido: " + pedido.toString());
		
		try {
			span.annotate(String.format("Petición normal hacia servicio-b en pedido.id %s", pedido.getId()));
			StringBuffer result = new StringBuffer();
			String id = pedido.getId();
			if (pedido.getIteraciones() > 1) {
				for (int i = 0; i < pedido.getIteraciones(); i++) {
					pedido.setId(id.concat("-").concat(String.valueOf(i)));
					logger.info(String.format("peticion_iniciada: %s", pedido.toString()));
					result = result.append(pedidoService.createPedido(pedido));
					result.append("\n").append(pedidoService.createTopic(pedido));
				}
				return new ResponseEntity<Object>(String.format("OK - %s\n%s", appName, result.toString()), HttpStatus.OK);
			}
			
			logger.info(String.format("peticion_iniciada: %s", pedido.toString()));
			result = result.append(pedidoService.createPedido(pedido));
			result.append("\n").append(pedidoService.createTopic(pedido));
			return new ResponseEntity<Object>(String.format("OK - %s\n%s", appName, result.toString()), HttpStatus.OK);

		} catch (HttpClientErrorException e) {
			CustomError customError = this.writeCustomError(e, e.getStatusText(), pedido);
			span.annotate(String.format("Petición con error desde servicio-b en pedido.id %s", pedido.getId()));
			logger.error(String.format("Exception: %s", e.getLocalizedMessage()));
			return new ResponseEntity<Object>(customError, e.getStatusCode());
		} catch (Exception e) {
			CustomError customError = this.writeCustomError(e, HttpStatus.INTERNAL_SERVER_ERROR.toString(), pedido);
			span.annotate(String.format("Petición con error desde servicio-b en pedido.id %s", pedido.getId()));
			logger.error(String.format("Exception: %s", e.getLocalizedMessage()));
			return new ResponseEntity<Object>(customError,	HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private CustomError writeCustomError(Exception e, String status, Pedido pedido) {
		CustomError customError = new CustomError();
		customError.setHttpStatusCode(status);
		customError.setCustomErrorCoder("COD-00101");
		customError.setDescripcion(String.format("No se ha podido procesar el pedido %s", pedido.getId()));
		return customError;
		 
	}

}
