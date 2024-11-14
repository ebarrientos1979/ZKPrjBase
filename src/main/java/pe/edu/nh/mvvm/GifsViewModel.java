package pe.edu.nh.mvvm;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.Clients;

import pe.edu.nh.model.gif.Datum;
import pe.edu.nh.rest.RestCliente;

public class GifsViewModel {
	private String nombreImagen;
	private ArrayList<Datum> data;
	private RestCliente restCliente = new RestCliente();
	
	@Init
	public void init() {
		
	}
	
	public void setNombreImagen(String imagen) {
		this.nombreImagen = imagen;
	}
	
	public String getNombreImagen() {
		return this.nombreImagen;
	}
	
	public ArrayList<Datum> getData(){
		return data;
	}
	
	public void setData(ArrayList<Datum> data) {
		this.data = data;
	}

	@Command
	@NotifyChange("data")
	public void buscarImagen() {
		System.out.println(this.nombreImagen);
		String script = String.format("console.log('%s')", this.nombreImagen);
		try {
			this.data = restCliente.getImages(nombreImagen).data;			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Clients.evalJavaScript(script);
		
	}
}	
