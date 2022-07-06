public class Planet {
	
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		double r =  Math.sqrt((xxPos - p.xxPos) * (xxPos - p.xxPos) + (yyPos - p.yyPos) * (yyPos - p.yyPos));
		return r;
	}

	public double calcForceExertedBy(Planet p) {
		double distance = calcDistance(p);
		double force = (G * mass * p.mass) / (distance * distance);
		return force;
			
		}

	public double calcForceExertedByX(Planet p) {
		double force = calcForceExertedBy(p);
		double distance = calcDistance(p);
		return force * (p.xxPos - xxPos) / distance;
	}

	public double calcForceExertedByY(Planet p) {
		double force = calcForceExertedBy(p);
		double distance = calcDistance(p);
		return force * (p.yyPos - yyPos) / distance;
	}

	public double calcNetForceExertedByX(Planet[] planets) {
		double netXForce = 0;
		for(Planet p : planets){
			if(!this.equals(p)){
				netXForce += calcForceExertedByX(p);
			}
		}
		return netXForce;
	}

	public double calcNetForceExertedByY(Planet[] planets) {
		double netYForce = 0;
		for(Planet p : planets ){
			if(!this.equals(p)){
				netYForce += calcForceExertedByY(p);
			}
		}
		return netYForce;
	}

	public void update(double dt, double fX, double fY) {
		double aX = fX / mass;
		double aY = fY / mass;
		xxVel += aX * dt;
		yyVel += aY * dt;
		xxPos += xxVel * dt;
		yyPos += yyVel * dt;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName );
	}
}

