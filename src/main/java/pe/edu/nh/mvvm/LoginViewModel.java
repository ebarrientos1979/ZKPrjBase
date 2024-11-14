package pe.edu.nh.mvvm;

import java.util.Locale;

import org.zkoss.web.Attributes;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.Locales;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;
import org.zkoss.zk.ui.util.Clients;

import pe.edu.nh.model.UsuarioDTO;

public class LoginViewModel {
	private UsuarioDTO usuario = new UsuarioDTO();
	private String mensaje;
	private String selectedLanguage;
	
	public UsuarioDTO getUsuario() {return usuario;}
	public void setUsuario(UsuarioDTO usuario) { this.usuario = usuario;}
	public String getMensaje() {return mensaje;}
	public void setMensaje(String mensaje) {this.mensaje = mensaje;}
	
	@Init
	public void init() {
		String script = String.format("localStorage.setItem('%s','%s');", "PRUEBA", "DATOS");
		Clients.evalJavaScript(script);
		selectedLanguage = (String) Sessions.getCurrent().getAttribute("preferred_language");
		if(selectedLanguage == null) {
			selectedLanguage = "es";
			changeLocale();
		}
	}
	
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
	
	public void setSelectedLanguage(String sl) {
		this.selectedLanguage = sl;
	}
	
	public String getSelectedLanguage() {
		return this.selectedLanguage;
	}
	
	private boolean validarCredenciales(String username, String password) {
		//Aqui llamamos a nuestra API REST
		return "admin".equals(username) && "admin123".equals(password);
	}
	
	
	@Command
	public void changeLocale() {
		Locale locale = new Locale(this.selectedLanguage);
		Sessions.getCurrent().setAttribute(Attributes.PREFERRED_LOCALE, locale);
		Sessions.getCurrent().setAttribute("preferred_language", this.selectedLanguage);
		Executions.sendRedirect(null);
		//Locales.setThreadLocal(locale);
	}
	
}
