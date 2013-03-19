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

	/** This constructor calculates the normal. */
	public Rectangle(Point3D _p0, Vector3D _a, Vector3D _b, RGBColor color) {
		this(_p0, _a, _b, new Normal(_a.cross(_b).hat()), color);
	}
	
	/** This constructor takes the normal as an argument. */
	public Rectangle(Point3D _p0, Vector3D _a, Vector3D _b, Normal n, RGBColor color) {
		super(color);
		this.p0 = _p0;
		this.a = _a;
		this.b = _b;
		this.normal = new Normal(n.hat());
		aLenSquared = a.lenSquared(); 
		bLenSquared = b.lenSquared();
	}	

	public HitInfo hit(Ray ray) {	
		double t = normal.dot(p0.subtract(ray.origin)) / 
				normal.dot(ray.direction);

		if (t <= Constants.EPSILON) {
			return null;
		}

		Point3D p = ray.origin.add(ray.direction.multiply(t));
		Vector3D d = p.subtract(p0);

		double ddota = d.dot(a);

		if (ddota < 0.0 || ddota > aLenSquared) {
			return null;
		}

		double ddotb = d.dot(b);

		if (ddotb < 0.0 || ddotb > bLenSquared) {
			return null;
		}

		return new HitInfo(normal, p, color, t);
	}

	public Normal getNormal (Point3D p) {
		return (normal);
	}
}