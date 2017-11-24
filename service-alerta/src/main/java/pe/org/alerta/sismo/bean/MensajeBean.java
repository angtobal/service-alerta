package pe.org.alerta.sismo.bean;

public class MensajeBean {

	private int tipo;
	private String text;
	private int codigo;
	
	public MensajeBean() {		
	     this.text   = "";
	     this.tipo   = 0;
	     this.codigo = 0;
	}
	
	public MensajeBean(String mensaje, int tipo, int codigo) {
		this.text = mensaje;
		this.tipo = tipo;
		this.codigo = codigo;
	}
	   
	public MensajeBean(String mensaje, int tipo) { 
		this.text = mensaje;
		this.tipo = tipo;
	}
	   
	public MensajeBean(String text) { this.text = text;
		this.tipo = 0;
		this.codigo = 0;
	}
	   
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
}
