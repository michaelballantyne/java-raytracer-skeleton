package raytracer.utilities;

//Copyright (C) Helen Hu 2011.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.


/** 
 * this file contains the declaration of the class Matrix
 * Matrix is a 4 x 4 square matrix that is used to represent affine transformations
 * we don't need a general m x n matrix
 *
 */
public class Matrix {
	public double[][] m = new double[4][4];
	
	/** The default constructor produces an identity matrix. */
	public Matrix() {	
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				if (x == y)
					m[x][y] = 1.0;
				else
					m[x][y] = 0.0;
			}
		}
	}

	/** Multiplication of two matrices. */
	public Matrix multiply (Matrix mat) {
		Matrix 	product = new Matrix();
		
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				double sum = 0.0;

				for (int j = 0; j < 4; j++)
					sum += m[x][j] * mat.m[j][y];
	 
				product.m[x][y] = sum;			
			}
		}
		
		return product;
	}


	/** Division by a scalar. */
	public Matrix divide(double d) {
		Matrix result = new Matrix();
		for (int x = 0; x < 4; x++)	{		
			for (int y = 0; y < 4; y++)	{	
				result.m[x][y] = m[x][y] / d;
			}
		}
		return result;
	}

	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		} else {
			Matrix other = (Matrix) obj;
			for (int x = 0; x < 4; x++) {
				for (int y = 0; y < 4; y++) {
					if (this.m[x][y] != other.m[x][y])
						return false;
				}
			}
			return true;
		}
	}
	
	public String toString() {
		return "[" + m[0][0] + "\t" + m[0][1] + "\t" + m[0][2] + "\t" + m[0][3] + "]\n" +  
			   "[" + m[1][0] + "\t" + m[1][1] + "\t" + m[1][2] + "\t" + m[1][3] + "]\n" +  
			   "[" + m[2][0] + "\t" + m[2][1] + "\t" + m[2][2] + "\t" + m[2][3] + "]\n" +  
			   "[" + m[3][0] + "\t" + m[3][1] + "\t" + m[3][2] + "\t" + m[3][3] + "]";  
	}
}
