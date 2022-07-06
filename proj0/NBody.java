public class NBody {

	public static double readRadius(String path) {
		In in = new In(path);
		in.readDouble();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet [] readPlanets(String path) {
		In in = new In(path);
		int universeSize = in.readInt();
		in.readDouble();
		int i = 0;
		Planet [] planets = new Planet [universeSize];
		while(!in.isEmpty()) {
			if (i == universeSize) break;
			double xPos = in.readDouble();
			double yPos = in.readDouble();
			double xVel = in.readDouble();
			double yVel = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();
			planets[i] = new Planet(xPos, yPos, xVel, yVel, mass, img);
			i++;
		}
		return planets;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double universeRadius = NBody.readRadius(filename);
		Planet [] planets = NBody.readPlanets(filename);

		/* Draws the background with starfield.jpg */
		StdDraw.setScale(-universeRadius, universeRadius);

		// StdDraw.clear();
		// StdDraw.picture(0,0,"images/starfield.jpg");
		// StdDraw.show();
		// StdDraw.pause(2000);
		// for(Planet p : planets) {
		// 	p.draw();
		// }

		/* Creates an animation */

		StdDraw.enableDoubleBuffering();

		double timer = 0;
		while (timer < T) {

			double [] xForces = new double [planets.length];
			double [] yForces = new double [planets.length];

			for (int i = 0; i < planets.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}

			for(int i = 0; i < planets.length; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0,0,"images/starfield.jpg");

			for(Planet p : planets) {
				p.draw();
			}

			StdDraw.show();
			StdDraw.pause(10);
			StdDraw.clear();

			timer += dt;

		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
		                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}

	}

}