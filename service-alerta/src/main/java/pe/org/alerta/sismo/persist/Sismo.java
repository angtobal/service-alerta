package pe.org.alerta.sismo.persist;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

public class Sismo {
	
	@Id
	private String id;
	
	private double magnitud;	
	
	@DateTimeFormat	(pattern="yyyy-MM-dd HH:mm:ss.SSS")
	private Date fecha;
	
	private String ciudadProxima;
	
	private Espacial espacial;
	
	public double getMagnitud() {
		return magnitud;
	}
	
	public void setMagnitud(double magnitud) {
		this.magnitud = magnitud;
	}
	public void setMagnitud(Long magnitud) {
		this.magnitud = magnitud;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getCiudadProxima() {
		return ciudadProxima;
	}
	public void setCiudadProxima(String ciudadProxima) {
		this.ciudadProxima = ciudadProxima;
	}
	public Espacial getEspacial() {
		return espacial;
	}
	public void setEspacial(Espacial espacial) {
		this.espacial = espacial;
	}
	
}
