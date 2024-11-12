package pe.edu.nh.model;

public class FormularioDTO {
	private String label;
	private String type;
	private Object value;
	
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}


	
	public FormularioDTO(String label, String type){//, Object value) {
		this.label = label;
		this.type = type;
		//this.value = value;
	}
	
	public FormularioDTO(String label, String type, Object value) {
		this.label = label;
		this.type = type;
		this.value = value;
	}
	
	

}
