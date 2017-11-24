package pe.org.alerta.sismo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pe.org.alerta.sismo.bean.MensajeBean;
import pe.org.alerta.sismo.bean.UsuarioBean;
import pe.org.alerta.sismo.persist.Sismo;
import pe.org.alerta.sismo.persist.SismoRepository;

@Service
public class SismoService {

    @Autowired 
    SismoRepository repository;
    
    @Autowired 
    MongoTemplate mongoTemplate;
    
    private Sismo sismo;
    
	public Map<String, Object>  registraSismo(Sismo sismo) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		notificarSismo(sismo);
		mongoTemplate.save(sismo);
		
		/*
		class UnSismo implements Runnable {
	        private Sismo sismo;
	        UnSismo(Sismo sismo) { this.sismo = sismo; }
	        public void run() {
	        	sismo =null;
	        }
	    }
		
		Thread hilo = new Thread(new UnSismo(this.sismo));
	    hilo.sleep(10000);
	    hilo.start();
	    */
		result.put("msg", new MensajeBean("Se registro corretamente."));
	    
		return result;
	}
	
	public Map<String, Object>  listaSismo(Sismo sismo) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		Iterable<Sismo> lista =repository.findAll();
		result.put("data", lista);
		
		return result;
	}
	
	//metodo implementado con socket que envia mensaje de alerta
	public Map<String, Object> notificarSismo(Sismo sismo) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		
		this.sismo = sismo;
		UsuarioBean[] usuarios = obtenerListaUsuarioCercanoAlSismo(this.sismo);
		
		result.put("msg", new MensajeBean("Notificando en segundo plano."));
		
		return result;
	}
	
	public UsuarioBean[] obtenerListaUsuarioCercanoAlSismo(Sismo sismo) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		System.out.println(restTemplate.getForObject("http://localhost:9001/usuario/lista", UsuarioBean[].class));
		
		UsuarioBean[] usuario = restTemplate.getForObject("http://localhost:9001/usuario/lista", UsuarioBean[].class);
		
		for (int i = 0; i < usuario.length; i++) {
			UsuarioBean usuarioBean = usuario[i];
			System.out.println(usuarioBean.getNombre()+"- "+usuarioBean.getCelular());
		}
		return usuario;
	}
	public Map<String, Object>  sismoDiponible(Sismo sismo) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		
		result.put("data", this.sismo);
		
		return result;
	}
	
}
