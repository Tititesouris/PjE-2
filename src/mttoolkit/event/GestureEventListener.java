package mttoolkit.event;

import java.util.EventListener;

public interface GestureEventListener extends EventListener {
    void gesturePerformed(GestureEvent event);
}