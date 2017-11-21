package mttoolkit.event;

import java.awt.geom.AffineTransform;
import java.util.EventObject;

@SuppressWarnings("serial")
public class SRTEvent extends EventObject {
	
	// La transformation à appliqué
	private AffineTransform transform;

	public SRTEvent(Object source, AffineTransform transform) {
		super(source);
		this.transform = transform;
	}

}
