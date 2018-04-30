package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolTip;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import model.School;

public class Map{

	
	DefaultTileFactory tileFactory;
	TileFactoryInfo info;
	JXMapKit jXMapKit;
	JPanel map;
	ArrayList<GeoPosition> geoList;
	Set<MyWaypoint> locations;
	WaypointPainter<MyWaypoint> markers;
	ArrayList<School> schools;
	School host;

	public Map() {
		

		
		map = new JPanel();
		jXMapKit = new JXMapKit();
		info = new OSMTileFactoryInfo();
		tileFactory = new DefaultTileFactory(info);
		jXMapKit.setTileFactory(tileFactory);

	}
	
	public void setFocus(int p) {
		
	jXMapKit.getMainMap().setCenterPosition(geoList.get(p));	
	jXMapKit.getMainMap().setZoom(7);
		

	}

	public void createMap() {

		GeoPosition hostMarker = new GeoPosition(host.getLat(), host.getLng());

		markers.setWaypoints(locations);
		markers.setRenderer(new FancyWaypointRenderer());

		jXMapKit.getMainMap().setOverlayPainter(markers);
		jXMapKit.setZoom(11);
		jXMapKit.setAddressLocation(hostMarker);
		jXMapKit.setPreferredSize(new Dimension(725, 370));


	

		map.add(jXMapKit);
	}

	public void removeMarkers() {
		jXMapKit.getMainMap().setOverlayPainter(null);
	}

	public void createGeo(ArrayList<School> schools, School host) {
		this.host = host;
		this.schools = schools;
		float lat;
		float lng;

		locations = new HashSet<MyWaypoint>();
		geoList = new ArrayList<GeoPosition>();
		markers = new WaypointPainter<MyWaypoint>();
		Container cont = new Container();

		for (int i = 0; i < schools.size(); i++) {
			lat = schools.get(i).getLat();
			lng = schools.get(i).getLng();	
			//System.out.println(i);
			//System.out.println(host.getName());
			if (schools.get(i).getName().compareToIgnoreCase(host.getName()) == 0) {	
				
				geoList.add(new GeoPosition(lat, lng));
				locations.add(new MyWaypoint("H", Color.GREEN, geoList.get(i)));
				
			} else {	
				geoList.add(new GeoPosition(lat, lng));
				locations.add(new MyWaypoint(i + "", Color.WHITE, geoList.get(i)));
			}

		}

		createMap();

	}

	


	public JPanel getMap() {
		return map;
	}

	public void setMap(JPanel map) {
		this.map = map;
	}
}
