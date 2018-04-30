package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Sectionals implements Serializable {
	
	
	
	/**
	 * 
	 */
	
	School host;
	ArrayList<School> participating;

	public Sectionals() {
		participating = new ArrayList<School>();
	}
	
	
	public void viewer() {
		//System.out.println(host.getName());
		for (int i =0; i<participating.size();i++) {
			System.out.println(participating.get(i).getName());
		}
	}
	
	public void removeSchool(int i) {
		participating.remove(i);
	}
	
	
	public void setHost(School host) {
		this.host = host;
	}
	public void addSchool(School school) {
		participating.add(school);
	}


	public ArrayList<School> getParticipating() {
		return participating;
	}


	public void setParticipating(ArrayList<School> participating) {
		this.participating = participating;
	}


	public School getHost() {
		return host;
	}
	
	
	

}
