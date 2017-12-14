package mttoolkit.recognizer;

import mttoolkit.event.DiscreteEvent;
import mttoolkit.event.SRTEvent;
import mttoolkit.mygeom.Point2;
import mttoolkit.mygeom.Tuple2;
import mttoolkit.mygeom.Vector2;
import mttoolkit.widget.BlobQueue;
import mttoolkit.widget.MTComponent;

public class GestureAnalyzer {

    private int cursorAID, cursorBID;
    private Point2 pA;
    private Point2 pB;
    private Dollar1Recognizer recognizer = new Dollar1Recognizer();

    public void analyse(MTComponent c, BlobQueue b, String state, int id, Point2 p) {
        switch (state) {
            case "add":
                add(c, b, p, id);
                break;

            case "update":
                update(c, b, p, id);
                break;

            case "remove":
                remove(c, b, p, id);
                break;
        }
    }

    private void add(MTComponent c, BlobQueue b, Point2 p, int id) {
        if (b.length() == 1) {
            cursorAID = id;
            pA = p;
            c.gestureState.motionTranslateBegin(p);
        } else if (b.length() == 2 && id != cursorAID) {
            cursorBID = id;
            pB = p;
            c.gestureState.motionTRSBegin(pA, p);
        }

        c.fireDiscretePerformed(new DiscreteEvent(c));
    }

    private void update(MTComponent c, BlobQueue b, Point2 p, int id) {
        if (b.length() == 1) {
            c.gestureState.motionTranslateUpdate(p);
            Tuple2 translate = c.gestureState.computeTranslation();
            c.fireSRTPerformed(new SRTEvent(c, translate, 0.0, 1.0));
        } else if (b.length() == 2) {
            if (id == cursorAID) {
                c.gestureState.motionTRSUpdate(p, pB);
                pA = p;
            }
            else if (id == cursorBID) {
                c.gestureState.motionTRSUpdate(pA, p);
                pB = p;
            }
            Tuple2 translation = c.gestureState.computeTRSTranslation();
            double rotation = c.gestureState.computeTRSRotation();
            double scale = c.gestureState.computeTRSScale();
            c.fireSRTPerformed(new SRTEvent(c, translation, rotation, scale));
        }
    }

    private void remove(MTComponent c, BlobQueue b, Point2 p, int id) {
        c.gestureState.motionTranslateBegin(new Vector2(p.getX(), p.getY()));
        c.gestureState.motionTranslateBegin(new Vector2(p.getX(), p.getY()));
        c.fireGesturePerformed(recognizer.recognize(b.getLastPath().getPoints()));
    }

}
