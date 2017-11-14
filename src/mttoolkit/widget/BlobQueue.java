package mttoolkit.widget;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import mttoolkit.mygeom.Path;

public class BlobQueue {
	
	private Map<Integer, Path> cursor = new HashMap<>();

	public void addBlob(int id, Point p) {
		cursor.put(id, new Path(p));
	}

	public void updateBlob(int id, Point p) {
		cursor.get(id).add(p);
	}

	public void removeBlob(int id) {
		cursor.remove(id);
	}
	
	public boolean checkID(int id) {
		return cursor.containsKey(id);
	}

}
