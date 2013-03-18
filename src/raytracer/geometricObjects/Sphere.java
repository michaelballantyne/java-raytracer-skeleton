package raytracer.geometricObjects;
import raytracer.utilities.*;

//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

public class Sphere extends GeometricObject {

	private Point3D 	center;   			// center coordinates as a point  
	private double 		radius;				// the radius 
			
	// ---------------------------------------------------------------- constructor
	
	public Sphere(Point3D c, double r) {
		super();
		center = c;
		radius = r;
	}

	//---------------------------------------------------------------- hit
	// textbook has it return a boolean
	// we altered it here so it returns tmin
	// hit returns a -1 if no hit
	// hit returns a number > 0 if hit
	
	public double hit(Ray ray, ShadeRec sr) {
		double 		t;
		Vector3D	temp 	= ray.o.subtract(center);
		double 		a 		= ray.d.dot(ray.d);
		double 		b 		= 2.0 * temp.dot(ray.d);
		double 		c 		= temp.dot(temp) - (radius * radius);
		double 		disc	= b * b - 4.0 * a * c;
		
		if (disc < 0.0)
			return(-1.0);  // no hit
		else {	
			double e = Math.sqrt(disc);
			double denom = 2.0 * a;
			t = (-b - e) / denom;    // smaller root
		
			if (t > S_EPSILON) {
				sr.normal 	 = new Normal(temp.add(ray.d.multiply(t)).divide(radius));
				sr.localHitPoint = ray.o.add(ray.d.multiply(t));
				return (t);
			} 
		
			t = (-b + e) / denom;    // larger root
		
			if (t > S_EPSILON) {
				sr.normal 	 = new Normal(temp.add(ray.d.multiply(t)).divide(radius));
				sr.localHitPoint = ray.o.add(ray.d.multiply(t));
				return (t);
			} 
		}
		
		return (-1.0); // no hit
	}
	
	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			Sphere other = (Sphere)obj;
			return (super.equals(obj) 
					&& center.equals(other.center)
					&& radius == other.radius);
		}
	}
	
	public String toString() {
		return color + "sphere at " + center + " with radius " + radius;
	}

}
