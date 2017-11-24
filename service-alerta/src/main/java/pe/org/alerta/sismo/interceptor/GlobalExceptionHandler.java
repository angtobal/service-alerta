package pe.org.alerta.sismo.interceptor;
import com.google.common.base.Throwables;
import com.mongodb.MongoSocketOpenException;
import com.mongodb.MongoTimeoutException;

import pe.org.alerta.sismo.bean.MensajeBean;
import pe.org.alerta.sismo.util.Constante;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler
{
  protected final Log log = LogFactory.getLog(getClass());
  
  @ExceptionHandler({SQLException.class})
  @ResponseBody
  public Map<String, Object> sqlExceptionHandler(Exception ex) {
	   ex.printStackTrace();
	   Map<String, Object> result = new HashMap<String, Object>();
	   if (Constante.CLOSE_CONNECTION_MESSAGE.equals(Throwables.getRootCause(ex).getMessage()))
		   result.put("msg", new MensajeBean(Constante.CLOSE_CONNECTION_MESSAGE_NEW, Constante.MSG_ERROR));
	   else
		   result.put("msg", new MensajeBean(Throwables.getRootCause(ex).getMessage(), Constante.MSG_ERROR));
	   
	   return result;
  }
  
  @ExceptionHandler({NumberFormatException.class})
  @ResponseBody
  public Map<String, Object> numberFormatExceptionHandler(Exception ex) {
	   ex.printStackTrace();
	   Map<String, Object> result = new HashMap<String, Object>();
	   if (Throwables.getRootCause(ex).getMessage().indexOf(Constante.FORMAT_EXCEPTION_MESSAGE) >= 0)
		   result.put("msg", new MensajeBean(Constante.FORMAT_EXCEPTION_MESSAGE_NEW, Constante.MSG_ERROR));
	   else
		   result.put("msg", new MensajeBean(Throwables.getRootCause(ex).getMessage(), Constante.MSG_ERROR));
	   
	   return result;
  }
  @ExceptionHandler({MongoSocketOpenException.class})
  @ResponseBody
  public Map<String, Object> mongoSocketExceptionHandler(Exception ex) {
	   ex.printStackTrace();
	   Map<String, Object> result = new HashMap<String, Object>();
	   if (Throwables.getRootCause(ex).getMessage().indexOf(Constante.MONGO_SOCKET_EXCEPTION_MESSAGE) >= 0)
		   result.put("msg", new MensajeBean(Constante.MONGO_SOCKET_EXCEPTION_MESSAGE_NEW, Constante.MSG_ERROR));
	   else
		   result.put("msg", new MensajeBean(Throwables.getRootCause(ex).getMessage(), Constante.MSG_ERROR));
	   
	   return result;
  }
  @ExceptionHandler({MongoTimeoutException.class})
  @ResponseBody
  public Map<String, Object> mongoTimeoutExceptionHandler(Exception ex) {
	   ex.printStackTrace();
	   Map<String, Object> result = new HashMap<String, Object>();
	   if (Throwables.getRootCause(ex).getMessage().indexOf(Constante.MONGO_TIMEOUT_EXCEPTION_MESSAGE) >= 0)
		   result.put("msg", new MensajeBean(Constante.MONGO_TIMEOUT_EXCEPTION_MESSAGE_NEW, Constante.MSG_ERROR));
	   else
		   result.put("msg", new MensajeBean(Throwables.getRootCause(ex).getMessage(), Constante.MSG_ERROR));
	   
	   return result;
  }
  
  
  
  /*
  @ExceptionHandler({HttpSessionRequiredException.class})
  public String sessionExceptionHandler(Exception ex) {
	   ex.printStackTrace();
	   return "redirect:/main.sgp";
  }*/
  /*
  @ExceptionHandler({Exception.class})
  @ResponseBody
  public Map<String, Object> iOExceptionHandler(Exception ex) { 
	 
	 ex.printStackTrace();
	 
    Map<String, Object> result = new HashMap<String, Object>();
    if (Constante.CLOSE_CONNECTION_MESSAGE.equals(Throwables.getRootCause(ex).getMessage()))
   	 result.put("msg", new MensajeBean(Constante.CLOSE_CONNECTION_MESSAGE_NEW, Constante.MSG_ERROR));
    else
   	 result.put("msg", new MensajeBean(Throwables.getRootCause(ex).getMessage(), Constante.MSG_ERROR));
    return result;
  }*/
}
