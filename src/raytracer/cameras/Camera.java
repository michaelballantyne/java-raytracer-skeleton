package raytracer.cameras;
import raytracer.utilities.Point3D;
import raytracer.utilities.Vector3D;
import raytracer.world.World;

//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

public abstract class Camera {
	protected Point3D		eye;				// eye point
	protected Point3D		lookat; 			// lookat point
	protected Vector3D		u, v, w;			// orthonormal basis vectors
	protected Vector3D		up;					// up vector
	protected float			exposureTime;		// exposure time affects color (so float)
	
	public Camera(Point3D eye, Point3D lookat, Vector3D up, float exposureTime) {		
		this.eye = eye;
		this.lookat = lookat;
		this.up = up;
		this.exposureTime = exposureTime;

		computeONB();
	}

	/** Computes an orthornormal basis given the view point, lookat point, and up vector */
	private void computeONB() {
		w = eye.subtract(lookat).hat();
		u = up.cross(w).hat(); 
		v = w.cross(u);

		// take care of the singularity by hardwiring in specific camera orientations
		if (eye.x == lookat.x && eye.z == lookat.z && eye.y > lookat.y) { // camera looking vertically down
			u = new Vector3D(0, 0, 1);
			v = new Vector3D(1, 0, 0);
			w = new Vector3D(0, 1, 0);	
		}
		
		if (eye.x == lookat.x && eye.z == lookat.z && eye.y < lookat.y) { // camera looking vertically up
			u = new Vector3D(1, 0, 0);
			v = new Vector3D(0, 0, 1);
			w = new Vector3D(0, -1, 0);
		}
	}

	public abstract void renderScene(World w);

	public String toString() {
		return "Camera:\n\teye: " + eye + "\tlookat: " + lookat + "\tup: " + up
		+ "\n\tu: " + u + "\tv: " + v + "\tw: " + w 
        + "\texposure: " + exposureTime
		;
	}
}

