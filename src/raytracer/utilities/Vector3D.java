package raytracer.utilities;

//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

public class Vector3D extends Tuple3D {
	public Vector3D(double a, double b, double c)	 
	{
		super(a,b,c);
	}

	/** Vector3D in reverse direction from this Vector */
	public Vector3D minus() {
		return new Vector3D(-x, -y, -z);    
	}

	/** Length of this vector */
	public double length() {
		return (Math.sqrt(x * x + y * y + z * z));
	}

	/** Square of the length of this vector. */
	public double lenSquared() {	
		return (x * x + y * y + z * z);
	}

	/** Scalar multiplication. */
	public Vector3D multiply (double a) {	
		return new Vector3D(x * a, y * a, z * a);	
	}

	/** Scalar division. */
	public Vector3D divide (double a) {	
		return new Vector3D(x / a, y / a, z / a);	
	}
	
	/** Sum of two vectors. */
	public Vector3D add (Vector3D v) {
		return new Vector3D(x + v.x, y + v.y, z + v.z);
	}

	/** Subtraction of parameter vector from this vector. */
	public Vector3D subtract(Vector3D v) {
		return new Vector3D(x - v.x, y - v.y, z - v.z);
	}

	/** Dot product. */
	public double dot(Vector3D v) {
		return (x * v.x + y * v.y + z * v.z);
	} 

	/** Cross product, with this vector on the left and the parameter on the right. */
	public Vector3D cross(Vector3D v)  {
		return new Vector3D(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
	}

	/** Unit length (normalized) vector in the same direction as this one. */
	public Vector3D hat() {	
		double length = Math.sqrt(x * x + y * y + z * z);
		return new Vector3D(x / length, y / length, z / length);
	}

	/** Multiplication by a matrix on the left. */
	public Vector3D multiply (Matrix mat) {
		return new Vector3D(mat.m[0][0] * x + mat.m[0][1] * y + mat.m[0][2] * z,
						mat.m[1][0] * x + mat.m[1][1] * y + mat.m[1][2] * z,
						mat.m[2][0] * x + mat.m[2][1] * y + mat.m[2][2] * z);
	}
}
