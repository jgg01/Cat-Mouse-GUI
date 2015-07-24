/* GUI.java by jgg01. **/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class newGUI extends Frame implements WindowListener,ActionListener {

	JButton b1; JButton b2; JButton b3;

	public newGUI(String title) {
		super(title);
		setLayout(new FlowLayout());
		addWindowListener(this);
		JButton b = new JButton("click me");
		JButton c = new JButton("click me");
		JButton d = new JButton("click me");
		add(b); add(c); add(d);

		frame.getContentPane().add(BorderLayout.CENTER, b);
		frame.getContentPane().add(BorderLayout.CENTER, c);
		frame.getContentPane().add(BorderLayout.CENTER, d);

	    b.setVerticalTextPosition(AbstractButton.CENTER);
	    b.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT
	    b.setMnemonic(KeyEvent.VK_D);
	    b.setActionCommand("enable");

	    c.setVerticalTextPosition(AbstractButton.BOTTOM);
	    c.setHorizontalTextPosition(AbstractButton.CENTER);
	    c.setMnemonic(KeyEvent.VK_M);

	    d.setMnemonic(KeyEvent.VK_E);
	    d.setActionCommand("enable");
	    d.setEnabled(true);

		b.addActionListener(this);
		c.addActionListener(this);
		d.addActionListener(this);


	    b.setToolTipText("Click this button to disable");
	    c.setToolTipText("This middle button does nothing");
	    d.setToolTipText("Click this button to click it");
	}

	public static void main(String[] args) {
		newGUI myWindow = new newGUI("My first");
		myWindow.setSize(350, 700);
		myWindow.setVisible(true);
	}


	public void actionPerformed(ActionEvent e) {
	    if ("disable".equals(e.getActionCommand())) {
		b2.setEnabled(false);
		b1.setEnabled(false);
		b3.setEnabled(false);
	    } else {
		b2.setEnabled(true);
		b1.setEnabled(true);
		b3.setEnabled(true);
	    }
	} 

        public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
        }

        public void windowOpened(WindowEvent e) {}
        public void windowActivated(WindowEvent e) {}
        public void windowIconified(WindowEvent e) {}
        public void windowDeiconified(WindowEvent e) {}
        public void windowDeactivated(WindowEvent e) {}
        public void windowClosed(WindowEvent e) {}
}

