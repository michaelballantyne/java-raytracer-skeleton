package raytracer.utilities;

//Copyright (C) Helen Hu 2011.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.


public class RGBColor {
	public float	r, g, b;	
	public static final RGBColor BLACK = new RGBColor(0f);
	public static final RGBColor WHITE = new RGBColor(1f);
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
	
	// -------------------------------------------------------- default constructor
	/**
	 * default constructor sets the color 
	 */
	public RGBColor() {
		r = g = b = 0f;    // black
	}


	// -------------------------------------------------------- constructor

	/**
	 * constructor sets the color to a grey scale value (with intensity c)
	 * @param grey is grey scale intensity 
	 */
	public RGBColor(float grey) {
		r = g = b = grey;
	}
									

	// -------------------------------------------------------- constructor

	public RGBColor(float newR, float newG, float newB)	{
		r = newR;
		g = newG;
		b = newB;
	}
	
	public RGBColor(double newR, double newG, double newB)	{
		r = (float)newR;
		g = (float)newG;
		b = (float)newB;
	}

	// -------------------------------------------------------- copy constructor

	public RGBColor(RGBColor c){
		r = c.r;
		g = c.g;
		b = c.b;
	}
		

	// --------------------------------------------------------assignment operator

	public void set (RGBColor rhs) {
		if (this != rhs) {
			r = rhs.r; g = rhs.g; b = rhs.b;
		}
	}
	 


	// ----------------------------------------------------------------------- operator+
	// addition of two colors

	public RGBColor add (RGBColor c) {
		return new RGBColor(r + c.r, g + c.g, b + c.b);
	}


	// ----------------------------------------------------------------------- operator+=
	// compound addition of two colors

	public void plusEqual (RGBColor c) {
		r += c.r; g += c.g; b += c.b;
	}


	// ----------------------------------------------------------------------- operator*
	// multiplication by a float on the right

	public RGBColor multiply (float a) {
		return new RGBColor (r * a, g * a, b * a);	
	}
	
	// ----------------------------------------------------------------------- operator*
	// multiplication by a double on the right (to avoid a type-cast)

	public RGBColor multiply (double a) {
		return new RGBColor (r * a, g * a, b * a);	
	}

	// ----------------------------------------------------------------------- operator*=
	// compound multiplication by a float on the right

	public void timesEqual (float a) {
		r *= a; g *= a; b *= a;
	}


	// ----------------------------------------------------------------------- operator/
	// division by float

	public RGBColor divide (float a) {
		return new RGBColor (r / a, g / a, b / a);
	}


	// ----------------------------------------------------------------------- operator/=
	// compound division by float

	public void divideEqual (float a) {	
		r /= a; g /= a; b /= a;
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
		return new RGBColor(Math.pow(r, p), Math.pow(g, p), Math.pow(b, p));
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
