

/**
 * Celestial Body class for NBody
 * Modified from original Planet class
 * used at Princeton and Berkeley
 * @author ola
 *
 * If you add code here, add yourself as @author below
 *
 *
 */
public class CelestialBody {

	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;

	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	public CelestialBody(double xp, double yp, double xv, double yv, double mass, String filename){
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;

	}

	/**
	 *
	 * @return
	 */
	public double getX() {
		return myXPos;
	}

	/**
	 *
	 * @return
	 */
	public double getY() {
		return myYPos;
	}

	/**
	 * Accessor for the x-velocity
	 * @return the value of this object's x-velocity
	 */
	public double getXVel() {
		return myXVel;
	}
	/**
	 * Return y-velocity of this Body.
	 * @return value of y-velocity.
	 */
	public double getYVel() {
		return myYVel;
	}

	/**
	 *
	 * @return
	 */
	public double getMass() {
		return myMass;
	}

	/**
	 *
	 * @return
	 */
	public String getName() {
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		double dist = (this.myXPos - b.myXPos) * (this.myXPos - b.myXPos) + (this.myYPos - b.myYPos) * (this.myYPos - b.myYPos);
		return Math.sqrt(dist);
	}

	public double calcForceExertedBy(CelestialBody b) {
		double G = 6.67*1e-11;
		double m1 = this.myMass;
		double m2 = b.myMass;
		double rsquared = (calcDistance(b) * calcDistance(b));
		double force = (G*m1*m2)/rsquared;
		return force;
	}

	public double calcForceExertedByX(CelestialBody b) {
		double forceX = (calcForceExertedBy(b) * (b.myXPos - this.myXPos)) / (calcDistance(b));
		return forceX;
	}
	public double calcForceExertedByY(CelestialBody b) {
		double forceY = (calcForceExertedBy(b) * (b.myYPos - this.myYPos)) / (calcDistance(b));
		return forceY;
	}

	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		double sum = 0.0;
		for(CelestialBody b : bodies) {
			if (! b.equals(this)) {
				sum += calcForceExertedByX(b);
			}
		}
		return sum;
	}

	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		double sum = 0.0;
		for(CelestialBody b : bodies) {
			if (! b.equals(this)) {
				sum += calcForceExertedByY(b);
			}
		}
		return sum;
	}

	public void update(double deltaT, double xforce, double yforce) {
		double ax = xforce/myMass;
		double ay = yforce/myMass;
		double nvx = myXVel + deltaT*ax;
		double nvy = myYVel + deltaT*ay;
		double nx = myXPos + deltaT*nvx;
		double ny = myYPos + deltaT*nvy;

		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;

	}

	/**
	 * Draws this planet's image at its current position
	 */
	public void draw() {
		StdDraw.picture(myXPos,myYPos,"images/"+myFileName);
	}
}
