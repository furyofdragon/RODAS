package core;

public class Material {
	
	public Material(int id, double E, double mu, double ro) {
		super();
		this.id = id;
		this.E = E;
		this.mu = mu;
		this.ro = ro;
	}
	
	int id;
	double E;
	double mu;
	double G;
	double ro;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getE() {
		return E;
	}
	
	public void setE(double e) {
		E = e;
	}
	
	public double getMu() {
		return mu;
	}
	
	public void setMu(double mu) {
		this.mu = mu;
	}
	
	public double getG() {
		return G;
	}
	
	public void setG() {
		this.G = 0.5 * this.E / (1 + this.mu);
	}
	
	public double getRo() {
		return ro;
	}
	
	public void setRo(double ro) {
		this.ro = ro;
	}

}
