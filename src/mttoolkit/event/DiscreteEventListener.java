package mttoolkit.event;

import java.util.EventListener;

public interface DiscreteEventListener extends EventListener {
	public abstract void gesturePerformed(DiscreteEvent event);
}
