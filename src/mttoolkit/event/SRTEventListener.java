package mttoolkit.event;

import java.util.EventListener;

public interface SRTEventListener extends EventListener {
	public abstract void gesturePerformed(SRTEvent event);
}
