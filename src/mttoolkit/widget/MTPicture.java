package mttoolkit.widget;

import mttoolkit.DebugDraw;
import mttoolkit.InertiaMatrix;
import mttoolkit.event.*;
import mttoolkit.mygeom.OBB;
import mttoolkit.mygeom.Point2;
import mttoolkit.mygeom.Segment2;
import mttoolkit.mygeom.Vector2;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class MTPicture extends MTComponent {

    private Image img;

    private boolean isGestureHappening = false;

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
                if ("delete".equals(event.getTemplate().getName())) {
                    setVisible(false);
                }
                isGestureHappening = false;
            }
        });

        addGestureInProgressListener(new GestureInProgressListener() {
            @Override
            public void gesturePerformed(GestureInProgressEvent event) {
                if (isVisualFeedback()) {
                    isGestureHappening = true;
                }
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
        if (isVisible()) {
            g.drawImage(img, 0, 0, (int) obb.getHeight(), (int) obb.getWidth(), null);
            g.setStroke(new BasicStroke(3));
            if (isGestureHappening) {
                g.setColor(Color.RED);
            }
            else {
                g.setColor(Color.DARK_GRAY);
            }
            g.drawRoundRect(0, 0, (int) obb.getHeight(), (int) obb.getWidth(), 10, 10);
        }
    }

}
