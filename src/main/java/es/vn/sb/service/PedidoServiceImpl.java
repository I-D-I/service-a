package es.vn.sb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import brave.Span;
import brave.Tracer;
import es.vn.sb.model.Pedido;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private RestTemplate myRestTemplate;
    
    @Value("${service-b.pedido.url}")
    String serviceBPedidoURL;

    @Value("${service-k1.pedido.url}")
    String serviceK1PedidoURL;
    
    @Autowired
	Tracer tracer;

	public String createPedido(Pedido pedido) throws Exception {
		Span span = tracer.currentSpan();
		span.tag("service", "entrada al servicio");
		span.annotate(String.format("Llamada al servicio con url %s", serviceBPedidoURL));
		return myRestTemplate.postForEntity(serviceBPedidoURL, pedido, String.class).getBody();
	}
    
	@Override
	public String createTopic(Pedido pedido) {
		Span span = tracer.currentSpan();
		span.tag("service", "entrada al servicio");
		span.annotate(String.format("Llamada al servicio con url %s", serviceK1PedidoURL));
		return myRestTemplate.postForEntity(serviceK1PedidoURL, pedido, String.class).getBody();
	}
}   
