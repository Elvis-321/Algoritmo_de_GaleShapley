
public class Celda {

	private String dato;
	private int pareja;
	private int preferencia;
	
	public Celda(String d, int p, int pref) {
		this.dato = d;
		this.pareja = p;
		this.preferencia = pref;
	}
	
	public String getDato() {
		return this.dato;
	}
	
	public int getPareja() {
		return this.pareja;
	}
	
	public int getPreferencia() {
		return this.preferencia;
	}
	
	public void setPareja(int p) {
		this.pareja = p;
	}
	
	public boolean equals(Celda x) {
		return this.dato.equals(x.dato);
	}
	
	public String toString() {
		return this.dato;
	}
}
