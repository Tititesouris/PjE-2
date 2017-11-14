package widget;

import javax.swing.*;

import event.ChangedSideEvent;
import event.ChangedSideListener;
import event.Side;
import mygeom.Path;
import mygeom.Point2;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;

import tuio.*;

public class MTSurface extends JPanel {

	public boolean dispCur;
	public MTedt edt;
	private HashMap<Integer, Path> pathMap;
	private ArrayList<ChangedSideListener> sideListeners;

	public MTSurface() {
		super();
		edt = new MTedt(this);
		pathMap = new HashMap<Integer, Path>();
		sideListeners = new ArrayList<ChangedSideListener>();
		dispCur = false;
	}

	public synchronized void addCursor(int id, Point2 p) {
		pathMap.put(id, new Path(id));
		pathMap.get(id).add(p);
		//System.out.println(id + " - (" + p.getX() + "," + p.getY() + ")");
		this.repaint();
	}

	public synchronized void updateCursor(int id, Point2 p) {
		pathMap.get(id).add(p);
		//System.out.println(id + " - (" + p.getX() + "," + p.getY() + ")");
		this.repaint();

		Point2 ptmp = pathMap.get(id).get(pathMap.get(id).size() - 2);
		
		int xPTest = (int) (p.getX() / 0.5);
		
		
		if (xPTest != (int) (ptmp.getX() / 0.5)) {
			if (xPTest == 0)
				fireChangedSideListener(new ChangedSideEvent(this, id, Side.LEFT));
			else
				fireChangedSideListener(new ChangedSideEvent(this, id, Side.RIGHT));
		}
	}

	public synchronized void removeCursor(int id, Point2 p) {
		pathMap.get(id).clear();
		//System.out.println(id + " - (" + p.getX() + "," + p.getY() + ")");
		this.repaint();
	}

	public void addChangedSideListener(ChangedSideListener list) {
		sideListeners.add(list);
	}

	public void fireChangedSideListener(ChangedSideEvent event) {
		for (ChangedSideListener actionListener : sideListeners) {
			actionListener.changedSidePerformed(event);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		// drawing instructions with g2.
		for (Path p : pathMap.values()) {
			p.draw(g2, dispCur);
		}
	}

}
