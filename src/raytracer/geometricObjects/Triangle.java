package raytracer.geometricObjects;
import raytracer.utilities.*;

//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

/**
 * The Triangle class described in Chapter 19.3
 * These are individual triangles, not triangle meshes (see the MeshTriangle class)
 */
public class Triangle extends GeometricObject {
	private	Point3D	v0, v1, v2;
	private Normal	normal;

	// ----------------------------------------------------------------  default constructor

	public Triangle(){
		super();
		v0 = new Point3D(0,0,0);
		v1 = new Point3D(0,0,1);
		v2 = new Point3D(1,0,0);
		normal = new Normal(0,1,0);
	}

	// ---------------------------------------------------------------- constructor

	public Triangle (Point3D a, Point3D b, Point3D c){
		super();
		v0 = new Point3D(a);
		v1 = new Point3D(b);
		v2 = new Point3D(c);
		this.computeNormal();	
	}


	// ---------------------------------------------------------------- copy constructor

	public Triangle (Triangle triangle){
		super(triangle);
		v0 = new Point3D(triangle.v0);
		v1 = new Point3D(triangle.v1);
		v2 = new Point3D(triangle.v2);
		normal = new Normal(triangle.normal);
	}

	// ---------------------------------------------------------------- clone

	public Triangle clone() {
		return (new Triangle(this));
	}

	// ---------------------------------------------------------------- assignment operator

	public void set(Triangle rhs) {
		if (this != rhs) {
			super.set(rhs);
			v0.set(rhs.v0);
			v1.set(rhs.v1);
			v2.set(rhs.v2);
			normal.set(rhs.normal);
		}
	}


	// ---------------------------------------------------------------- compute_normal

	protected void computeNormal() {
		normal = new Normal((v1.subtract(v0)).cross(v2.subtract(v0)));  
		normal.normalize();
	}

	// ------------------------------------------------------------------------------ hit

	public double hit(Ray ray, ShadeRec sr) {	
		double a = v0.x - v1.x, b = v0.x - v2.x, c = ray.d.x, d = v0.x - ray.o.x; 
		double e = v0.y - v1.y, f = v0.y - v2.y, g = ray.d.y, h = v0.y - ray.o.y;
		double i = v0.z - v1.z, j = v0.z - v2.z, k = ray.d.z, l = v0.z - ray.o.z;

		double m = f * k - g * j, n = h * k - g * l, p = f * l - h * j;
		double q = g * i - e * k, s = e * j - f * i;

		double inv_denom  = 1.0 / (a * m + b * q + c * s);

		double e1 = d * m - b * n - c * p;
		double beta = e1 * inv_denom;

		if (beta < 0.0)
			return (-1.0);  // no intersection

		double r = e * l - h * i;
		double e2 = a * n + d * q + c * r;
		double gamma = e2 * inv_denom;

		if (gamma < 0.0 )
			return (-1.0);  // no intersection

		if (beta + gamma > 1.0)
			return (-1.0);  // no intersection

		double e3 = a * p - b * r + d * s;
		double t = e3 * inv_denom;

		if (t < Constants.EPSILON) 
			return (-1.0);  // no intersection

		sr.normal 			= normal;  	
		sr.localHitPoint 	= ray.o.add(ray.d.multiply(t));	

		return (t);	
	}  		


	// ------------------------------------------------------------------------------ shadow_hit

	public double shadow_hit(Ray ray) {	
		double a = v0.x - v1.x, b = v0.x - v2.x, c = ray.d.x, d = v0.x - ray.o.x; 
		double e = v0.y - v1.y, f = v0.y - v2.y, g = ray.d.y, h = v0.y - ray.o.y;
		double i = v0.z - v1.z, j = v0.z - v2.z, k = ray.d.z, l = v0.z - ray.o.z;

		double m = f * k - g * j, n = h * k - g * l, p = f * l - h * j;
		double q = g * i - e * k, s = e * j - f * i;

		double invDenom  = 1.0 / (a * m + b * q + c * s);

		double e1 = d * m - b * n - c * p;
		double beta = e1 * invDenom;

		if (beta < 0.0)
			return (-1.0);

		double r = e * l - h * i;
		double e2 = a * n + d * q + c * r;
		double gamma = e2 * invDenom;

		if (gamma < 0.0)
			return (-1.0);

		if (beta + gamma > 1.0)
			return (-1.0);

		double e3 = a * p - b * r + d * s;
		double t = e3 * invDenom;

		if (t < Constants.EPSILON)
			return (-1.0);

		return(t);	
	}  


	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			Triangle other = (Triangle)obj;
			return (super.equals(obj) 
					&& v0.equals(other.v0) && v1.equals(other.v1) 
					&& v2.equals(other.v2) && normal.equals(other.normal));
		}
	}
	
	public String toString() {
		return color + "triangle: [" + v0 + ", "+ v1 + ", " + v2 + "] with " + normal;
	}

}
