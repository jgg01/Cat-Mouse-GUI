import java.lang.Math.*;

/** Cat.fw.java and Mouse.java define the Cat and Mouse classes, respectively. Both have a move method and constructors. Neither class is allowed direct access to the components of a Position. The complete Mouse class is provided for you; you must provide the move method of the Cat class. The Mouse move method doesnt return anything. The Cat move method returns true if the cat eats the mouse during the move and returns false otherwise. */

public class Cat {
    
    /** Cat constructor with inital Position. */
    public Cat ( ) {
        myPosition = new Position ( );
    }
    
    /** Cat consturctor with initial Position p. */
    public Cat (Position p) {
        myPosition = p;
    }
    
    // An access function.
    public Position getPosition ( ) {
        return myPosition;
    }
    
    // Move the cat around the statue:
    //  one meter toward the statue if the cat sees the mouse (or up to the statue
    //          if the cat is closer to the statue than one meter away), or 
    //  1.25 meters counterclockwise around the statue if the cat doesn't see the mouse.
    // Return true if the cat eats the mouse during the move, false otherwise.
    public boolean move (Position mousePosition) {
        double mR = mousePosition.myRadius;
        double mA = mousePosition.myAngle;
        double cR = myPosition.myRadius;
        double cA = myPosition.myAngle;
        double oldCA = cA;

        if (cR == 1.0 && (cA < mA & cA+((2*Math.PI)*(1.25/(2 * Math.PI * cR))) > mA)) {
            myPosition.update(0, (2 * Math.PI)*(1.25/(2 * Math.PI * cR)));
            return true;
        } else if (cR > 1 & (cR * Math.cos(cA - mA) >= 1)) {
            myPosition.update(-1, cA);
            return false;
        } else {
            myPosition.update(0, (2*Math.PI)*(1.25/(2 * Math.PI * cR)));
            return false;
        }
    }
    
    /** Stores the position of this cat. */
    private Position myPosition;
}
