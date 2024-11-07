package pe.edu.nh.mvvm;

import org.zkoss.bind.annotation.*;

public class MenuViewModel {
	private String fuente="inicio.zul";
	private String titulo="Bienvenido";
	
	public String getFuente() {
		return fuente;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	@Command
	@NotifyChange({"fuente", "titulo"})
	public void navigate(@BindingParam("page") String page, @BindingParam("titulo") String titulo) {
		this.fuente = page;
		this.titulo = titulo;
	}

}
