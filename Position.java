/** Position.fw.java defines the Position class. Positions are represented in polar coordinates. 
 * Each Position object consists of a double variable myRadius, and a double variable myAngle; 
 * both are private, and the Position class should not provide direct access to them. 
 * Methods provided for you in the Position class are several constructors, 
 * and toString (to allow positions to be printed). 
 * You are to supply an update method to increment the positions radius or angle. 
 * You will also need several comparison methods to allow a user to determine 
 * if a position is at the base of the statue, if it is at the same coordinate as another position, 
 * if its angle is between two other angles, and 
 * if a position on the base of the statue is in its line of sight. 
 * You will need a separate class, PositionTestDrive, to test the Position class. Write this yourself.
 */

public class Position {
    
    // Represent a position (radius, angle) in polar coordinates.
    // All angles are in radians.
    // The internal representation of an angle is always at least 0
    // and less than 2 * PI.  Also, the radius is always at least 1.
    
    // Constructors.
    public Position ( ) {
        myRadius = 0;
        myAngle = 0;
    }
    
    public Position (Position p) {
        myRadius = p.myRadius;
        myAngle = p.myAngle;
    }
    
    public Position (double r, double theta) {
        myRadius = r;
        myAngle = theta;
    }
    
    // Return a printable version of the position.
    public String toString ( ) {
        return "(" + myRadius + "," + myAngle + ")";
    }
    
    // Update the current position according to the given increments.
    // Preconditions: thetaChange is less than 2 * PI and greater than -2 * PI;
    //   one of rChange and thetaChange is 0.
    public void update (double rChange, double thetaChange) {
        myRadius = rChange + myRadius;
        if (myRadius < 1) {
            myRadius = 1;
        } myAngle = (thetaChange + myAngle)%(2 * Math.PI);
    }

    protected double myRadius;
    protected double myAngle;
}
