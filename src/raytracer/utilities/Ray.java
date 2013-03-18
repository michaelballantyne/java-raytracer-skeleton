package raytracer.utilities;

//Copyright (C) Helen Hu 2011.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

public class Ray {
	public Point3D		o;  	// origin 
	public Vector3D		d; 		// direction 
	
	// ---------------------------------------------------------------- default constructor

	public Ray () {
		o = new Point3D(0.0);
		d = new Vector3D(0.0, 0.0, 1.0);
	}

	// ---------------------------------------------------------------- constructor

	public Ray (Point3D origin, Vector3D dir) {
			o = new Point3D(origin);
			d = new Vector3D(dir);
	}

	// ---------------------------------------------------------------- copy constructor

	public Ray (Ray ray) {
		o = new Point3D(ray.o);
		d = new Vector3D(ray.d);
	}

	// ---------------------------------------------------------------- assignment operator

	public void set (Ray rhs) {
		
		if (this != rhs) {			
			o = new Point3D(rhs.o); 
			d = new Vector3D(rhs.d);
		}
	}
	
	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			Ray other = (Ray)obj;
			return (o.equals(other.o) && d.equals(other.d));
		}
	}
	
	public String toString() {
		return "Ray: origin (" + o + ")\tdir (" + d + ")";
	}
}
