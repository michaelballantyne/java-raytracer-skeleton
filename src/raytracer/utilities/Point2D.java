package raytracer.utilities;

//2D points are used to store sample points

public class Point2D {
	public double x, y;
	
	// ------------------------------------------------ default constructor

	public Point2D() {
		x = 0.0;
		y = 0.0;
	}
	
	// ------------------------------------------------ constructor

	public Point2D(double arg){
		x = arg;
		y = arg;
	}

	// ------------------------------------------------ constructor

	public Point2D(double x1, double y1) {
		x = x1;
		y = y1;
	}

	// ------------------------------------------------ copy constructor

	public Point2D(Point2D p) {
		x = p.x;
		y = p.y;
	}

	// ------------------------------------------------ assignment operator

	public void set (Point2D rhs) {
		if (this != rhs) {
			x = rhs.x;
			y = rhs.y;
		}
	}

	public Point2D multiply (double a) {
		return (new Point2D(x * a, y * a));
	}
	

	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			Point2D other = (Point2D)obj;
			return (this.x==other.x && this.y==other.y);
		}
	}
	
	public String toString() {
		return "Point2D: (" + x + "," + y + ")";
	}
}
