package es.vn.sb.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.vn.sb.utils.Utils;

@Controller
public class HelloWebController {

	private static final Logger logger = LoggerFactory.getLogger(HelloWebController.class);
	
	@Value("${spring.application.name}")
	private String appName;

	@Value("${spring.application.version}")
	private String appVersion;
	
	@Value("#{systemEnvironment['VERSION']}")
	String serviceVersion;
	
	@RequestMapping(path = "/", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String hello(
			@RequestHeader(value = "sprint", required = false, defaultValue = "0") String sprint, Model model) {
		logger.info("START hello():");
		model.addAttribute("message", String.format("(%tc) HELLO from '%s' in sprint: '%s', pom version: '%s', service version: %s and pod: '%s'", new Date(), appName, sprint,
				appVersion, serviceVersion, Utils.getPodName()));
		return "index";
	}

}
