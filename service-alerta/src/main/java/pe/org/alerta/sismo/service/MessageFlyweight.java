package pe.org.alerta.sismo.service;

import java.util.HashMap;

import pe.org.alerta.sismo.bean.MensajeBean;

public class MessageFlyweight {
	private static final HashMap<String, MensajeBean> mensajeMap = new HashMap<String, MensajeBean>();

	   public static MensajeBean getMensaje(String tipoMensaje) {
		   MensajeBean mensaje = (MensajeBean)mensajeMap.get(tipoMensaje);

	      if(mensaje == null) {
	    	  if(tipoMensaje.equals("alerta-baja"))
	    		  mensaje = new MensajeBean("Por favor indicar si puede movilizarse.");
	    	  if(tipoMensaje.equals("alerta-media"))
	    		  mensaje = new MensajeBean("Por favor indicar si requiere asistencia.");
	    	  if(tipoMensaje.equals("alerta-alta"))
	    		  mensaje = new MensajeBean("Por favor indicar si se encuentra a salvo.");
	         mensajeMap.put(tipoMensaje, mensaje);
	      }
	      return mensaje;
	   }
}
