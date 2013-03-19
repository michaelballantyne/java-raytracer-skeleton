package raytracer.cameras;

import raytracer.utilities.*;
import raytracer.world.*;

//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

public class Pinhole extends Camera {
	private double d;		// view plane distance
	private double zoom;	// zoom factor
	
	public Pinhole(Point3D eye, Point3D lookat, Vector3D up,
			float exposureTime, double d, double zoom) {
		super(eye, lookat, up, exposureTime);
		this.d = d;
		this.zoom = zoom;
	}

	public Vector3D getDirection(Point2D p) {
		Vector3D temp1 = u.multiply(p.x);
		Vector3D temp2 = v.multiply(p.y);
		Vector3D temp3 = w.multiply(-d);
		Vector3D dir = (temp1.add(temp2)).add(temp3).hat();
		return(dir);
	}

	public void renderScene(World w) {
		RGBColor	L;
		ViewPlane	vp = new ViewPlane(w.vp);	 								
		int n = (int)Math.sqrt((float)vp.numSamples);
			
		vp.s /= zoom;
			
		for (int row = 0; row < vp.vres; row++) {			// up
			for (int column = 0; column < vp.hres; column++) {		// across 					
				L = (RGBColor.BLACK); 
				
				for (int p = 0; p < n; p++)	 {		// up pixel
					for (int q = 0; q < n; q++) {	// across pixel
						double x = vp.s * (column - 0.5 * vp.hres + (q + 0.5) / n); 
						double y = vp.s * (row - 0.5 * vp.vres + (p + 0.5) / n);
						Vector3D rayDirection = getDirection(new Point2D(x, y));
						L = L.add(w.tracer.traceRay(new Ray(eye, rayDirection)));
					}	
				}
												
				L = L.divide(vp.numSamples);
				L = L.multiply(exposureTime);
				w.displayPixel(row, column, L);
			} 
		}
	}
}
