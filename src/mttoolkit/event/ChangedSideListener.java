package mttoolkit.event;

import java.util.EventListener;

public interface ChangedSideListener extends EventListener {
    void changedSidePerformed(ChangedSideEvent evt);
}
