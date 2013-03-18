package raytracer.world;

import raytracer.geometricObjects.Plane;
import raytracer.geometricObjects.Sphere;
import raytracer.tracers.MultipleObjects;
import raytracer.utilities.Normal;
import raytracer.utilities.Point3D;
import raytracer.utilities.RGBColor;

public class Scene3_18 extends World {

	@Override
	protected void buildScene() {
		// Listing 3.18 on page 77
		// creates scene shown in Figure 3.20
		tracer = new MultipleObjects(this); 

		backgroundColor = RGBColor.BLACK;

		// use access functions to set centre and radius

		Sphere sphere = new Sphere(new Point3D(0, -25, 0), 80, RGBColor.RED);
		addObject(sphere);

		// use constructor to set centre and radius 

		sphere = new Sphere(new Point3D(0, 30, 0), 60, RGBColor.YELLOW);
		addObject(sphere);

		Plane plane_ptr = new Plane(new Point3D(0, 0, 0), new Normal(0, 1, 1), new RGBColor(0.0f, 0.3f, 0.0f));
		addObject(plane_ptr);
	}

}
