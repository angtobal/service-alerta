package pe.org.alerta.sismo.service;

import java.util.Map;

public abstract class EventoTemplate<T> {

	public Map<String, Object> notificarEvento(T evento) throws Exception {
		verificarEvento(evento);
		registrarEvento(evento);
		alertarEvento(evento);
		return finalizarEvento(evento);
	}

	public abstract void verificarEvento(T sismo) throws Exception;

	public abstract void registrarEvento(T sismo) throws Exception;

	public abstract void alertarEvento(T evento) throws Exception;
	
	public abstract Map<String, Object> finalizarEvento(T evento) throws Exception;
}
