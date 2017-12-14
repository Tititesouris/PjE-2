package mttoolkit.widget;

import mttoolkit.event.*;
import mttoolkit.mygeom.Point2;
import mttoolkit.mygeom.Vector2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class MTPicture extends MTComponent {

    private Image img;

    public MTPicture(Point2 position, Point2 size, String imgPath) {
        obb.setOrigin(new Vector2(position.getX(), position.getY()));
        obb.setHeight(size.getX());
        obb.setWidth(size.getY());
        obb.setAngle(0.0);
        try {
            img = ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        addDiscreteEventListener(new DiscreteEventListener() {
            @Override
            public void gesturePerformed(DiscreteEvent event) {
                System.out.println("CLICKED ON IMAGE " + position.getX());
                click();
            }
        });
        addSRTEventListener(new SRTEventListener() {
            @Override
            public void gesturePerformed(SRTEvent event) {
                ((MTPicture) event.getSource()).updatePosition(event.getTranslation(), event.getRotation(),
                        event.getScale());
            }
        });

        addGestureEventListener(new GestureEventListener() {
            @Override
            public void gesturePerformed(GestureEvent event) {
                System.out.println(event);
                if ("delete".equals(event.getTemplate().getName())) {
                    System.out.println("TODO DELETE"); // TODO
                }
                System.out.println(event.getTemplate().getName() + " ::: " + event.getScore());
            }
        });
    }

    @Override
    public boolean isInside(Point2 p) {
        return obb.getOrigin().getX() <= p.getX() && p.getX() <= obb.getOrigin().getX() + obb.getHeight()
                && obb.getOrigin().getY() <= p.getY() && p.getY() <= obb.getOrigin().getY() + obb.getWidth();
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawImage(img, 0, 0, (int) obb.getHeight(), (int) obb.getWidth(), null);
        g.setColor(Color.DARK_GRAY);
        g.drawRoundRect(0, 0, (int) obb.getHeight(), (int) obb.getWidth(), 10, 10);
    }

}
