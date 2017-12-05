package mttoolkit.widget;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import mttoolkit.mygeom.Path;
import mttoolkit.mygeom.Point2;

public class BlobQueue {

    private Map<Integer, Path> cursor = new HashMap<>();
    
    public Path getBlob(Integer id) {
    	return cursor.get(id);
    }

    public void addBlob(int id, Point2 p) {
        cursor.put(id, new Path(id, p));
    }

    public void updateBlob(int id, Point2 p) {
        cursor.get(id).add(p);
    }

    public void removeBlob(int id) {
        cursor.remove(id);
    }

    public boolean checkID(int id) {
        return cursor.containsKey(id);
    }
    
    public int length() {
    	return cursor.size();
    }

    public void draw(Graphics2D g2, boolean cursorsVisible) {
        for (Path p : cursor.values()) {
            p.draw(g2, cursorsVisible);
        }

    }

}
