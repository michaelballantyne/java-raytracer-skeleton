	package raytracer.geometricObjects;
	
	import raytracer.utilities.*;
	
	//Copyright (C) Helen Hu 2013.
	//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
	//This Java code is for non-commercial purposes only.
	//This Java code is licensed under the GNU General Public License Version 2.
	//See the file COPYING.txt for the full license.
	
	/**
	 * The Rectangle class described in Chapter 19.4
	 */
	public class Rectangle extends GeometricObject {
	
		private Point3D 		p0;   			// corner vertex 
		private Vector3D		a;				// side
		private Vector3D		b;				// side
		private double			aLenSquared;	// square of the length of side a
		private double			bLenSquared;	// square of the length of side b
		private Normal			normal;	
			
		
		// ----------------------------------------------------------------  default constructor
	
		public Rectangle() {
			super();
			p0 = new Point3D(-1, 0, -1);
			a = new Vector3D(0, 0, 2);
			b = new Vector3D(2, 0, 0);
			aLenSquared = 4.0;
			bLenSquared = 4.0;
			normal = new Normal(0, 1, 0);
		
		}
	
	
		// ----------------------------------------------------------------  constructor
		// this constructs the normal
	
		public Rectangle(Point3D _p0, Vector3D _a, Vector3D _b) {
			super();
			p0 = new Point3D(_p0);
			a = new Vector3D(_a);
			b = new Vector3D(_b);
			aLenSquared = a.lenSquared(); 
			bLenSquared = b.lenSquared();
			normal = new Normal(a.cross(b));
			normal.normalize();
		}
	
	
		// ----------------------------------------------------------------  constructor
		// this has the normal as an argument
	
		public Rectangle(Point3D _p0, Vector3D _a, Vector3D _b, Normal n) {
			super();
			p0 = new Point3D(_p0);
			a = new Vector3D(_a);
			b = new Vector3D(_b);
			normal = new Normal(n);
			normal.normalize();
			aLenSquared = a.lenSquared(); 
			bLenSquared = b.lenSquared();
		}	
	
	
		// ---------------------------------------------------------------- clone
	
		 
		public Rectangle clone() {
			return (new Rectangle(this));
		}
	
	
		// ---------------------------------------------------------------- copy constructor
	
		public Rectangle (Rectangle r) {
			super(r);
			p0 = new Point3D(r.p0);
			a = new Vector3D(r.a);
			b = new Vector3D(r.b);
			normal = new Normal(r.normal);
			aLenSquared = r.aLenSquared; 
			bLenSquared = r.bLenSquared;
		}	

	
	
		// ---------------------------------------------------------------- assignment operator

		public void set (Rectangle rhs) {
			if (this != rhs){
				super.set(rhs);
				p0.set(rhs.p0);
				a.set(rhs.a);
				b.set(rhs.b);
				normal.set(rhs.normal);

				aLenSquared	= rhs.aLenSquared; 
				bLenSquared	= rhs.bLenSquared;
				
			}
		}
	
	
		//------------------------------------------------------------------ hit 
	 												 
		public double hit(Ray ray, ShadeRec sr) {	
			double t = normal.dot(p0.subtract(ray.o)) / 
						normal.dot(ray.d);
			
			if (t <= Constants.EPSILON)
				return (-1);
					
			Point3D p = ray.o.add(ray.d.multiply(t));
			Vector3D d = p.subtract(p0);
			
			double ddota = d.dot(a);
			
			if (ddota < 0.0 || ddota > aLenSquared)
				return (-1);
				
			double ddotb = d.dot(b);
			
			if (ddotb < 0.0 || ddotb > bLenSquared)
				return (-1);
				
			sr.normal 			= normal;
			sr.localHitPoint 	= p;
			
			return (t);
		}
	

		//------------------------------------------------------------------ get_normal 
		 
		public Normal get_normal (Point3D p) {
			return (normal);
		}

	}
