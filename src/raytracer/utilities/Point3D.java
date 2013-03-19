package raytracer.utilities;

//Copyright (C) Helen Hu 2011.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.


public class Point3D extends Tuple3D {
	public Point3D(double a, double b, double c){
		super(a,b,c);
	}

	/** Distance between two points. */
	public double distance(Point3D p) {
		return (Math.sqrt( (x - p.x) * (x - p.x) 
				+  (y - p.y) * (y - p.y)
				+ (z - p.z) * (z - p.z) ));
	}

	/** Unary minus of this point, or its reflection across all axes. */
	public Point3D minus() {
		return new Point3D(-x, -y, -z);
	}


	/** The vector from the argument point p to this point. */
	public Vector3D subtract(Point3D p){
		return new Vector3D(x - p.x,y - p.y,z - p.z);
	}

	/** The point translated by the argument vector. */
	public Point3D add(Vector3D v) {
		return new Point3D(x + v.x, y + v.y, z + v.z);
	}

	/** The point translated by the argument vector's negative. */
	public Point3D subtract(Vector3D v) {
		return new Point3D(x - v.x, y - v.y, z - v.z);
	}

	/** Multiplication by a scalar. */
	public Point3D multiply(double a) {
		return new Point3D(x * a,y * a,z * a);
	}

	/** The square of the distance between two points. */
	public double d_squared(Point3D p)  {
		return ( (x - p.x) * (x - p.x) 
				+  (y - p.y) * (y - p.y)
				+ (z - p.z) * (z - p.z) );
	}

	/** Multiplication by a matrix on the left. */
	public Point3D multiply(Matrix mat) {
		return new Point3D(mat.m[0][0] * x + mat.m[0][1] * y + mat.m[0][2] * z + mat.m[0][3],
				mat.m[1][0] * x + mat.m[1][1] * y + mat.m[1][2] * z + mat.m[1][3],
				mat.m[2][0] * x + mat.m[2][1] * y + mat.m[2][2] * z + mat.m[2][3]);
	}
}
