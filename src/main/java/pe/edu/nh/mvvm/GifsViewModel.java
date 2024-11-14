package pe.edu.nh.mvvm;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.Clients;

public class GifsViewModel {
	private String nombreImagen;
	
	@Init
	public void init() {
		
	}
	
	public void setNombreImagen(String imagen) {
		this.nombreImagen = imagen;
	}
	
	public String getNombreImagen() {
		return this.nombreImagen;
	}

	@Command
	@NotifyChange("nombreImagen")
	public void buscarImagen() {
		System.out.println(this.nombreImagen);
		String script = String.format("console.log('%s')", this.nombreImagen);
		Clients.evalJavaScript(script);
	}
}	
