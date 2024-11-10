package pe.edu.nh.binding;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zul.Messagebox;

import pe.edu.nh.model.*;

public class DynamicFormViewModel {
	private List<FormularioDTO> campos;
	
	public DynamicFormViewModel() {
		campos = new ArrayList<FormularioDTO>();
		campos.add(new FormularioDTO("Nombre", "text"));
		campos.add(new FormularioDTO("Edad", "number"));
		campos.add(new FormularioDTO("Fecha de Nacimiento", "date"));
		campos.add(new FormularioDTO("Es correcto?", "boolean"));
	}
	
	public List<FormularioDTO> getCampos(){
		return campos;
	}
	
	public void submitForm() {
		StringBuilder data = new StringBuilder("Datos del Formulario\n");
		for(FormularioDTO campo: campos) {
			data.append( campo.getLabel()).append(campo.getValue()).append("\n");
		}
	
		Messagebox.show(data.toString(), "Formulario Enviado", Messagebox.OK, Messagebox.INFORMATION);
	}

}
