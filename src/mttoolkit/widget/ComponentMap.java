package mttoolkit.widget;

import java.util.HashMap;
import java.util.Map;

import mttoolkit.mygeom.Point2;
import mttoolkit.tuio.GestureAnalyzer;

public class ComponentMap {
	
	private Map<MTComponent, BlobQueue> cMap = new HashMap<>();

	private GestureAnalyzer analyzer = new GestureAnalyzer();

	public void addBlob(MTComponent component, int id, Point2 p) {
	    if (!cMap.containsKey(component)) {
	        cMap.put(component, new BlobQueue());
        }
		cMap.get(component).addBlob(id, p);
		analyzer.analyse(component, cMap.get(component), "add", id, p);
	}

	public void updateBlob(int id, Point2 p) {
		for (MTComponent c : cMap.keySet()) {
			if (cMap.get(c).checkID(id)) {
				cMap.get(c).updateBlob(id, p);

				analyzer.analyse(c, cMap.get(c), "update", id, p);
				break;
			}
		}
	}

	public void removeBlob(int id, Point2 p) {
		for (MTComponent c : cMap.keySet()) {
			if (cMap.get(c).checkID(id)) {
				cMap.get(c).removeBlob(id);

				analyzer.analyse(c, cMap.get(c), "remove", id, p);
				break;
			}
		}
	}
	
	public Map<MTComponent, BlobQueue> getBlobQueue() {
		return cMap;
	}
}
