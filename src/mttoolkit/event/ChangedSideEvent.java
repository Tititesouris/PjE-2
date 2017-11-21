package mttoolkit.event;

import java.awt.event.ActionEvent;

public class ChangedSideEvent extends ActionEvent {

    private Side side;

    public ChangedSideEvent(Object source, int id, Side side) {
        super(source, id, "");
        this.side = side;
    }

    public Side getSide() {
        return side;
    }

}
