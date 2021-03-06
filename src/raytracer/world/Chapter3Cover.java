package raytracer.world;

import raytracer.geometricObjects.Sphere;
import raytracer.tracers.MultipleObjects;
import raytracer.utilities.Point3D;
import raytracer.utilities.RGBColor;

public class Chapter3Cover extends World {

	@Override
	protected void buildScene() {
		// This builds the cover image for Chapter 3: Bare Bones Ray Tracing (page 45)
		tracer = new MultipleObjects(this);  

		// spheres

		Sphere	sphere1 = new Sphere(new Point3D(5, 3, 0), 30, RGBColor.YELLOW);	   								// yellow
		addObject(sphere1);

		Sphere	sphere2 = new Sphere(new Point3D(45, -7, -60), 20, RGBColor.BROWN);									// brown
		addObject(sphere2);

		Sphere	sphere3 = new Sphere(new Point3D(40, 43, -100), 17, RGBColor.DARK_GREEN);								// dark green
		addObject(sphere3);

		Sphere	sphere4 = new Sphere(new Point3D(-20, 28, -15), 20, RGBColor.ORANGE);									// orange
		addObject(sphere4);

		Sphere	sphere5 = new Sphere(new Point3D(-25, -7, -35), 27, RGBColor.GREEN);									// green
		addObject(sphere5);

		Sphere	sphere6 = new Sphere(new Point3D(20, -27, -35), 25, RGBColor.LIGHT_GREEN);								// light green
		addObject(sphere6);

		Sphere	sphere7 = new Sphere(new Point3D(35, 18, -35), 22, RGBColor.GREEN);   								// green
		addObject(sphere7);

		Sphere	sphere8 = new Sphere(new Point3D(-57, -17, -50), 15, RGBColor.BROWN);									// brown
		addObject(sphere8);

		Sphere	sphere9 = new Sphere(new Point3D(-47, 16, -80), 23, RGBColor.LIGHT_GREEN);							// light green
		addObject(sphere9);

		Sphere	sphere10 = new Sphere(new Point3D(-15, -32, -60), 22, RGBColor.DARK_GREEN);     						// dark green
		addObject(sphere10);

		Sphere	sphere11 = new Sphere(new Point3D(-35, -37, -80), 22, RGBColor.DARK_YELLOW);							// dark yellow
		addObject(sphere11);

		Sphere	sphere12 = new Sphere(new Point3D(10, 43, -80), 22, RGBColor.DARK_YELLOW);							// dark yellow
		addObject(sphere12);

		Sphere	sphere13 = new Sphere(new Point3D(30, -7, -80), 10, RGBColor.DARK_YELLOW);
		addObject(sphere13);												// dark yellow (hidden)

		Sphere	sphere14 = new Sphere(new Point3D(-40, 48, -110), 18, RGBColor.DARK_GREEN); 							// dark green
		addObject(sphere14);

		Sphere	sphere15 = new Sphere(new Point3D(-10, 53, -120), 18, RGBColor.BROWN); 								// brown
		addObject(sphere15);

		Sphere	sphere16 = new Sphere(new Point3D(-55, -52, -100), 10, RGBColor.LIGHT_PURPLE);							// light purple
		addObject(sphere16);

		Sphere	sphere17 = new Sphere(new Point3D(5, -52, -100), 15, RGBColor.BROWN);									// browm
		addObject(sphere17);

		Sphere	sphere18 = new Sphere(new Point3D(-20, -57, -120), 15, RGBColor.DARK_PURPLE);							// dark purple
		addObject(sphere18);

		Sphere	sphere19 = new Sphere(new Point3D(55, -27, -100), 17, RGBColor.DARK_GREEN);							// dark green
		addObject(sphere19);

		Sphere	sphere20 = new Sphere(new Point3D(50, -47, -120), 15, RGBColor.BROWN);									// browm
		addObject(sphere20);

		Sphere	sphere21 = new Sphere(new Point3D(70, -42, -150), 10, RGBColor.LIGHT_PURPLE);							// light purple
		addObject(sphere21);

		Sphere	sphere22 = new Sphere(new Point3D(5, 73, -130), 12, RGBColor.LIGHT_PURPLE);							// light purple
		addObject(sphere22);

		Sphere	sphere23 = new Sphere(new Point3D(66, 21, -130), 13, RGBColor.DARK_PURPLE);							// dark purple
		addObject(sphere23);	

		Sphere	sphere24 = new Sphere(new Point3D(72, -12, -140), 12, RGBColor.LIGHT_PURPLE);							// light purple
		addObject(sphere24);

		Sphere	sphere25 = new Sphere(new Point3D(64, 5, -160), 11, RGBColor.GREEN);					 				// green
		addObject(sphere25);

		Sphere	sphere26 = new Sphere(new Point3D(55, 38, -160), 12, RGBColor.LIGHT_PURPLE);							// light purple
		addObject(sphere26);

		Sphere	sphere27 = new Sphere(new Point3D(-73, -2, -160), 12, RGBColor.LIGHT_PURPLE);							// light purple
		addObject(sphere27);

		Sphere	sphere28 = new Sphere(new Point3D(30, -62, -140), 15, RGBColor.DARK_PURPLE); 							// dark purple
		addObject(sphere28);

		Sphere	sphere29 = new Sphere(new Point3D(25, 63, -140), 15, RGBColor.DARK_PURPLE);							// dark purple
		addObject(sphere29);

		Sphere	sphere30 = new Sphere(new Point3D(-60, 46, -140), 15, RGBColor.DARK_PURPLE); 							// dark purple
		addObject(sphere30);

		Sphere	sphere31 = new Sphere(new Point3D(-30, 68, -130), 12, RGBColor.LIGHT_PURPLE); 							// light purple
		addObject(sphere31);

		Sphere	sphere32 = new Sphere(new Point3D(58, 56, -180), 11, RGBColor.GREEN);									//  green
		addObject(sphere32);

		Sphere	sphere33 = new Sphere(new Point3D(-63, -39, -180), 11, RGBColor.GREEN);									// green 
		addObject(sphere33);

		Sphere	sphere34 = new Sphere(new Point3D(46, 68, -200), 10, RGBColor.LIGHT_PURPLE);							// light purple
		addObject(sphere34);

		Sphere	sphere35 = new Sphere(new Point3D(-3, -72, -130), 12, RGBColor.LIGHT_PURPLE);							// light purple
		addObject(sphere35);
	}

}
