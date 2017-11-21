package mttoolkit.tuio;

import TUIO.*;
import mttoolkit.mygeom.Point2;
import mttoolkit.widget.MTSurface;

public class MTEdt implements TuioListener {

	TuioClient client=null;
	MTSurface surface; // la surface qui recevra les messages des curseurs.
	
	private void initConnexion() {
		int port=3333;
		client=new TuioClient(port);
		client.connect();
		if (!client.isConnected()) {
			System.exit(1);
		}
		System.out.println("connexion");
		client.addTuioListener(this);
	}
	
	public MTEdt() {
		initConnexion();
	}
	
	public MTEdt(MTSurface mtSurface) {
		initConnexion();
		surface=mtSurface;
	}
	
	public void stop() {
		client.disconnect();
		System.out.println("deconnexion");
	}
	
		
	/** Listeners **/
	
	public void addTuioObject(TuioObject tobj) {
	}

	public void updateTuioObject(TuioObject tobj) {
	}
	
	public void removeTuioObject(TuioObject tobj) {
	}

	public void addTuioCursor(TuioCursor tcur) {
		surface.addCursor(tcur.getCursorID(), new Point2(tcur.getX(), tcur.getY()));
	}

	public void updateTuioCursor(TuioCursor tcur) {
		surface.updateCursor(tcur.getCursorID(), new Point2(tcur.getX(), tcur.getY()));
	}
	
	public void removeTuioCursor(TuioCursor tcur) {
		surface.removeCursor(tcur.getCursorID(), new Point2(tcur.getX(), tcur.getY()));
	}

	
	public void refresh(TuioTime frameTime) {
	}

}
