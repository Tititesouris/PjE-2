package mttoolkit.widget;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class ComponentMap {
	
	private Map<MTComponent, BlobQueue> cMap = new HashMap<>();
	
	public void addBlob(MTComponent component, int id, Point p) {
		cMap.get(component).addBlob(id, p);
	}
	
	public void updateBlob(int id, Point p) {		
		for (MTComponent c : cMap.keySet()) {
			if (cMap.get(c).checkID(id)) {
				cMap.get(c).updateBlob(id, p);
				break;
			}
		}
	}
	
	public void removeBlob(int id, Point p) {
		for (MTComponent c : cMap.keySet()) {
			if (cMap.get(c).checkID(id)) {
				cMap.get(c).removeBlob(id);
				break;
			}
		}
	}
}
