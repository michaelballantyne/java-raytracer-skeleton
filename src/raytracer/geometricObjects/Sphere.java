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
			
	// ---------------------------------------------------------------- default constructor

	public Sphere() {
		super();
		center = new Point3D(0.0);
		radius = 1.0;
	}
	
	// ---------------------------------------------------------------- constructor
	
	public Sphere(Point3D c, double r) {
		super();
		center = new Point3D(c);
		radius = r;
	}


	// ---------------------------------------------------------------- copy constructor

	public Sphere (Sphere sphere) {
		super(sphere);
		center = new Point3D(sphere.center);
		radius = sphere.radius;
	}

	// ---------------------------------------------------------------- clone

	public Sphere clone() {
		return (new Sphere(this));
	}
	

	// ---------------------------------------------------------------- assignment operator

	public void set (Sphere rhs) {
		if (this != rhs) {
			this.color.set(rhs.color);
			this.center.set(rhs.center);
			this.radius	= rhs.radius;	
		}
	}
	

	public void setCenter(Point3D c) {
		center.set(c);
	}
			
	public void setCenter(double x, double y, double z) {
		center.x = x;
		center.y = y;
		center.z = z;
	}
			
	public void setRadius(double r) {
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
