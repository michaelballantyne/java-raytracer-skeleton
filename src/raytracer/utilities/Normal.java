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

	/** Normal in the opposite direction of this normal. */
	public Normal minus() {
		return new Normal(-x, -y, -z);
	}

	public Normal add(Vector3D n)  {
		return new Normal(x + n.x, y + n.y, z + n.z);
	}

	/** Scalar multiplication. */
	public Normal multiply(double a) {
		return new Normal(x * a, y * a, z * a);
	}
	
	/** 
	 * 	Multiplication by a matrix on the left.
	 *  a normal is transformed by multiplying it on the left by the transpose of the upper left 3 x 3
     *  partition of the inverse transformation matrix
     */
	public static Normal multiply(Matrix mat, Normal n) {
		return new Normal( mat.m[0][0] * n.x + mat.m[1][0] * n.y + mat.m[2][0] * n.z,
						   mat.m[0][1] * n.x + mat.m[1][1] * n.y + mat.m[2][1] * n.z,
						   mat.m[0][2] * n.x + mat.m[1][2] * n.y + mat.m[2][2] * n.z );
	}
}
