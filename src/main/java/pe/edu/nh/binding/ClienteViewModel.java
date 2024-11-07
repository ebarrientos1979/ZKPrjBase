package pe.edu.nh.binding;

import pe.edu.nh.ModelMapperConfig;
import pe.edu.nh.dto.ClienteDTO;
import pe.edu.nh.rest.RestCliente;

import java.util.*;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Window;

public class ClienteViewModel {	
	private ClienteDTO miSeleccionado;
	private ClienteDTO selected;
	private Boolean modalAbierto = Boolean.FALSE;
	private List<ClienteDTO> listaClientes;

	
	@Init
	public void init(@ExecutionArgParam("cliente") ClienteDTO cliente) {	
		
		this.selected = cliente;
		RestCliente rc = new RestCliente();
		try {
			listaClientes = rc.ListarClientes();			
		}catch(Exception e) {
			
		}
	}
	
	
	
	public ClienteDTO getClienteSeleccionado() {
		if (Objects.nonNull(selected) && Boolean.FALSE.equals(modalAbierto)) {
			miSeleccionado = new ClienteDTO();
			
			ModelMapperConfig.copyProperties(selected, miSeleccionado);
		}
		
		modalAbierto = Boolean.TRUE;
		return miSeleccionado;
	} 
	
		
	public List<ClienteDTO> getClienteList(){
		return listaClientes;
	}
	
	public ClienteDTO getSelectedCliente() {
		return this.selected;
	}
	
	@GlobalCommand
	@NotifyChange("clienteList")
	public void refrescarListaClientes(@BindingParam("cliente") ClienteDTO cliente) {
		ModelMapperConfig.copyProperties(cliente, this.selected);
	}
	
	@Command
	public void grabar() {
		Map<String, Object> parametro = new HashMap<>();		
		parametro.put("cliente", miSeleccionado);
		BindUtils.postGlobalCommand(null, null, "refrescarListaClientes", parametro);
		this.cerrarModal();
	}
	
	@Command
	public void cerrarModal() {
		Component window = Executions.getCurrent().
					getDesktop().getFirstPage().getFellow("modalDialog");
		
		window.detach();
	}
	
	@Command	
	public void seleccionarCliente( @BindingParam("cliente") ClienteDTO cliente ) {
		
		this.selected = cliente;
		
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("cliente", cliente);
		
		
		Window window = (Window) Executions.
							createComponents("/cliente_modal.zul", null, parametros);		
		
		window.doModal();
	}

}
