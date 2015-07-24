/** Position Testing. @author jgg01. 
    cat radius      cat angle       mouse angle     test case features
    Test 1) 1.0     35.0Â°   396.0Â°  angle > 360; mouse caught immediately
    Test 2) 3.2     0.0Â°    -57.0Â°  negative angle; cat radius has fractional part; mouse caught at time t
    Test 3) 8.1     0.0Â°    45.0Â°   ...figure out why...
    Test 4) 8.1     150.0Â°  240.0Â°  ...we asked...
    Test 5) 4.0     0.0Â°    -57.0Â°  ...for these tests?...
    Test 6) cat catches mouse at time 30; catches before time 30;
    Test 7) cat would catch mouse at time 31 if the simulation ran that long;
    Test 8) change position of a cat or mouse successfully. 
*/

import static org.junit.Assert.*;
import org.junit.Test;

public class PositionTestDrive {
	private CatMouseSimulation s = new CatMouseSimulation();

	/** Prints a statement to assess end of a tests print statements. */
	private void print() {
		System.out.println("end of test");
		s.count = 0;
		s.eaten = false;
	}

    /* Test 1. */
    @Test
    public void testMouseCaught() { 
	s = new CatMouseSimulation();
        double[] a = { 100, 1000, 1.0, 35.0, 396.0 };
	s.initialize(a);
	assertEquals(true, s.eaten);
	print();
    }

    /* Test 2. */
    @Test
    public void testNegAngle() {
	s = new CatMouseSimulation();
	
        double[] a = { 100, 1000, 3.2, 0.0, -57.0 };
	assertEquals(s.changeDegree(-57.0), 303.0, 1);
	s.initialize(a);
	int c= s.count;
	assertEquals(22, c);
	print();
    }

    /* Test 3. */
    @Test
    public void testEndSoon() { 
        double[] a = { 100, 1000, 8.1, 0.0, 45.0 };
	s.initialize(a);
	int c= s.count;
	assertEquals(30, c);
	print();
    }

    /* Test 4. */
    @Test
    public void testConfusingAngles() { 
        double[] a = { 100, 1000, 8.1, 150.0, 240.0 };
	s.initialize(a);
	int c= s.count;
	assertEquals(30, c);
	print();
    }

    /* Test 5. */
    @Test
    public void testNegativeStrangeness2() { 
        double[] a = { 100, 1000, 4.0, 0.0, -57.0 };
	s.initialize(a);
	String c= s.status();
	assertEquals("eaten", c);
	print();
    }

    /* Test 6. */
    @Test
    public void testTimeThirty() { 
        double[] a = { 100, 1000, 4.0, 0.0, 157.0 };
        s.initialize(a);
	int c= s.count;
	assertEquals(28, c);
	print();
        double[] b = { 100, 1000, 8.1, 0.0, 45.0 };
	s.initialize(b);
	int d= s.count;
	assertEquals(30, d);
	print();

    }

    /* Test 7. */
    @Test
    public void testShouldaWoulda() { 
        double[] a = { 100, 1000, 1.0, 66.21, 85.98 };
        s.initialize(a);
	int c= s.count;
	assertEquals(1, c);
	print();
    }

    /* Test 8. */
    @Test
    public void testChangePosition() { 
	Mouse m = new Mouse();
	m.move();
	assertEquals(m.getPosition().toString(), "(1.0,1.0)");
	m.getPosition().update(0, 1);
    }

    /* Run the unit tests in the testing files. */
    public static void main(String... args) {
        ucb.junit.textui.runClasses(PositionTestDrive.class);
    }
        
}

