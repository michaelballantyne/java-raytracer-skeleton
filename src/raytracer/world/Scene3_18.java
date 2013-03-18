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

		Sphere sphere = new Sphere();
		sphere.setCenter(0, -25, 0);
		sphere.setRadius(80);
		sphere.setColor(1, 0, 0);  // red
		addObject(sphere);

		// use constructor to set centre and radius 

		sphere = new Sphere(new Point3D(0, 30, 0), 60);
		sphere.setColor(1, 1, 0);	// yellow
		addObject(sphere);

		Plane plane_ptr = new Plane(new Point3D(0), new Normal(0, 1, 1));
		plane_ptr.setColor(0.0f, 0.3f, 0.0f);	// dark green
		addObject(plane_ptr);
	}

}
