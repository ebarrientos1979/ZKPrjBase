package pe.edu.nh.model.gif;

public class Analytics{
    public Onload getOnload() {
		return onload;
	}
	public void setOnload(Onload onload) {
		this.onload = onload;
	}
	public Onclick getOnclick() {
		return onclick;
	}
	public void setOnclick(Onclick onclick) {
		this.onclick = onclick;
	}
	public Onsent getOnsent() {
		return onsent;
	}
	public void setOnsent(Onsent onsent) {
		this.onsent = onsent;
	}
	public Onload onload;
    public Onclick onclick;
    public Onsent onsent;
}
