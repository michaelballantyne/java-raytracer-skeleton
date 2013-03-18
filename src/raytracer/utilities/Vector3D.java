package raytracer.utilities;

//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

public class Vector3D extends Tuple3D{
	
	// ---------------------------------------------------------- default constructor
	/**
	 * default constructor
	 */
	public Vector3D() {
		super();
	}

	// ---------------------------------------------------------- constructor

	/**
	 * constructor: sets all components of Vector to parameter a
	 */
	public Vector3D(double a) {
		super(a);
	}

	// ---------------------------------------------------------- constructor

	/**
	 * constructor: sets Vector to (a, b, c)
	 */
	public Vector3D(double a, double b, double c)	 
	{
		super(a,b,c);
	}

	// ---------------------------------------------------------- copy constructor
	/** 
	 * constructs a vector from a Normal, a Point3D, or another Vector3D
	 * 
	 * @param vector 
	 */

	public Vector3D(Tuple3D vector) {
		super(vector);
	}

	// ------------------------------------------------------------------------ unary minus
	// this allows ShadeRec objects to be declared as constant arguments in many shading
	// functions that reverse the direction of a ray that's stored in the ShadeRec object

	/**
	 * unary minus
	 * does not change the current vector
	 * @return Vector3D in reverse direction from original Vector
	 */
	public Vector3D minus() {
		return new Vector3D(-x, -y, -z);    
	}

	// ----------------------------------------------------------  length
	// length of the vector

	/**
	 * length of the vector
	 * @return magnitude of this vector
	 */
	public double length() {
		return (Math.sqrt(x * x + y * y + z * z));
	}

	// ---------------------------------------------------------------------  len_squared
	// the square of the length

	/**
	 * lenSquared
	 * @return the square of the length
	 */
	public double lenSquared() {	
		return (x * x + y * y + z * z);
	}


	// ----------------------------------------------------------------------- operator*
	// multiplication by a double on the right

	/**
	 * multiply: returns the product of multiplying this Vector3D by the double parameter
	 * @param a
	 * @return the new Vector3D
	 */
	public Vector3D multiply (double a) {	
		return new Vector3D(x * a, y * a, z * a);	
	}

	// ----------------------------------------------------------------------- operator/
	// division by a double

	/**
	 * divide: returns the result of dividing this Vector3D by the scalar a
	 * @param a
	 * @return the new Vector3D
	 */
	public Vector3D divide (double a) {	
		return new Vector3D(x / a, y / a, z / a);	
	}


	// ----------------------------------------------------------------------- operator+
	// addition
	
	/**
	 * add: returns the sum of this Vector3D + the parameter v
	 * @param v
	 * @return the new Vector3D sum
	 */
	public Vector3D add (Vector3D v) {
		return new Vector3D(x + v.x, y + v.y, z + v.z);
	}


	// ----------------------------------------------------------------------- operator-
	// subtraction

	/**
	 * subtract: returns the difference of this Vector3D - the parameter v
	 * @param v
	 * @return the new Vector3D difference
	 */
	public Vector3D subtract (Vector3D v) {
		return new Vector3D(x - v.x, y - v.y, z - v.z);
	}


	// ----------------------------------------------------------------------- operator*
	// dot product - will work between Vector3Ds and Normals

	/**
	 * dot: returns the dot product of this Vector3D and the parameter v
	 * @param v
	 * @return the dot product
	 */
	public double dot (Vector3D v) {
		return (x * v.x + y * v.y + z * v.z);
	} 


	// ----------------------------------------------------------------------- operator^
	// cross product - will work between Vector3Ds and Normals

	/** 
	 * cross: returns the cross product of this Vector3D x the parameter v
	 * @param v
	 * @return cross product
	 */
	public Vector3D cross (Vector3D v)  {
		return new Vector3D(y * v.z - z * v.y, z * v.x - x * v.z, x * v.y - y * v.x);
	}

	
	// ---------------------------------------------------------------------  operator+=
	// compound addition - will work between Vector3Ds and Normals

	/**
	 * plusEqual: adds Vector3D v to this Vector
	 * modifies this Vector
	 * @param v
	 */
	public void plusEqual(Vector3D v) {
		x += v.x; y += v.y; z += v.z;
	}

	// ----------------------------------------------------------  normalize
	// converts the vector to a unit vector

	/**
	 * normalize: converts this Vector3D to a unit Vector3D
	 */
	public void normalize() {	
		double length = Math.sqrt(x * x + y * y + z * z);
		x /= length; y /= length; z /= length;
	}
	
	// ----------------------------------------------------------  hat
	// converts the vector to a unit vector and returns the vector

	/**
	 * hat: returns the unit vector created from this Vector3D;
	 * this Vector3D is not modified.
	 * @return the unit vector
	 */
	public Vector3D hat() {	
		double length = Math.sqrt(x * x + y * y + z * z);
		x /= length; y /= length; z /= length;
		return this;
	}
	
	
	// static methods
	// non-member function

	// ----------------------------------------------------------------------- operator*
	// multiplication by a double on the left

	public static Vector3D multiply (double a, Vector3D v) {
		return new Vector3D(a * v.x, a * v.y, a * v.z);	
	}

	// ----------------------------------------------------------  operator* 
	// multiplication by a matrix on the left
	// overloaded by Normal class!

	public static Vector3D multiply (Matrix mat, Vector3D v) {
		return new Vector3D(mat.m[0][0] * v.x + mat.m[0][1] * v.y + mat.m[0][2] * v.z,
						mat.m[1][0] * v.x + mat.m[1][1] * v.y + mat.m[1][2] * v.z,
						mat.m[2][0] * v.x + mat.m[2][1] * v.y + mat.m[2][2] * v.z);
	}
	
}
