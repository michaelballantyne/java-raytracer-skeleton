package raytracer.geometricObjects;

import raytracer.utilities.*;

import java.io.IOException;
import java.util.Vector;

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
	protected Vector<GeometricObject> objects;
	public Compound() {
		super();
		objects = new Vector<GeometricObject>();
	}	

	// ---------------------------------------------------------------- copy constructor

	public Compound(Compound c)
	{
		super(c);
		copyObjects(c.objects);					
	}


//	---------------------------------------------------------------- assignment operator

	public void set (Compound rhs) {
		if (this != rhs) {
			super.set(rhs);
			copyObjects(rhs.objects);				
		}
	}


	// ---------------------------------------------------------------- add_object

	public void addObject(GeometricObject object) {
		objects.add(object);	
	}


	//------------------------------------------------------------------ delete_objects
	// Deletes the objects in the objects array, and erases the array.

	private void deleteObjects() {
		objects.clear();
	}


	//------------------------------------------------------------------ copy_objects

	private void copyObjects(Vector<GeometricObject> rhsObjects) {
		deleteObjects();    	
		for (GeometricObject new_object : rhsObjects)
			objects.add(new_object.clone());
	}

	/**Method reads in a triangle mesh from a SMF file, 
	 * and stores the indices and vertices in meshes/triangles
	 * through helper methods
	 */
	public abstract void readSMFfile(String filename) throws IOException;

	//------------------------------------------------------------------ hit

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
			}
		}
		
		if (hit) {
			sr.t				= tmin;
			sr.normal 			= normal;   
			sr.localHitPoint 	= localHitPoint;  
		}
		
		return (tmin);
	}

}
