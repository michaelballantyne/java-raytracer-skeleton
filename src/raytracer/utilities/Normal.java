package raytracer.utilities;

//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

public class Normal extends Vector3D {
	public Normal(double a, double b, double c)	 
	{
		super(a,b,c);
	}
	
	public Normal(Vector3D vector) {
		super(vector.x, vector.y, vector.z);
	}

	/** 
	 * unary minus
	 * this does not change the current Normal
	 * @return reverse Normal in the opposite direction from the original Normal
	 */
	public Normal minus() {
		return new Normal(-x, -y, -z);
	}

	// ----------------------------------------------------------------------- operator+
	// addition of two normals

	/**
	 * addition of two normals 
	 * @param n - Vector3D or Normal
	 * @return
	 */
	public Normal add (Vector3D n)  {
		return new Normal(x + n.x, y + n.y, z + n.z);
	}

	public Normal multiply (double a) {
		return new Normal(x * a, y * a, z * a);
	}
	
	// ----------------------------------------------------------------------- operator*
	// multiplication by a double on the left

	public static Normal multiply(double f, Normal n) {
		return new Normal(f * n.x, f * n.y,f * n.z);
	}
	
	// ---------------------------------------------------------- operator*
	// multiplication by a matrix on the left

	// a normal is transformed by multiplying it on the left by the transpose of the upper left 3 x 3
	// partition of the inverse transformation matrix

	public static Normal multiply (Matrix mat, Normal n) {
		return new Normal(	mat.m[0][0] * n.x + mat.m[1][0] * n.y + mat.m[2][0] * n.z,
						mat.m[0][1] * n.x + mat.m[1][1] * n.y + mat.m[2][1] * n.z,
						mat.m[0][2] * n.x + mat.m[1][2] * n.y + mat.m[2][2] * n.z);
	}

}
