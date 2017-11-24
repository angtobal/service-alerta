package pe.org.alerta.sismo.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.org.alerta.sismo.persist.Sismo;
import pe.org.alerta.sismo.service.SismoService;

@RestController
public class SismoController {
	private static Logger log = LoggerFactory.getLogger(SismoController.class);
	
	@Autowired
	private SismoService registroService;
	
	@RequestMapping(value = "/registro")
	@ResponseBody
	public Map<String, Object> registro(Sismo sismo) throws Exception {
		return registroService.registraSismo(sismo);
	}
	
	@RequestMapping(value = "/lista")
	@ResponseBody
	public Map<String, Object> lista(Sismo sismo) throws Exception {
		return registroService.listaSismo(sismo);
	}
	
	@RequestMapping(value = "/notificar")
	@ResponseBody
	public Map<String, Object> notificarSismo(Sismo sismo) throws Exception {
		return registroService.notificarSismo(sismo);
	}
	
	
	@RequestMapping(value = "/disponible")
	@ResponseBody
	public Map<String, Object> sismoDiponible(Sismo sismo) throws Exception {
		return registroService.sismoDiponible(sismo);
	}
	
}
