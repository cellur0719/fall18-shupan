public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static double G=6.67e-11f;

	public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}

	public Planet(Planet p){
		xxPos=p.xxPos;
		yyPos=p.yyPos;
		xxVel=p.xxVel;
		yyVel=p.yyVel;
		mass=p.mass;
		imgFileName=p.imgFileName;
	}

	public double calcDistance(Planet p){
		double r_square;
		double r;
		if(this.equals(p)){
			r=1e20f;
		}
		else{
			r_square=(this.xxPos-p.xxPos)*(this.xxPos-p.xxPos)+(this.yyPos-p.yyPos)*(this.yyPos-p.yyPos);
			r=Math.sqrt(r_square);
		}

		return r;
	}

	public double calcForceExertedBy(Planet p){
		double f;
		if(this.equals(p)){
			f=0;
		}else{
			f=G*this.mass*p.mass/(this.calcDistance(p)*this.calcDistance(p));
		}
		return f;
	}

	public double calcForceExertedByX(Planet p){
		double xForce;
		xForce=(calcForceExertedBy(p)*(p.xxPos-this.xxPos))/calcDistance(p);
		return xForce;
	}

	public double calcForceExertedByY(Planet p){
		double yForce;
		yForce=(calcForceExertedBy(p)*(p.yyPos-this.yyPos))/calcDistance(p);
		return yForce;
	}

	public double calcNetForceExertedByX(Planet[] pArray){
		double xNetForce=0;
		for(int i=0;i<pArray.length;i++){
			xNetForce=xNetForce+this.calcForceExertedByX(pArray[i]);
		}
		return xNetForce;
	}

	public double calcNetForceExertedByY(Planet[] pArray){
		double yNetForce=0;
		for(int i=0;i<pArray.length;i++){
			yNetForce=yNetForce+this.calcForceExertedByY(pArray[i]);
		}
		return yNetForce;
	}

	public Planet update(double dt,double fX,double fY){
		xxVel=xxVel+(fX/this.mass)*dt;
		yyVel=yyVel+(fY/this.mass)*dt;
		xxPos=xxPos+xxVel*dt;
		yyPos=yyPos+yyVel*dt;
		return this;
	}

	public void draw(){
		String image="./images/" + this.imgFileName;
		StdDraw.picture(xxPos, yyPos, image);
	}
}