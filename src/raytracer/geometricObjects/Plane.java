package raytracer.geometricObjects;

import raytracer.utilities.*;

//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

public class Plane extends GeometricObject {
	private Point3D 	a;   				// point through which plane passes 
	private Normal 		n;					// normal to the plane

	// ----------------------------------------------------------------------  constructor

	public Plane(Point3D point, Normal normal, RGBColor color) {
		super(color);
		a = point;
		n = new Normal(normal.hat());
	}

	// ----------------------------------------------------------------- hit
	// textbook has it return a boolean
	// we altered it here so it returns tmin
	// hit returns a -1 if no hit
	// hit returns a number > 0 if hit
	
	public double hit(Ray ray, ShadeRec sr) {
		double t = ((a.subtract(ray.o)).dot(n)) / ((ray.d.dot(n))); 
		
		if (t > S_EPSILON) {
			sr.normal = n;
			sr.localHitPoint = ray.o.add(ray.d.multiply(t));
			
			return t;	
		}

		return -1.0;
	}
	
	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			Plane other = (Plane)obj;
			return (super.equals(obj) 
					&& a.equals(other.a)
					&& n.equals(other.n));
		}
	}
	
	public String toString() {
		return color + "plane through " + a + " with " + n;
	}

}
