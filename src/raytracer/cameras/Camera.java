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
	protected double		ra;					// roll angle
	protected Vector3D		u, v, w;			// orthonormal basis vectors
	protected Vector3D		up;					// up vector
	protected float			exposureTime;		// exposure time affects color (so float)
	
	// ----------------------------------------------------------------- default constructor

	public Camera() {		
			eye = new Point3D(0, 0, 500.0);
			lookat = new Point3D(0);
			ra=0;
			up = new Vector3D(0, 1, 0);
			u = new Vector3D(1, 0, 0);
			v = new Vector3D(0, 1, 0);
			w = new Vector3D(0, 0, 1);
			exposureTime = 1f;
	}

	// ----------------------------------------------------------------- copy constructor

	public Camera(Camera c) {   		
		 	eye = new Point3D(c.eye);
			lookat = new Point3D(c.lookat);
			ra = c.ra;
			up = new Vector3D(c.up);
			u = new Vector3D(c.u);
			v = new Vector3D(c.v);
			w = new Vector3D(c.w);
			exposureTime = c.exposureTime;
	}



	// ----------------------------------------------------------------- assignment operator

	public void set (Camera rhs) {
		if (this != rhs){		
			eye.set(rhs.eye);
			lookat.set(rhs.lookat);
			up.set(rhs.up);
			u.set(rhs.u);
			v.set(rhs.v);
			w.set(rhs.w);
			ra				= rhs.ra;
			exposureTime 	= rhs.exposureTime;
		}
	}

	// ----------------------------------------------------------------- set_eye

	public void setEye(Point3D p) {
		eye.set(p);
	}


	// ----------------------------------------------------------------- set_eye

	public void setEye(double x, double y, double z) {
		eye.set(x,y,z);
	}


	// ----------------------------------------------------------------- set_lookat

	public void setLookat(Point3D p) {
		lookat.set(p);
	}


	// ----------------------------------------------------------------- set_lookat

	public void setLookat(double x, double y, double z) {
		lookat.set(x,y,z);
	}	


	// ----------------------------------------------------------------- set_up_vector

	public void setUpVector(Vector3D u) {
		up.set(u);
	}


	// ----------------------------------------------------------------- set_up_vector

	public void setUpVector(double x, double y, double z) {
		up.set(x,y,z);
	}


	// ----------------------------------------------------------------- set_roll

	public void setRoll(double r) { 
		ra = r;
	}


	// ----------------------------------------------------------------- set_exposure_time

	public void setExposureTime(float exposure) {
		exposureTime = exposure;
	}
	
	//-------------------------------------------------------------- compute_uvw

	// This computes an orthornormal basis given the view point, lookat point, and up vector

	public void computeONB() {
		w = eye.subtract(lookat);
		w.normalize();
		u = up.cross(w); 
		u.normalize();
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
	
	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			Camera other = (Camera)obj;
			return (this.eye.equals(other.eye) && 
					this.lookat.equals(other.lookat) &&
					this.up.equals(other.up) &&
					this.u.equals(other.u) && 
					this.v.equals(other.v) && 
					this.w.equals(other.w) &&
					this.ra	== other.ra && 
					this.exposureTime == other.exposureTime);	

		}
	}

	public String toString() {
		return "Camera:\n\teye: " + eye + "\tlookat: " + lookat + "\tup: " + up
		+ "\n\tu: " + u + "\tv: " + v + "\tw: " + w 
//		+ "\n\troll: " + ra + "\texposure: " + exposure_time
		;
	}
}

