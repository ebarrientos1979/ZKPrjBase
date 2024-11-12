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
		campos.add(new FormularioDTO("Nombre", "text", "PRUEBA"));
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
	
		
	public boolean isText(String tipo){
		//System.out.println("texto - " + campo.getLabel() + " - " + campo.getType() + "text".equals(campo.getType()));
		return "text".equals(tipo);
	}
	
	public boolean isNumber(String tipo){
		System.out.println(tipo);
		//System.out.println("number - " + campo.getLabel() + " - " + campo.getType() + "number".equals(campo.getType()));
		return "number".equals(tipo);
	}
	
	public boolean isDate(String tipo){
		//System.out.println("date - " + campo.getLabel() + " - " + campo.getType() + "date".equals(campo.getType()));
		return "date".equals(tipo);
	}
	
	public boolean isBoolean(String tipo){
		//System.out.println("boolean - " + campo.getLabel() + " - " + campo.getType() + "boolean".equals(campo.getType()));
		return "boolean".equals(tipo);
	}

}
