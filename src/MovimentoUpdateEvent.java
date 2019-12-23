public class MovimentoUpdateEvent {
	
	private static final long serialVersionUID = 1L;
	
	private double x,y,z;
	
	public MovimentoUpdateEvent(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

	public String toString() {
		return "Position x : " + x + " Position y : " + y + " Position z : " + z;
	}

}
