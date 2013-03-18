package raytracer.cameras;
import raytracer.utilities.*;
import raytracer.world.*;

//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

public class Pinhole extends Camera {

	public Pinhole(Point3D eye, Point3D lookat, double ra, Vector3D up,
			float exposureTime, double d, double zoom) {
		super(eye, lookat, ra, up, exposureTime);
		this.d = d;
		this.zoom = zoom;
	}



	private double	d;		// view plane distance
	private double	zoom;	// zoom factor


	// ----------------------------------------------------------------------------- get_direction

	public Vector3D getDirection(Point2D p) {
		Vector3D temp1 = u.multiply(p.x);
		Vector3D temp2 = v.multiply(p.y);
		Vector3D temp3 = w.multiply(-d);
		Vector3D dir = (temp1.add(temp2)).add(temp3).hat();
		return(dir);
	}



	// ----------------------------------------------------------------------------- render_scene

	public void renderScene(World w) {
		RGBColor	L;
		ViewPlane	vp = new ViewPlane(w.vp);	 								
		Ray			ray;
		int 		depth = 0;  
		Point2D 	pp = new Point2D();		// sample point on a pixel
		int n = (int)Math.sqrt((float)vp.numSamples);
			
		vp.s /= zoom;
		Point3D rayOrigin = eye;
			
		for (int r = 0; r < vp.vres; r++)			// up
			for (int c = 0; c < vp.hres; c++) {		// across 					
				L = (RGBColor.BLACK); 
				
				for (int p = 0; p < n; p++)			// up pixel
					for (int q = 0; q < n; q++) {	// across pixel
						pp.x = vp.s * (c - 0.5 * vp.hres + (q + 0.5) / n); 
						pp.y = vp.s * (r - 0.5 * vp.vres + (p + 0.5) / n);
						Vector3D rayDirection = getDirection(pp);
						L = L.add(w.tracer.traceRay(new Ray(rayOrigin, rayDirection), depth));
					}	
												
				L = L.divide(vp.numSamples);
				L = L.multiply(exposureTime);
				w.displayPixel(r, c, L);
			} 
	}
}
