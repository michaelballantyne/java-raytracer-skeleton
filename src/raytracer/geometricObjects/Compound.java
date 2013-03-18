package raytracer.geometricObjects;

import raytracer.utilities.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

/**
 * The Compound abstract class is the parent class of all compound objects, 
 * including triangular meshes. 
 */

public abstract class Compound extends GeometricObject {
	protected List<GeometricObject> objects = new ArrayList<GeometricObject>();

	/** Method reads in a triangle mesh from a SMF file, 
	 * and stores the indices and vertices in meshes/triangles
	 * through helper methods
	 */
	public abstract void readSMFfile(String filename) throws IOException;

	public double hit(Ray ray, ShadeRec sr) {
		Normal		normal = null;
		Point3D		localHitPoint = null;
		boolean		hit 		= false;
		double		tmin 		= Double.MAX_VALUE;
		
		for (GeometricObject obj : objects) {
			double t = obj.hit(ray, sr);
			if (0 < t && t < tmin) {
				hit				= true;
				tmin 			= t;
				normal			= sr.normal;
				localHitPoint	= sr.localHitPoint;  
				color = obj.color;
			}
		}
		
		if (hit) {
			sr.t				= tmin;
			sr.normal 			= normal;   
			sr.localHitPoint 	= localHitPoint;  
			sr.color = color;
		}
		
		return (tmin);
	}
}
