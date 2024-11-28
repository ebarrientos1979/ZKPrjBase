package pe.edu.nh.dto;

public class FormularioDTO {
	private String label;
	private String tipo;
	private Object valor;
	
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}


	
	public FormularioDTO(String label, String tipo){//, Object value) {
		this.label = label;
		this.tipo = tipo;
		//this.value = value;
	}
	
	public FormularioDTO(String label, String tipo, Object valor) {
		this.label = label;
		this.tipo = tipo;
		this.valor = valor;
	}
	
	

}


