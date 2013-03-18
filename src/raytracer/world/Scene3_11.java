package raytracer.world;

import raytracer.geometricObjects.Sphere;
import raytracer.tracers.SingleSphere;
import raytracer.utilities.Point3D;
import raytracer.utilities.RGBColor;

public class Scene3_11 extends World {

	@Override
	protected void buildScene() {
		// Listing 3.11 on page 68
		// creates scene shown in Figure 3.18 (a single sphere)
		backgroundColor = RGBColor.BLACK;
		tracer = new SingleSphere(this); 

		sphere = new Sphere(new Point3D(0, 0, 0), 85.0, RGBColor.RED);		
	}
}
