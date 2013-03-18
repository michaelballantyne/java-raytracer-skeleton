package raytracer.world;

//Copyright (C) Helen Hu 2013.
//based on C++ code from Ray Tracing from the Ground Up, by Kevin Suffern 
//This Java code is for non-commercial purposes only.
//This Java code is licensed under the GNU General Public License Version 2.
//See the file COPYING.txt for the full license.

public class ViewPlane {
	public int 			hres;   					// horizontal image resolution 
	public int 			vres;   					// vertical image resolution
	public double		s;							// pixel size
	public int			numSamples;					// number of samples per pixel
	public int			maxDepth;
	
	public float		gamma;						// gamma correction factor
	public float		invGamma;					// the inverse of the gamma correction factor
	public boolean		showOutOfGamut;				// display red if RGBColor out of gamut


	// ---------------------------------------------------------------- default constructor	

	public ViewPlane() {							
		hres = 400;
		vres = 400;
		s = 1.0;
		numSamples = 1;
		gamma = 1f;
		invGamma = 1f;
		showOutOfGamut = false;
	}

	// ---------------------------------------------------------------- copy constructor

	public ViewPlane(ViewPlane vp){
		hres = vp.hres;
		vres = vp.vres;
		s = vp.s;
		numSamples = vp.numSamples;
		gamma = vp.gamma;
		invGamma = vp.invGamma;
		showOutOfGamut = vp.showOutOfGamut;
	}

	// ---------------------------------------------------------------- assignment operator

	public void set (ViewPlane rhs) {
		if (this != rhs) {			
			hres 				= rhs.hres;
			vres 				= rhs.vres;
			s					= rhs.s;
			numSamples 			= rhs.numSamples;
			gamma				= rhs.gamma;
			invGamma			= rhs.invGamma;
			showOutOfGamut		= rhs.showOutOfGamut;
		}
	}

	// ------------------------------------------------------------------------------ set_hres

	public void setHres(int hRes) {
		hres = hRes;
	}

	// ------------------------------------------------------------------------------ set_vres

	public void setVres(int vRes) {
		vres = vRes;
	}

	// ------------------------------------------------------------------------------ set_pixel_size

	public void setPixelSize(double size) {
		s = size;
	}


	// ------------------------------------------------------------------------------ set_gamma

	public void setGamma(float g) {
		gamma = g;
		invGamma = 1.0f / gamma;
	}

	// ------------------------------------------------------------------------------ set_gamut_display

	public void setGamutDisplay(boolean show) {
		showOutOfGamut = show;
	}

	// ------------------------------------------------------------------------------ set_samples

	public void setSamples(int n) {
		numSamples = n;
	}

	public boolean equals(Object obj) {
		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}
		else {
			ViewPlane vp = (ViewPlane)obj;
			return (hres == vp.hres && 
					vres == vp.vres &&
					s == vp.s &&
					numSamples == vp.numSamples &&
					gamma == vp.gamma &&
					invGamma == vp.invGamma &&
					showOutOfGamut == vp.showOutOfGamut);
		}
	}

	public String toString() {
		return "View Plane: " + hres + " x " + vres + ", " +
//		num_samples + " samples, " +
		"pixel size" + s + "(gamma = " + gamma + ")";
	}
}
