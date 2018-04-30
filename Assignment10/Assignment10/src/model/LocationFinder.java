package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class LocationFinder {
	// TODO Auto-generated method stub
	URL url;
	URL urlMap;
	String line;
	String key = "AIzaSyAG7q6MLeY_rZ3xr4sRbNJ-yOw2jOmK3A4";
	String key2 = "AIzaSyBy9uZrJQ2QjIYPM26L3omJHszyjMUNwr8";
	String inputLine = null;
	String lat;
	String lng;
	float flat;
	float flng;

	public LocationFinder() {
	}
	
	public void find(String address) {
		try {
			//System.out.println(address);
			address = address.replaceAll("\\s+", "%20");
			

			url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=" + key);
			//System.out.println(url);
			

			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			ArrayList<String> array = new ArrayList<String>();

			String i = "";
			while ((i = in.readLine()) != null) {
				inputLine = inputLine + i + "\n";
				array.add(i);
			}
			
			for (int j=0; j<array.size();j++) {			
				if(array.get(j).compareToIgnoreCase("            \"location\" : {")==0) {
					lat = array.get(j+1);
					lng = array.get(j+2);		
					Thread.sleep(1000);
					break;
				}
			}
			
			in.close();



			String[] parts = lat.split(":");
			String[] parts1 = lng.split(":");

			lat = parts[1];
			lng = parts1[1];

			parts = lat.split(",");
			parts1 = lng.split(",");

			lat = parts[0];
			lng = parts1[0];

			flat = Float.parseFloat(lat);
			flng = Float.parseFloat(lng);

			//System.out.println(flat + " " + flng);
			//System.out.println();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public float getFlat() {
		return flat;
	}

	public void setFlat(float flat) {
		this.flat = flat;
	}

	public float getFlng() {
		return flng;
	}

	public void setFlng(float flng) {
		this.flng = flng;
	}

}
