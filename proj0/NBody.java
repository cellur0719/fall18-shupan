public class NBody{

	public static double readRadius(String fileName){
		String img=fileName;
		In in=new In(img);

		int firstItemInFile = in.readInt();
		double secondItemFile = in.readDouble();
		return secondItemFile;
	}

	public static Planet[] readPlanets(String fileName){
		Planet[] planets=new Planet[5];
		planets[0]=new Planet(0,0,0,0,0,"");
		planets[1]=new Planet(0,0,0,0,0,"");
		planets[2]=new Planet(0,0,0,0,0,"");
		planets[3]=new Planet(0,0,0,0,0,"");
		planets[4]=new Planet(0,0,0,0,0,"");
		String img=fileName;
		In in = new In(img);
		int firstItemInFile = in.readInt();
		double secondItemFile = in.readDouble();
		for(int i=0;i<5;i++){
			double xPos=in.readDouble();
			double yPos=in.readDouble();
			double xVel=in.readDouble();
			double yVel=in.readDouble();
			double m=in.readDouble();
			String imageFileName=in.readString();
			planets[i].xxPos=xPos;
			planets[i].yyPos=yPos;
			planets[i].xxVel=xVel;
			planets[i].yyVel=yVel;
			planets[i].mass=m;
			planets[i].imgFileName=imageFileName;
		}
		return planets;
	}

	public static void main(String[] args) {
		StdDraw.enableDoubleBuffering();

		Double[] xForces,yForces;
		Double T,t,dt,radius;
		String filename;
		int i;
		Planet[] planets=new Planet[5];
		String imageToDraw = "./images/starfield.jpg";
		T=Double.parseDouble(args[0]);
		dt=Double.parseDouble(args[1]);
		filename=args[2];
		radius=readRadius(filename);
		planets=readPlanets(filename);
		System.out.println(radius);	
		StdDraw.setScale(-1e12, 1e12);
		StdDraw.setXscale(-1e12/2, 1e12/2);
		StdDraw.setYscale(-1e12/2, 1e12/2);
		
		
		for(i=0;i<5;i++){
			planets[i].draw();
		}

		for(t=0.0;t<T;t=t+dt){
			xForces=new Double[5];
			yForces=new Double[5];
			for (i=0;i<5 ;i++ ) {
				xForces[i]=planets[i].calcNetForceExertedByX(planets);
				yForces[i]=planets[i].calcNetForceExertedByY(planets);
				
			}
			for (i=0;i<5 ;i++ ) {
				planets[i].update(dt,xForces[i],yForces[i]);
			}
			StdDraw.clear();
			StdDraw.picture(0, 0, imageToDraw);
			for (i=0;i<5 ;i++ ) {
				planets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}
		
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            	planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            	planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
		
	}
}