package es.vn.sb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import brave.Span;
import brave.Tracer;
import es.vn.sb.model.Tramit;
import es.vn.sb.service.TramitService;

@RestController
@RequestMapping("/tramit")
public class TramitController {
	
	
	private static final Logger log = LoggerFactory.getLogger(TramitController.class);

	@Autowired
	Tracer tracer;
	
	@Autowired
	TramitService tramitService;
	

	@RequestMapping(path = "/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<String> createTramit(@PathVariable("id") String id) {
		log.info("START createTramit():");
		Span span = tracer.currentSpan();
		span.tag("controller", "entrada al controller");
		return new ResponseEntity<String>(tramitService.createTramit(null), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/create", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
	public HttpEntity<String> create(@RequestBody Tramit tramit) {
		log.info("START create():");
		Span span = tracer.currentSpan();
		span.tag("controller", "entrada al controller");
		
		return new ResponseEntity<String>(tramitService.createTramit(tramit), HttpStatus.CREATED);
	}
}
