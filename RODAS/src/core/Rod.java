package core;

public class Rod {
	
	public Rod(int id, int startPoint, int endPoint) {
		super();
		this.id = id;
		this.startPoint = startPoint;
		this.endpoint = endPoint;
	}
	
	int id;
	int startPoint;
	int endpoint;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStartPoint() {
		return startPoint;
	}
	public void setStartPoint(int startPoint) {
		this.startPoint = startPoint;
	}
	public int getEndpoint() {
		return endpoint;
	}
	public void setEndpoint(int endpoint) {
		this.endpoint = endpoint;
	}

}
