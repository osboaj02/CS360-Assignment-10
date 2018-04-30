package model;

import java.io.Serializable;
import java.util.ArrayList;

public class SemiState implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	School host;
	ArrayList<School> sectionals;
	ArrayList<School> Regionals;
	ArrayList<School> participating;
	
	public SemiState() {
		participating = new ArrayList<School>();
	}
	
	
	public void setHost(School host) {
		this.host = host;
	}
	public void addSchool(School school) {
		participating.add(school);
	}

	public void removeSchool(int i) {
		participating.remove(i);
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
