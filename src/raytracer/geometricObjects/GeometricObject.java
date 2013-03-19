package raytracer.geometricObjects;
import raytracer.utilities.HitInfo;
import raytracer.utilities.RGBColor;
import raytracer.utilities.Ray;

//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

/** 
 * this file contains the definition of the class GeometricObject 
 */
public abstract class GeometricObject {

	public final RGBColor   color;						   // only used for Bare Bones ray tracing
	protected static final double S_EPSILON = 0.001;   // for shadows and secondary rays

	public GeometricObject() {
		color = null;
	}
	
	public GeometricObject(RGBColor color) {
		this.color = color;
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
	
	public abstract HitInfo hit(Ray ray);
}
