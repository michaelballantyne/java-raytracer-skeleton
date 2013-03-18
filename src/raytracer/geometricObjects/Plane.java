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
					
	// ----------------------------------------------------------------------  default constructor

	public Plane() {	
		super();
		a = new Point3D(0.0);
		n = new Normal(0, 1, 0);						
	}

	// ----------------------------------------------------------------------  constructor

	public Plane(Point3D point, Normal normal) {
		super();
		a = new Point3D(point);
		n = new Normal(normal);
		n.normalize();
	}

	// ---------------------------------------------------------------- copy constructor

	public Plane(Plane plane) {
		super(plane);
		a = new Point3D(plane.a);
		n = new Normal(plane.n); 				
	}

	// ---------------------------------------------------------------- clone

	public GeometricObject clone() {
		return (new Plane(this));

	}

	// ---------------------------------------------------------------- assignment operator

	public void set (Plane rhs)	{
		
		if (this != rhs) {
			super.set(rhs);
			a.set(rhs.a);
			n.set(rhs.n);
		}
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
