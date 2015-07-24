/** Simple animation. */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class sGUI {
	int x = 70;
	int y = 70;
	int time = 0;
	JFrame frame;
	JLabel label;
	JButton buttonT; JButton buttonS; JButton buttonR; JButton buttonQ;
	MyDrawPanel drawPanel;
	JPanel topPanel; JPanel middlePanel; JPanel bottomPanel;

	public static void main (String[] args) {
		sGUI gui = new sGUI();
		gui.go();
	}

	public void go() {
		frame = new JFrame();
		frame. setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawPanel = new MyDrawPanel();

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

		//buttonT.setActionCommand("enable");
		enableAll();

		frame.getContentPane().add(drawPanel);
		frame.setSize(400, 400);
		frame.setVisible(true);
	}

	public void disableAll() {
		buttonT.setEnabled(false);
		buttonS.setEnabled(false);
		buttonR.setEnabled(false);
		buttonQ.setEnabled(false);
	}

	public void enableAll() {
		buttonT.setEnabled(true);
		buttonS.setEnabled(true);
		buttonR.setEnabled(true);
		buttonQ.setEnabled(true);
	}

	/** the draw panel contains the graphical representation of the simulation:
		. A representation of the statue
		. A representation of the cat and mouse via graphic objects.
		. the graphic you choose to represent your cat and mouse must:
			- It must be evident which direction it is facing
			- It must be evident what the (x,y) point is
	*/

	class MyDrawPanel extends JPanel {
		public void paintComponent(Graphics g) {
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			g.setColor(Color.green);
			g.fillOval(x, y, 40, 40);
		}
	}

	/** The Reset Button sets all variables to the inital values and time to 0. */
	class ResetListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			for (int i = 0; i < 130; i++) {
				x++;
				y++;
				drawPanel.repaint();

				try {
					Thread.sleep(50);
				} catch (Exception ex) { }
			}

			enableAll();
		}
	}

	/** The Step Button advances time one step in the simulation.
		Enabled until the simulation is finished, then disabled. */
	class StepListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			buttonS.setText("STEPPED");
			//while stepping, disableAll();
			//if (simulation is finished) { buttonS.setEnabled(false); }
		}
	}

	/** The Run Button runs the simulation from its current 'time' to completion;
		the mouse is caught or 30 "minutes" have elapsed.
		Then only reset and quit buttons are enabled. */
	class RunListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			buttonR.setText("RUNNING");
			//while running, disableAll();
			buttonR.setEnabled(false);
			buttonS.setEnabled(false);
		}
	}

	/** The Quit Button quits the simulation. */
	class QuitListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}
	}

}

