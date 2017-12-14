package mttoolkit.event;

import java.util.EventObject;

import mttoolkit.mygeom.Tuple2;
import mttoolkit.mygeom.Vector2;

@SuppressWarnings("serial")
public class SRTEvent extends EventObject {

    // La transformation à appliqué
    private Tuple2 translation;
    private double rotation;
    private double scale;

    public SRTEvent(Object source, Tuple2 translation, double rotation, double scale) {
        super(source);
        this.translation = translation;
        this.rotation = rotation;
        this.scale = scale;
    }

    public Tuple2 getTranslation() {
        return translation;
    }

    public void setTranslation(Vector2 translation) {
        this.translation = translation;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }


}
