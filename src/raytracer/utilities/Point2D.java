package raytracer.utilities;

//2D points are used to store sample points

public class Point2D {
	public final double x, y;

	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Point2D multiply (double a) {
		return new Point2D(x * a, y * a);
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
