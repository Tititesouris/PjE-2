package mttoolkit.widget;

import java.util.HashMap;
import java.util.Map;

import mttoolkit.mygeom.Point2;

public class ComponentMap {
	
	private Map<MTComponent, BlobQueue> cMap = new HashMap<>();
	
	public void addBlob(MTComponent component, int id, Point2 p) {
		cMap.get(component).addBlob(id, p);
	}
	
	public void updateBlob(int id, Point2 p) {		
		for (MTComponent c : cMap.keySet()) {
			if (cMap.get(c).checkID(id)) {
				cMap.get(c).updateBlob(id, p);
				break;
			}
		}
	}
	
	public void removeBlob(int id, Point2 p) {
		for (MTComponent c : cMap.keySet()) {
			if (cMap.get(c).checkID(id)) {
				cMap.get(c).removeBlob(id);
				break;
			}
		}
	}
	
	public Map<MTComponent, BlobQueue> getBlobQueue() {
		return cMap;
	}
}
