package raytracer.utilities;

//Copyright (C) Helen Hu 2011.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.


public class Point3D extends Tuple3D {

	// --------------------------------------------- default constructor
	public Point3D(){
		super();
	}

	// --------------------------------------------- constructor
	public Point3D(double a){
		super(a);
	}

	// --------------------------------------------- constructor
	public Point3D(double a, double b, double c){
		super(a,b,c);
	}

	// --------------------------------------------- constructor
	public Point3D(Point3D p){
		super(p);
	}

	// --------------------------------------------- distance
	// distance between two points

	public double distance(Point3D p) {
		return (Math.sqrt( (x - p.x) * (x - p.x) 
				+  (y - p.y) * (y - p.y)
				+ (z - p.z) * (z - p.z) ));
	}

	// -------------------------------------------------------------- operator-
	// unary minus

	public Point3D minus() {
		return new Point3D(-x, -y, -z);
	}


	// -------------------------------------------------------------- operator-
	// the vector that joins two points

	public Vector3D subtract(Point3D p){
		return new Vector3D(x - p.x,y - p.y,z - p.z);
	}

	// -------------------------------------------------------------- operator+
	// addition of a vector to a point that returns a new point

	public Point3D add(Vector3D v) {
		return new Point3D(x + v.x, y + v.y, z + v.z);
	}

	// -------------------------------------------------------------- operator-
	// subtraction of a vector from a point that returns a new point

	public Point3D subtract(Vector3D v) {
		return new Point3D(x - v.x, y - v.y, z - v.z);
	}

	// -------------------------------------------------------------- operator*
	// mutliplication by a double on the right

	public Point3D multiply(double a) {
		return new Point3D(x * a,y * a,z * a);
	}

	// -------------------------------------------------------------- d_squared
	// the square of the distance between the two points as a member function

	public double d_squared(Point3D p)  {
		return ( (x - p.x) * (x - p.x) 
				+  (y - p.y) * (y - p.y)
				+ (z - p.z) * (z - p.z) );
	}

	// static methods
	// non-member function

	// -------------------------------------------------------------- operator*
	// multiplication by a double on the left

	public static Point3D multiply (double a, Point3D p) {
		return new Point3D(a * p.x, a * p.y, a * p.z);
	}


	// --------------------------------------------- operator*
	// multiplication by a matrix on the left

	public static Point3D multiply (Matrix mat, Point3D p) {
		return new Point3D(mat.m[0][0] * p.x + mat.m[0][1] * p.y + mat.m[0][2] * p.z + mat.m[0][3],
				mat.m[1][0] * p.x + mat.m[1][1] * p.y + mat.m[1][2] * p.z + mat.m[1][3],
				mat.m[2][0] * p.x + mat.m[2][1] * p.y + mat.m[2][2] * p.z + mat.m[2][3]);
	}


	/**
	 * addition of a vector to a point that returns a new point
	 * @param p point
	 * @param v vector
	 * @return the sum of the points (p1 + p2)
	 */
	public static Point3D add(Point3D p, Vector3D p2) {
		return new Point3D(p.x + p2.x,
				p.y + p2.y,
				p.z + p2.z);
	}

	/** 
	 * the vector that joins two points
	 * @param p1
	 * @param p2
	 * @return the difference between the points (p1 - p2)
	 */
	public static Vector3D subtract(Point3D p1, Point3D p2) {
		return new Vector3D(p1.x - p2.x,
				p1.y - p2.y,
				p1.z - p2.z);
	}

	/** 
	 * subtraction of a vector
	 * @param p
	 * @param v
	 * @return the difference between the points (p1 - p2)
	 */
	public static Point3D subtract(Point3D p, Vector3D v) {
		return new Point3D(p.x - v.x,
				p.y - v.y,
				p.z - v.z);
	}


	/** 
	 * unary minus of a point
	 * @param p
	 * @return -p
	 */
	public static Point3D minus(Point3D p) {
		return new Point3D(-p.x, -p.y, -p.z);
	}

	/**
	 * the square of the distance between the two points 
	 * @param p1
	 * @param p2
	 * @return the square of the distance between two points
	 */
	public static double d_squared(Point3D p1, Point3D p2) {
		return ( (p1.x - p2.x) * (p1.x - p2.x) 
				+  (p1.y - p2.y) * (p1.y - p2.y)
				+ (p1.z - p2.z) * (p1.z - p2.z) );
	}

} // end Point3D class
