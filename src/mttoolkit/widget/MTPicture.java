package mttoolkit.widget;

import mttoolkit.mygeom.Point2;

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

    private Point2 position;

    private Point2 size;

    private Image img;

    public MTPicture(Point2 position, Point2 size, String imgPath) {
        this.position = position;
        this.size = size;
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isInside(Point2 p) {
        return position.getX() <= p.getX() && p.getX() <= position.getX() + size.getX() && position.getY() <= p.getY() && p.getY() <= position.getY() + size.getY();
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(img, (int) (position.getX() - (size.getX() / 2)), (int) (position.getY() - (size.getY() / 2)), (int) size.getX(), (int) size.getY(),
                null);
        g.setColor(Color.DARK_GRAY);
        g.drawRoundRect((int) position.getX(), (int) position.getY(), (int) size.getX(), (int) size.getY(), 10, 10);
    }

}
