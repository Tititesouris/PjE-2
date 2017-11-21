package mttoolkit.event;

import java.util.EventListener;

public interface DiscreteEventListener extends EventListener {
	void gesturePerformed(DiscreteEvent event);
}
