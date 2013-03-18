package raytracer.cameras;
import raytracer.utilities.*;
import raytracer.world.*;

//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

public class Pinhole extends Camera {

	private double	d;		// view plane distance
	private double	zoom;	// zoom factor

	// ----------------------------------------------------------------------------- default constructor

	public Pinhole(){	
		super();
		d = 500.0;
		zoom = 1.0;
	}

	// ----------------------------------------------------------------------------- copy constructor

	public Pinhole(Pinhole c){
		super(c);
		d = c.d;
		zoom = c.zoom;
	}


	// ----------------------------------------------------------------------------- clone

	public Camera clone() {
		return (new Pinhole(this));
	}

	// ----------------------------------------------------------------------------- assignment operator

	public void set (Pinhole rhs) { 	
		if (this != rhs) {
			super.set(rhs);
			d 		= rhs.d;
			zoom	= rhs.zoom;
		}
	}



	//-------------------------------------------------------------------------- set_vpd

	public void setViewDistance(double _d) {
		d = _d;
	}	
		


	//-------------------------------------------------------------------------- set_zoom

	public void setZoom(double zoomFactor) {
		zoom = zoomFactor;
	}	
	// ----------------------------------------------------------------------------- get_direction

	public Vector3D getDirection(Point2D p) {
		Vector3D temp1 = u.multiply(p.x);
		Vector3D temp2 = v.multiply(p.y);
		Vector3D temp3 = w.multiply(-d);
		Vector3D dir = (temp1.add(temp2)).add(temp3);
		dir.normalize();
		return(dir);
	}



	// ----------------------------------------------------------------------------- render_scene

	public void renderScene(World w) {
		RGBColor	L = new RGBColor();
		ViewPlane	vp = new ViewPlane(w.vp);	 								
		Ray			ray = new Ray();
		int 		depth = 0;  
		Point2D 	pp = new Point2D();		// sample point on a pixel
		int n = (int)Math.sqrt((float)vp.numSamples);
			
		vp.s /= zoom;
		ray.o = eye;
			
		for (int r = 0; r < vp.vres; r++)			// up
			for (int c = 0; c < vp.hres; c++) {		// across 					
				L.set(RGBColor.BLACK); 
				
				for (int p = 0; p < n; p++)			// up pixel
					for (int q = 0; q < n; q++) {	// across pixel
						pp.x = vp.s * (c - 0.5 * vp.hres + (q + 0.5) / n); 
						pp.y = vp.s * (r - 0.5 * vp.vres + (p + 0.5) / n);
						ray.d = getDirection(pp);
						L.plusEqual(w.tracer.traceRay(ray, depth));
					}	
												
				L.divideEqual(vp.numSamples);
				L.timesEqual(exposureTime);
				w.displayPixel(r, c, L);
			} 
	}
}
