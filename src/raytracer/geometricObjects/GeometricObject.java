package raytracer.geometricObjects;
import raytracer.utilities.RGBColor;
import raytracer.utilities.Ray;
import raytracer.utilities.ShadeRec;

//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

/** 
 * this file contains the definition of the class GeometricObject 
 */
public abstract class GeometricObject {

	protected RGBColor   color;						   // only used for Bare Bones ray tracing
	protected static final double S_EPSILON = 0.001;   // for shadows and secondary rays

	// ---------------------------------------------------------------------- default constructor

	public GeometricObject() {
		color = new RGBColor(RGBColor.BLACK);
	}

	// ---------------------------------------------------------------------- copy constructor

	public GeometricObject (GeometricObject object) {
		this.color = new RGBColor(object.color);	
	}


	// ---------------------------------------------------------------------- assignment operator

	public void set (GeometricObject rhs) {

		if (this != rhs) 
			this.color.set(rhs.color);
	}

	public abstract double hit(Ray ray, ShadeRec s);


	// --------------------------------------------------------------------  set_colour

	public void setColor(RGBColor c) {
		color.set(c);
	}

	// --------------------------------------------------------------------  set_colour

	public void setColor(float r, float g, float b) {
		color.r = r;
		color.b = b;
		color.g = g;
	}

	// --------------------------------------------------------------------  get_colour

	public RGBColor getColor() {
		return (color);
	}

	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			GeometricObject other = (GeometricObject)obj;
			return (color.equals(other.color));
		}
	}

	public String toString() {
		String className = this.getClass().getName();
		return className.substring(className.lastIndexOf("."+1)) + 
				" with color " + color;
	}
}
