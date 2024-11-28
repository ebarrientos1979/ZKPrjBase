package pe.edu.nh.binding;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.Messagebox;

import com.google.gson.Gson;

import pe.edu.nh.dto.ClienteDTO;
import pe.edu.nh.dto.FormularioDTO;
import pe.edu.nh.model.*;
import pe.edu.nh.rest.RestCliente;

public class DynamicFormViewModel {
	private List<FormularioDTO> campos;
	private RestCliente restCliente;
	private int idFormulario;
	
	@Init
	public void init() throws MalformedURLException, IOException {
		restCliente = new RestCliente();
		campos = restCliente.getFormulario(0);		
	}
	
	public void setIdFormulario(int idFormulario) {
		this.idFormulario = idFormulario;
	}
	
	public int getIdFormulario() {
		return this.idFormulario;
	}
	
	public List<FormularioDTO> getCampos(){
		return campos;
	}
	
	public void setCampos(List<FormularioDTO> campos) {
		this.campos	= campos;
	}
	
	@Command
	public void submitForm() {
		Gson gson = new Gson();
		String jsonInputString = gson.toJson(campos);
		
		System.out.println( jsonInputString );
		/*StringBuilder data = new StringBuilder("Datos del Formulario\n");
		for(FormularioDTO campo: campos) {
			data.append( campo.getLabel()).append(campo.getValor()).append("\n");
		}*/
	}
	
	@Command
	@NotifyChange("campos")
	public void selFormulario(@BindingParam("idFormulario") int idFormulario) throws MalformedURLException, IOException {
		restCliente = new RestCliente();
		campos = restCliente.getFormulario(idFormulario);
	}
	
	
	
	

}
