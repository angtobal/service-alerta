package pe.org.alerta.sismo.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
public class SismoService extends EventoTemplate<Sismo>{

    @Autowired 
    SismoRepository repository;
    
    @Autowired 
    MongoTemplate mongoTemplate;
    
    private Sismo sismo;
    
  //Metodos del Template
  	@Override
  	public void verificarEvento(Sismo sismo) throws Exception{
  		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
  		String fechaEvento = df.format(sismo.getFecha());
  		String fechaActual = df.format(Calendar.getInstance().getTime());
  		if(!fechaEvento.equals(fechaActual))
  			throw new Exception("Evento invalido");
  	}

  	@Override
  	public void registrarEvento(Sismo sismo) throws Exception{
  		mongoTemplate.save(sismo);
  	}

  	@Override
  	public void alertarEvento(Sismo evento) throws Exception {
  		Map<String,Object> result = new HashMap<String,Object>();
  		UsuarioBean[] usuarios = obtenerListaUsuarioCercanoAlSismo(this.sismo);
  		for(UsuarioBean usuario : usuarios){
  			System.out.println("Alertando: " + usuario.getCelular());
  		}
  		result.put("msg", new MensajeBean("Alertando en segundo plano."));
  	}

  	@Override
  	public Map<String, Object> finalizarEvento(Sismo evento) throws Exception {
  		Map<String,Object> result = new HashMap<String,Object>();
  		result.put("msg", new MensajeBean("Evento con magnitud: " + evento.getMagnitud()));
  		return result;
  	}
    
//	public Map<String, Object>  registraSismo(Sismo sismo) throws Exception {
//		Map<String,Object> result = new HashMap<String,Object>();
//		
//		this.notificarEvento(sismo);
//
//		result.put("msg", new MensajeBean("Se registro corretamente."));
//	    
//		return result;
//	}
	
  	//Metodos del Servicio
	public Map<String, Object>  listaSismo(Sismo sismo) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		Iterable<Sismo> lista =repository.findAll();
		result.put("data", lista);
		
		return result;
	}
	
	//metodo implementado con socket que envia mensaje de alerta
//	public Map<String, Object> notificarSismo(Sismo sismo) throws Exception {
//		Map<String,Object> result = new HashMap<String,Object>();
//		
//		this.sismo = sismo;
//		UsuarioBean[] usuarios = obtenerListaUsuarioCercanoAlSismo(this.sismo);
//		
//		result.put("msg", new MensajeBean("Notificando en segundo plano."));
//		
//		return result;
//	}
	
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
