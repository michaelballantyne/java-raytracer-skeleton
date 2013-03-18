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
	
		// ----------------------------------------------------------------  constructor
		// this constructs the normal
	
		public Rectangle(Point3D _p0, Vector3D _a, Vector3D _b) {
			super();
			p0 = _p0;
			a = _a;
			b = _b;
			aLenSquared = a.lenSquared(); 
			bLenSquared = b.lenSquared();
			normal = new Normal(a.cross(b).hat());
		}
	
	
		// ----------------------------------------------------------------  constructor
		// this has the normal as an argument
	
		public Rectangle(Point3D _p0, Vector3D _a, Vector3D _b, Normal n) {
			super();
			p0 = _p0;
			a = _a;
			b = _b;
			normal = new Normal(n.hat());
			aLenSquared = a.lenSquared(); 
			bLenSquared = b.lenSquared();
		}	
		 												 
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
			 
		public Normal get_normal (Point3D p) {
			return (normal);
		}

	}
