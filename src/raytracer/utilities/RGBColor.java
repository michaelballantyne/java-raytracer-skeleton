package raytracer.utilities;

//Copyright (C) Helen Hu 2011.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

/** Opaque sRGB color with the R, G, and B components each represented by a float 
 *  greater than or equal to 0. */
public class RGBColor {
	public final float r, g, b;	
	
	public static final RGBColor BLACK = new RGBColor(0, 0, 0);
	public static final RGBColor WHITE = new RGBColor(1, 1, 1);
	public static final RGBColor RED = new RGBColor(1, 0, 0);
	public static final RGBColor YELLOW = new RGBColor(1, 1, 0);
	public static final RGBColor BROWN = new RGBColor(0, 0, 0.16f);
	public static final RGBColor DARK_GREEN = new RGBColor(0, 0.41f, 0.41f);
	public static final RGBColor ORANGE = new RGBColor(1, 0.75f, 0);
	public static final RGBColor GREEN = new RGBColor(0, 0.6f, 0.3f);
	public static final RGBColor LIGHT_GREEN = new RGBColor(0.65f, 1, 0.30f);
	public static final RGBColor DARK_YELLOW = new RGBColor(0.61f, 0.61f, 0);
	public static final RGBColor LIGHT_PURPLE = new RGBColor(0.65f, 0.3f, 1);
	public static final RGBColor DARK_PURPLE = new RGBColor(0.5f, 0, 1);
	public static final RGBColor CYAN = new RGBColor(0, 0.5f, 0.75f);
	public static final RGBColor BLUE = new RGBColor(0, 0, 1);

	/** r, g, and b should be greater than or equal to 0. */
	public RGBColor(float r, float g, float b)	{
		this.r = r;
		this.g = g;
		this.b = b;
	}

	/** Addition of two colors (component-wise addition of R, G, and B components). */
	public RGBColor add(RGBColor c) {
		return new RGBColor(r + c.r, g + c.g, b + c.b);
	}
	/** Multiplication by a scalar. */
	public RGBColor multiply(float a) {
		return new RGBColor(r * a, g * a, b * a);	
	}

	/** Multiplication by a scalar. */
	public RGBColor divide(float a) {
		return new RGBColor(r / a, g / a, b / a);
	}

	/** Component-wise multiplication of two colors. */
	public RGBColor multiply(RGBColor c)  {
		return new RGBColor (r * c.r, g * c.g, b * c.b);
	} 

	/** Average of the R, G, and B components. */
	public float average() {
		return (0.333333333333f * (r + g + b));
	}

	/** Raise each component to the specified power. Used for color filtering in Chapter 28. */
	public RGBColor powc(float p)  {
		return new RGBColor((float) Math.pow(r, p), (float) Math.pow(g, p), (float) Math.pow(b, p));
	}

	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			RGBColor other = (RGBColor)obj;
			return (r==other.r && g==other.g && b==other.b);
		}
	}
	
	public String toString() {
		return "RGBColor: (" + r + "," + g + "," + b + ")";
	}
}
