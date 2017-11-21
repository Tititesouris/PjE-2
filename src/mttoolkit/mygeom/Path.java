package mttoolkit.mygeom;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.Iterator;

public class Path {

	private int id;
	private ArrayList<Point2> pathList;

	public Path(int id) {
		this.id = id;
		pathList = new ArrayList<Point2>();
	}
	
	public Path(int id, Point2 p) {
		this.id = id;
		pathList = new ArrayList<Point2>();
		pathList.add(p);
	}
	
	public Point2 get(int index) {
		return pathList.get(index);
	}

	public void add(Point2 p) {
		pathList.add(p);
	}
	
	public int size() {
		return pathList.size();
	}

	public void clear() {
		pathList.clear();
	}

	public Point2 isobarycentre() {
		Point2 p = new Point2();

		for (Point2 point2 : pathList) {
			p.setX(p.getX() + point2.getX());
			p.setY(p.getY() + point2.getY());
		}

		p.setX(p.getX() / pathList.size());
		p.setY(p.getY() / pathList.size());

		return p;
	}

	public void draw(Graphics2D g, boolean dispCurl) {
		GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD, pathList.size());
		g.setColor(new Color(0, 0, 255));

		if (!pathList.isEmpty()) {
			path.moveTo((double) pathList.get(0).getX() * g.getClipBounds().getWidth(),
					(double) pathList.get(0).getY() * g.getClipBounds().getHeight());

			for (int i = 1; i < pathList.size(); i++) {
				path.lineTo((double) pathList.get(i).getX() * g.getClipBounds().getWidth(),
						(double) pathList.get(i).getY() * g.getClipBounds().getHeight());
			}

			//System.out.println(path.getBounds2D());

			g.draw(path);

			if (dispCurl) {
				g.fillOval((int) (pathList.get(pathList.size() - 1).getX() * g.getClipBounds().getWidth()) - 15,
						(int) (pathList.get(pathList.size() - 1).getY() * g.getClipBounds().getHeight()) - 15, 30, 30);
				g.setColor(Color.WHITE);
				g.drawString("" + id, (int) (pathList.get(pathList.size() - 1).getX() * g.getClipBounds().getWidth()),
						(int) (pathList.get(pathList.size() - 1).getY() * g.getClipBounds().getHeight()));
			}

			g.setColor(Color.RED);
			Point2 iso = isobarycentre();
			g.fillOval((int) (iso.getX() * g.getClipBounds().getWidth()) - 15,
					(int) (iso.getY() * g.getClipBounds().getHeight()) - 15, 30, 30);

			//System.out.println("PAINT !!");
		}
	}
}
