package es.vn.sb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import brave.Span;
import brave.Tracer;
import es.vn.sb.model.Tramit;

@Service
public class TramitServiceImpl implements TramitService {

	@Autowired
    private RestTemplate myRestTemplate;
    
    @Value("${service-k1.tramit.topic.create.url}")
    String tramitTopicCreateUrl;

    @Autowired
	Tracer tracer;
    
	@Override
	public String createTramit(Tramit tramit) {
		Span span = tracer.currentSpan();
		span.tag("service", "entrada al servicio");
		span.annotate(String.format("Llamada al servicio con url %s", tramitTopicCreateUrl));
		return myRestTemplate.postForEntity(tramitTopicCreateUrl, tramit, String.class).getBody();
	}	

}
