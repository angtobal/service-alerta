package pe.org.alerta.sismo.util;

public interface Constante {
	public static int MSG_SUCCESS = 0;
	public static int MSG_ERROR = 1;
	public static String CLOSE_CONNECTION_MESSAGE = "Connection has already been closed";
	public static String CLOSE_CONNECTION_MESSAGE_NEW = "Tiempo de espera agotado.";
	
	public static String FORMAT_EXCEPTION_MESSAGE = "For input string";
	public static String FORMAT_EXCEPTION_MESSAGE_NEW = "El tipo de dato no es valido";
	
	public static String MONGO_SOCKET_EXCEPTION_MESSAGE = "Exception opening socket";
	public static String MONGO_SOCKET_EXCEPTION_MESSAGE_NEW = "El servicio no esta disponible";
	
	public static String MONGO_TIMEOUT_EXCEPTION_MESSAGE = "Timed out after";
	public static String MONGO_TIMEOUT_EXCEPTION_MESSAGE_NEW = "Tiempo de espera mayor a lo esperado";
	
}