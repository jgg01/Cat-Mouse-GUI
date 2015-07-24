/* MyDrawWidget.java by cs9g-ab. **/

import java.awt.*;
import javax.swing.*;

class MyDrawPanel extends JPanel {

	Image cat = new ImageIcon("cat.jpeg").getImage();
	Image mouse = new ImageIcon("mouse.jpg").getImage();

	/** the draw panel contains the graphical representation of the simulation:
		. A representation of the statue
		. A representation of the cat and mouse via graphic objects.
		. the graphic you choose to represent your cat and mouse must:
			- It must be evident which direction it is facing
			- It must be evident what the (x,y) point is
	*/

		public void paintComponent(Graphics g) {
			/** Background. */
			g.setColor(Color.yellow);
			g.fillRect(100, 100, this.getWidth(), this.getHeight());

			/** Statue. */
			g.setColor(Color.green);

		

		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		g.drawImage(cat, 3, 4, this);
		g.drawImage(mouse, 10, 11, this);

		Color randomColor = new Color(255, 20, 147);
		g.setColor(randomColor);
		g.fillOval(50, 50, 100, 100);
	}
}

