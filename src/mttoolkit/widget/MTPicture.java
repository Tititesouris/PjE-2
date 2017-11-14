package mttoolkit.widget;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class MTPicture extends MTComponent {

	private Image img;
	
	public Rectangle bounds;

	public MTPicture(String imgPath) {
		try {
			img = ImageIO.read(new File(imgPath));
			bounds = new Rectangle(0, 0, img.getWidth(null), img.getHeight(null));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isInside(Point p) {
		return false;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(img, bounds.x - (bounds.width / 2), bounds.y - (bounds.height / 2), bounds.width, bounds.height,
				null);
		g.setColor(Color.DARK_GRAY);
		g.drawRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, 10, 10);
	}

}
