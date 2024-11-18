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

import pe.edu.nh.model.Mostrador;
import pe.edu.nh.model.gif.Datum;
import pe.edu.nh.rest.RestCliente;

public class GifsViewModel {
	private String nombreImagen = "";
	private ArrayList<Mostrador> data = new ArrayList<Mostrador>();
	private RestCliente restCliente = new RestCliente();
	
	@Init
	public void init() {
		if (nombreImagen.isBlank() || nombreImagen.isEmpty()) {
			this.buscarImagen();
		}
	}
	
	public void setNombreImagen(String imagen) {
		this.nombreImagen = imagen;
	}
	
	public String getNombreImagen() {
		return this.nombreImagen;
	}
	
	public ArrayList<Mostrador> getData(){
		return data;
	}
	
	public void setData(ArrayList<Mostrador> data) {
		this.data = data;
	}

	@Command
	@NotifyChange("data")
	public void buscarImagen() {
		try {
			this.data = new ArrayList<Mostrador>();
			int contador = 0;
			Mostrador m = new Mostrador();
			ArrayList<Datum> lista = restCliente.getImages(nombreImagen).data;
			
			//Recorremos la lista para agrupar en numeros de 3 imagenes
			for(Datum d : lista) {
				if(contador == 0)
					m.setImagen1(d);
				if(contador == 1)
					m.setImagen2(d);
				if(contador == 2)
					m.setImagen3(d);
				
				//En caso se llegue a las tres imagenes
				//se recarga la lista y se reinicia el contador.
				contador++;
				if(contador == 3) {
					this.data.add(m);
					m = new Mostrador();
					contador = 0;
				}
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}	
