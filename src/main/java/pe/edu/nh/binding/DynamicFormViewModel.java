package pe.edu.nh.binding;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.Messagebox;

import pe.edu.nh.model.*;

public class DynamicFormViewModel {
	private List<FormularioDTO> campos;
	
	@Init
	public void init() {
		campos = new ArrayList<>();
		campos.add(new FormularioDTO("Nombre", "text", "EDWIN"));
		campos.add(new FormularioDTO("Ap. Paterno", "text", "BARRIENTOS"));
		campos.add(new FormularioDTO("Ap. Materno", "text", "RETUERTO"));
		campos.add(new FormularioDTO("Edad", "number", Integer.valueOf(100)));
		campos.add(new FormularioDTO("Fecha de Nacimiento", "date", new Date()));
		campos.add(new FormularioDTO("Es correcto?", "boolean", Boolean.valueOf(true)));
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
