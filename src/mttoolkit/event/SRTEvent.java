package mttoolkit.event;

import java.awt.geom.AffineTransform;
import java.util.EventObject;

import mttoolkit.mygeom.Vector2;

@SuppressWarnings("serial")
public class SRTEvent extends EventObject {

	// La transformation à appliqué
	private Vector2 transform;
	private double rotation;
	private double scale;

	public SRTEvent(Object source, Vector2 transform, double rotation, double scale) {
		super(source);
		this.transform = transform;
		this.rotation = rotation;
		this.scale = scale;
	}

	public Vector2 getTransform() {
		return transform;
	}

	public void setTransform(Vector2 transform) {
		this.transform = transform;
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
