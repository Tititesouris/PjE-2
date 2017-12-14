package mttoolkit.event;

import java.util.EventListener;

public interface SRTEventListener extends EventListener {
    void gesturePerformed(SRTEvent event);
}
