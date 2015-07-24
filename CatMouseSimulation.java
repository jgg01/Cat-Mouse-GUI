/** CatMouseSimulation.fw.java supplies empty main and runChase methods for you. The main method should retrieve initial position values for the cat and the mouse from the user and initialize a cat and a mouse appropriately. It then calls runChase to run the simulation to completion. */

public class CatMouseSimulation {

    /** Count of time steps the program has taken. */
    protected static int count = 0;
    /** Tests if mouse is eaten or not. */
    protected static boolean eaten = false;

	protected static double[] coodXY (String position) {
		String shit = position.replaceAll(".*\\(|\\).*", "");
		String beforeComma = shit.split("\\,")[0];
		String afterComma = shit.split("\\,")[1];
		double parseX = Double.parseDouble(beforeComma);
		double parseY = Double.parseDouble(afterComma);
		double[] xy = new double[2];
		xy[0] = parseX; xy[1] = parseY;
		return xy;
	}

    /** Tracks and returns the status of the simulation. */
    protected static String status() {
        if (eaten) {
            return "eaten";
        } else if (count == 0) {
            return "init";
        } else if (count < 30) {
            return "running";
	} else if (count == 30) {
		return "its 30 steps, bored.";
        } else {
            return "nope.";
        }
    }

	/** Changes degree of angle appropriately. */
	protected static double changeDegree(double angle) {
		if (angle > 360) {
			angle = angle - 360;
		} else if (angle < 0) {
			angle = 360 + angle;
		} return angle;
	}

    /** Initializes the program with the args, creating the cat and mouse.
     * Also calls runChase method. */
    public static void initialize(double[] args) {
	double cA = changeDegree(args[3]);
	double mA = changeDegree(args[4]);
	changeDegree(cA);
	if (args[4] > 360.0) {
            eaten = true;
        }

	pixelsPerMeter(args[0]);
	millisecondsPerSimStep(args[1]);

        Position cat = new Position(args[2], (Math.PI/180)*(cA%360));
        Position mouse = new Position(1.0, (Math.PI/180)*(mA%360));
        Mouse m = new Mouse(mouse);
        Cat c = new Cat(cat);
        runChase(c, m);
    }

    /** Checks if cat is bored yet or not. */
    public static boolean checkBored() {
        if (status().equals("nope") || status().equals("its 30 steps, bored.")) {
		System.out.print("true");
            return true;
        } return false;
    }       
                

    /** Runs the simulation from given cat and mouse. */
    public static void runChase(Cat cat, Mouse mouse) {

	String mouPos = mouse.getPosition().toString();
	String catPos = cat.getPosition().toString();
	double[] mouXY = coodXY(mouPos);
	double[] catXY = coodXY(catPos);

	//update coordinates

        String printTable = (count +"	" + status() + "	" + 
		mouPos + "	" + catPos);
	if (!eaten && !checkBored()) { 
		moveStep(mouXY[0], mouXY[1], catXY[0], catXY[1]);
		System.out.println(printTable);
		// Move one STEP
	}
	if (checkBored()) { 
		if (!cat.move(mouse.getPosition())) {
			System.out.println(printTable);
			moveStep(mouXY[0], mouXY[1], catXY[0], catXY[1]);
			//Move one STEP?
		} else {
			eaten = true;
		}
	}
        else if (eaten || cat.move(mouse.getPosition())) {
            count +=1; eaten = true;
            cat = new Cat( cat.getPosition());
            mouse = new Mouse( mouse.getPosition() );
            printTable = (count +"	" + "eaten" + "	" +
		mouPos + "	" + catPos);
            System.out.println(printTable);
	    moveStep(mouXY[0], mouXY[1], catXY[0], catXY[1]);
		//Move one STEP
        } else if (!checkBored()) {
            count += 1;
            mouse.move();
            cat = new Cat( cat.getPosition() );
            mouse = new Mouse( mouse.getPosition() );
            runChase(cat, mouse);
		//recurse to end
        }
        
    }

	protected static void moveStep (double mX, double mY, double cX, double cY) {
		GUI.moveSim(mX, mY, cX, cY);
	}

    /** Set up the arguments and then call runChase to run the simulation. */
    public static void main(String [] args) {
        if (args.length == 0) {
            System.out.println("Usage: java CatMouseSimulation <pixelsPerMeter>" +
		"<millisecondsPerSimStep> <catRadius> <catAngle> <mouseAngle>");
            System.exit(0);
        } else {
            System.out.println("Welcome to the CS9G cat and mouse simulation!" +
		"\nTIME STATUS  MOUSE   CAT");

            double[] a = new double[5];
            for (int i = 0; i < 5; i++) {
                a[i] = Double.parseDouble(args[i]);
            }

            initialize(a);
            System.exit(0);
        }
    }

	/** Determines how many milliseconds of actual time pass between
		simulation time steps, which are "minutes" here. */
	protected static void millisecondsPerSimStep(double m) {
	}

	/** Scale of your simulation. The radius of the statue is 1 meter. 
		Given this and the catRadius, you should create a drawing panel
		that fits all of your elements comfortably on the screen 
		at time = 0. */
	protected static void pixelsPerMeter(double p) {
	}

}

