/**
 * @author YOUR NAME THE STUDENT IN 201
 * 
 * Simulation program for the NBody assignment
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class NBody {
	
	/**
	 * Read the specified file and return the radius
	 * @param fname is name of file that can be open
	 * @return the radius stored in the file
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static double readRadius(String fname) throws FileNotFoundException  {
		Scanner s = new Scanner(new File(fname));
	
		// TODO: read values at beginning of file to
		// find the radius

		double n = s.nextDouble();
		double rad = s.nextDouble(); //radius

		s.close();

		return rad;
	}
	
	/**
	 * Read all data in file, return array of Celestial Bodies
	 * read by creating an array of Body objects from data read.
	 * @param fname is name of file that can be open
	 * @return array of Body objects read
	 * @throws FileNotFoundException if fname cannot be open
	 */
	public static CelestialBody[] readBodies(String fname) throws FileNotFoundException {

		Scanner s = new Scanner(new File(fname));
			
		// TODO: read # bodies, store in nb

		int nb = s.nextInt();          // # bodies to be read

		// TODO: Create array that can store nb CelestialBodies

		CelestialBody[] arr = new CelestialBody[nb];

		// TODO: read and ignore radius
		s.nextLine();
		s.nextLine();

		// TODO: read data for each body
		// TODO: construct new body object and add to array


		for(int k=0; k < nb; k++) {
			double xp = s.nextDouble();
			double yp = s.nextDouble();
			double xv = s.nextDouble();
			double yv = s.nextDouble();
			double mass = s.nextDouble();
			String filename = s.next();
			//CelestialBody b = new CelestialBody(xp, yp, xv, yv, mass, filename);
			//arr[k] = b;
			//int n = s.nextInt();
			//arr[k] = n;
			//System.out.println(arr);
			//int info = s.nextLine();
			//String[] rayray = info.split("");
			//System.out.println("hi");
			//double xp = Double.parseDouble(info[0]);
			//double yp = Double.parseDouble(info[1]);
			//double xv = Double.parseDouble(info[2]);
			//double yv = Double.parseDouble(info[3]);
			//double mass = Double.parseDouble(info[4]);
			//String filename = info[5];
			CelestialBody b = new CelestialBody(xp, yp, xv, yv, mass, filename);
			arr[k] = b;

		}

		s.close();

		// TODO: return array of body objects read
		return arr;
	}
	public static void main(String[] args) throws FileNotFoundException{
		double totalTime = 39447000.0;
		double dt = 25000.0;

		String fname= "./data/planets.txt";

		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			fname = args[2];
		}	
		
		CelestialBody[] bodies = readBodies(fname);
		double radius = readRadius(fname);

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0,0,"images/starfield.jpg");

		// run simulation until over

		for(double t = 0.0; t < totalTime; t += dt) {
			
			// TODO: create double arrays xforces and yforces
			//       to hold forces on each body
			double[] xforces = new double[bodies.length];
			double[] yforces = new double[bodies.length];


			// TODO: in loop, calculate netForcesX and netForcesY and store in
			//       arrays xforces and yforces for each object in bodies

			for(int k=0; k < bodies.length; k++) {
								yforces[k] = bodies[k].calcNetForceExertedByY(bodies);
								xforces[k] = bodies[k].calcNetForceExertedByX(bodies);

  			}

			// TODO: loop over all bodies and call update
			//       with dt and corresponding xforces and yforces arrays

			for(int k=0; k < bodies.length; k++){
				bodies[k].update(dt, xforces[k], yforces[k]); //done
			}

			StdDraw.clear();
			StdDraw.picture(0,0,"images/starfield.jpg");
			
			// TODO: loop over all bodies and call draw on each one

			for(CelestialBody b : bodies){
				b.draw(); //done
			}
			StdDraw.show();
			StdDraw.pause(10);

		}
		
		// prints final values after simulation
		
		System.out.printf("%d\n", bodies.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              bodies[i].getX(), bodies[i].getY(), 
		                      bodies[i].getXVel(), bodies[i].getYVel(), 
		                      bodies[i].getMass(), bodies[i].getName());	
		}
	}
}
