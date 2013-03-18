package raytracer.utilities;

//Copyright (C) Helen Hu 2011.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.


public class RGBColor {
	public final float r, g, b;	
	
	public static final RGBColor BLACK = new RGBColor(0, 0, 0);
	public static final RGBColor WHITE = new RGBColor(1, 1, 1);
	public static final RGBColor RED = new RGBColor(1.0f, 0.0f, 0.0f);
	public static final RGBColor YELLOW = new RGBColor(1f, 1f, 0f);										// yellow
	public static final RGBColor BROWN = new RGBColor(0.71f, 0.40f, 0.16f);								// brown
	public static final RGBColor DARK_GREEN = new RGBColor(0.0f, 0.41f, 0.41f);							// dark_green
	public static final RGBColor ORANGE = new RGBColor(1f, 0.75f, 0f);									// orange
	public static final RGBColor GREEN = new RGBColor(0f, 0.6f, 0.3f);									// green
	public static final RGBColor LIGHT_GREEN = new RGBColor(0.65f, 1f, 0.30f);							// light green
	public static final RGBColor DARK_YELLOW = new RGBColor(0.61f, 0.61f, 0f);							// dark yellow
	public static final RGBColor LIGHT_PURPLE = new RGBColor(0.65f, 0.3f, 1f);							// light purple
	public static final RGBColor DARK_PURPLE = new RGBColor(0.5f, 0f, 1f);								// dark purple
	public static final RGBColor CYAN = new RGBColor(0f, 0.5f, 0.75f);									// cyan
	public static final RGBColor BLUE = new RGBColor(0f, 0f, 1f);										// blue


	public RGBColor(float newR, float newG, float newB)	{
		r = newR;
		g = newG;
		b = newB;
	}

	// ----------------------------------------------------------------------- operator+
	// addition of two colors

	public RGBColor add (RGBColor c) {
		return new RGBColor(r + c.r, g + c.g, b + c.b);
	}

	// ----------------------------------------------------------------------- operator*
	// multiplication by a float on the right

	public RGBColor multiply (float a) {
		return new RGBColor (r * a, g * a, b * a);	
	}

	// ----------------------------------------------------------------------- operator/
	// division by float

	public RGBColor divide (float a) {
		return new RGBColor (r / a, g / a, b / a);
	}

	// ----------------------------------------------------------------------- operator*
	// component-wise multiplication of two colors

	public RGBColor multiply (RGBColor c)  {
		return new RGBColor (r * c.r, g * c.g, b * c.b);
	} 


	// ----------------------------------------------------------------------- operator==
	// are two RGBColors the same?

	public boolean equal (Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			RGBColor c = (RGBColor)obj;
			return (r == c.r && g == c.g && b == c.b);
		}	
	}


	// ----------------------------------------------------------------------- average
	// the average of the three components

	public float average() {
		return (0.333333333333f * (r + g + b));
	}


	// -------------------------------------------------------- powc
	// raise each component to the specified power
	// used for color filtering in Chapter 28

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
	
	// static method
	// inlined non-member function

	// ----------------------------------------------------------------------- operator*
	// multiplication by a float on the left

	public static RGBColor multiply (float a, RGBColor c) {
		return new RGBColor (a * c.r, a * c.g, a * c.b);	
	}


}
