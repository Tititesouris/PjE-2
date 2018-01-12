package mttoolkit.event;

import java.util.EventListener;

public interface GestureInProgressListener extends EventListener {
    void gesturePerformed(GestureInProgressEvent event);
}
