package raytracer.utilities;

//Copyright (C) Helen Hu 2011.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

public abstract class Tuple3D {

	public double	x, y, z;
	
	// ---------------------------------------------------------- default constructor

	public Tuple3D()						
	{
		x = y = z = 0;
	}


	// ---------------------------------------------------------- constructor

	public Tuple3D(double a)
	{
		x = y = z = a;
	}


	// ---------------------------------------------------------- constructor

	public Tuple3D(double a, double b, double c)	 
	{
		x = a;
		y = b;
		z = c;
	}


	// ---------------------------------------------------------- copy constructor

	public Tuple3D(Tuple3D t)
	{
		 x = t.x;
		 y = t.y; 
		 z = t.z;
	}


	// ----------------------------------------------------------- operator=
	// assignment operator

	public void set (Tuple3D rhs) {
		if (this != rhs) {
			x = rhs.x; y = rhs.y; z = rhs.z;
		}
	}
	
	public void set (double x, double y, double z) {
		this.x = x; this.y = y; this.z = z;
	}
	
	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			Tuple3D other = (Tuple3D)obj;
			return (this.x==other.x && this.y==other.y && this.z==other.z);
		}
	}
	
	public String toString() {
		String className = this.getClass().getName();
		return className.substring(className.lastIndexOf(".")+1) + ": (" + x + "," + y + "," + z + ")";
	}

}
