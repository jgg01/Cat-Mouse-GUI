/* GUI.java by jgg01. **/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends CatMouseSimulation {
	int x = 70;
	int y = 70;
	int w = 450; int h = 450;
	int time = CatMouseSimulation.count;
	JFrame frame;
	JLabel label;
	JButton buttonT; JButton buttonS; JButton buttonR; JButton buttonQ;
	MyDrawPanel drawPanel;
	JPanel topPanel; JPanel middlePanel; JPanel bottomPanel;
	long startTime; long endTime; long execTime;
	static double[] coord;

	public static void main (String[] args) {
		GUI gui = new GUI();
		gui.go();
	}
	
	private void tExec() {
		execTime = endTime - startTime;
	}

	public void go() {
		frame = new JFrame();
		frame. setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawPanel = new MyDrawPanel();
		Font bigFont = new Font("serif", Font.BOLD, 16);

		buttonT = new JButton("RESET");
		buttonS = new JButton("STEP");
		buttonR = new JButton("RUN");
		buttonQ = new JButton("QUIT");

		buttonT.setToolTipText("Reset the simulation");
		buttonS.setToolTipText("Move one step");
		buttonR.setToolTipText("Run the complete simulation");
		buttonQ.setToolTipText("Quit the program");

		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		buttonT.addActionListener(new ResetListener());
		buttonS.addActionListener(new StepListener());
		buttonR.addActionListener(new RunListener());
		buttonQ.addActionListener(new QuitListener());

		bottomPanel.add(buttonT);
		bottomPanel.add(buttonS);
		bottomPanel.add(buttonR);
		bottomPanel.add(buttonQ);

		JLabel timeLabel = new JLabel("Time:" + time);
		bottomPanel.add(timeLabel);

		frame.setTitle("CatMouse Simulation 1.0  ");
		frame.add(bottomPanel, BorderLayout.SOUTH);

		enableAll();

		frame.getContentPane().add(drawPanel);
		frame.setSize(w, h);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	protected void disableAll() {
		buttonT.setEnabled(false);
		buttonS.setEnabled(false);
		buttonR.setEnabled(false);
		buttonQ.setEnabled(false);
	}

	protected void enableAll() {
		buttonT.setEnabled(true);
		buttonS.setEnabled(true);
		buttonR.setEnabled(true);
		buttonQ.setEnabled(true);
	}

	protected static void moveSim (double mX, double mY, double cX, double cY) {
		double[] d = { mX, mY, cX, cY };
		coord = d;
	}

	/** The Reset Button sets all variables to the inital values and time to 0. */
	class ResetListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			enableAll();
		}
	}

	private void tStart() {
		if (startTime == 0) {
			startTime = System.currentTimeMillis();
		}
	}

	private void simFinished() {
		buttonR.setEnabled(false);
		buttonS.setEnabled(false);
		tEnd();
	}

	private void tEnd() {
		endTime = System.currentTimeMillis();
		tExec();
	}


	/** The Step Button advances time one step in the simulation.
		Enabled until the simulation is finished, then disabled. */
	class StepListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			tStart();
			buttonS.setText("STEPPED");
			
			//while stepping, disableAll();
			//if (simulation is finished) { simFinished(); }
		}
	}

	/** The Run Button runs the simulation from its current 'time' to completion;
		the mouse is caught or 30 "minutes" have elapsed.
		Then only reset and quit buttons are enabled. */
	class RunListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			tStart();
			buttonR.setText("RUNNING");
			//while running, disableAll();
			simFinished();
		}
	}

	/** The Quit Button quits the simulation. */
	class QuitListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}
	}

	/** the draw panel contains the graphical representation of the simulation:
		. A representation of the statue
		. A representation of the cat and mouse via graphic objects.
		. the graphic you choose to represent your cat and mouse must:
			- It must be evident which direction it is facing
			- It must be evident what the (x,y) point is
	*/
	class MyDrawPanel extends JPanel {

		Image cat = new ImageIcon("cat.png").getImage();
		Image mouse = new ImageIcon("mouse.png").getImage();

		public void paintComponent(Graphics g) {
			//System.out.println("hi " + coord[0]);
			/** Background. */
			g.setColor(Color.yellow);
			g.fillRect(0, 0, w, h);

			/** Statue. */
			g.setColor(Color.black);
			g.fillOval(w / 3 + 20, h / 3, 20, 20);

			/** Set center. */
			g.translate(w/3 + 20, h/3);

			g.drawImage(mouse, coord[0], coord[1], this);
			g.drawImage(cat, coord[2], coord[3], this);

			/**g.setColor(Color.pink);
			g.fillOval(50, 50, 100, 100);

			for (int i = 0; i < 130; i++) {
				x++;
				y++;
				drawPanel.repaint();

				try {
					Thread.sleep(50);
				} catch (Exception ex) { }
			}*/
		}
	}

}

