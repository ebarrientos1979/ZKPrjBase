package pe.edu.nh.mvvm;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

import pe.edu.nh.model.UsuarioDTO;

public class LoginViewModel {
	private UsuarioDTO usuario = new UsuarioDTO();
	private String mensaje;
	
	public UsuarioDTO getUsuario() {return usuario;}
	public void setUsuario(UsuarioDTO usuario) { this.usuario = usuario;}
	public String getMensaje() {return mensaje;}
	public void setMensaje(String mensaje) {this.mensaje = mensaje;}
	
	@Wire
	private Window loginWin;
	
	@Command
	@NotifyChange({"mensaje"})
	public void login() {
		if(validarCredenciales(usuario.getUsername(), usuario.getPassword())) {
			Executions.sendRedirect("/principal.zul");
			Sessions.getCurrent().setAttribute("usuario", usuario.getUsername());
		}else {
			mensaje = "Usuario o password incorrectos";
		}
	}
	
	@Command
	public void resetForm() {
		usuario = new UsuarioDTO();
		mensaje = null;
		BindUtils.postNotifyChange(null, null, this, "usuario");
		BindUtils.postNotifyChange(null, null, this, "mensaje");
	}
	
	private boolean validarCredenciales(String username, String password) {
		//Aqui llamamos a nuestra API REST
		return "admin".equals(username) && "admin123".equals(password);
	}
	
}
