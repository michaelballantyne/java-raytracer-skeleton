package raytracer.utilities;

//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

public class Vector3D extends Tuple3D{
	public Vector3D(double a, double b, double c)	 
	{
		super(a,b,c);
	}

	/**
	 * unary minus
	 * does not change the current vector
	 * @return Vector3D in reverse direction from original Vector
	 */
	public Vector3D minus() {
		return new Vector3D(-x, -y, -z);    
	}

	/**
	 * length of the vector
	 * @return magnitude of this vector
	 */
	public double length() {
		return (Math.sqrt(x * x + y * y + z * z));
	}

	/**
	 * lenSquared
	 * @return the square of the length
	 */
	public double lenSquared() {	
		return (x * x + y * y + z * z);
	}

	/**
	 * multiply: returns the product of multiplying this Vector3D by the double parameter
	 * @param a
	 * @return the new Vector3D
	 */
	public Vector3D multiply (double a) {	
		return new Vector3D(x * a, y * a, z * a);	
	}

	/**
	 * divide: returns the result of dividing this Vector3D by the scalar a
	 * @param a
	 * @return the new Vector3D
	 */
	public Vector3D divide (double a) {	
		return new Vector3D(x / a, y / a, z / a);	
	}
	
	/**
	 * add: returns the sum of this Vector3D + the parameter v
	 * @param v
	 * @return the new Vector3D sum
	 */
	public Vector3D add (Vector3D v) {
		return new Vector3D(x + v.x, y + v.y, z + v.z);
	}

	/**
	 * subtract: returns the difference of this Vector3D - the parameter v
	 * @param v
	 * @return the new Vector3D difference
	 */
	public Vector3D subtract (Vector3D v) {
		return new Vector3D(x - v.x, y - v.y, z - v.z);
	}

	/**
	 * dot: returns the dot product of this Vector3D and the parameter v
	 * @param v
	 * @return the dot product
	 */
	public double dot (Vector3D v) {
		return (x * v.x + y * v.y + z * v.z);
	} 

	/** 
	 * cross: returns the cross product of this Vector3D x the parameter v
	 * @param v
	 * @return cross product
	 */
	public Vector3D cross (Vector3D v)  {
		return new Vector3D(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
	}

	/**
	 * plusEqual: adds Vector3D v to this Vector
	 * modifies this Vector
	 * @param v
	 */
	public Vector3D plus(Vector3D v) {
		return new Vector3D(x + v.x, y + v.y, z + v.z);
	}

	public Vector3D hat() {	
		double length = Math.sqrt(x * x + y * y + z * z);
		return new Vector3D(x / length, y / length, z / length);
	}

	/**
	 * multiplication by a matrix on the left.
	 */
	public Vector3D multiply (Matrix mat) {
		return new Vector3D(mat.m[0][0] * x + mat.m[0][1] * y + mat.m[0][2] * z,
						mat.m[1][0] * x + mat.m[1][1] * y + mat.m[1][2] * z,
						mat.m[2][0] * x + mat.m[2][1] * y + mat.m[2][2] * z);
	}
	
}
