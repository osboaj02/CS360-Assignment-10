package controller;



import model.FileManager;
import model.Tournament;

import view.Interface;
import view.Map;

public class OperationsManager {
	
/*	@Nathan_Lantz
	model implements data and passes it to the controller



*/
	public static void main(String[] args) {
		// TODO Auto-generated method stub	

			
		//Location loc = new Location(60.5, 13.4);
		//map.setBerlinLocation(loc);

		//test.update(test.getGraphics());

		
		FileManager fm = new FileManager();
		Tournament tn;
		Interface view;
		
		
		
		
		
		//building tournament creates and saves the inital tournament
		
		//sizes must have 1 added to them from new tournament 
		
		//fm.importNew();	//1
		//fm.findOne(299);
		//fm.getLocation();	//1	
		tn = fm.importExists("Tournament.dat");
		
		//40.499183,-86.9167149
		tn.getSectionals().get(6).getParticipating().get(1).setLat(40.499183f);
		tn.getSectionals().get(6).getParticipating().get(1).setLng(-86.9167149f);
		//System.out.println(tn.getSectionals().get(6).getParticipating().get(1).getLat());
		//System.out.println(tn.getSectionals().get(6).getParticipating().get(1).getLng());
		fm.distance(tn);
		//tn = new Tournament(fm.getSchools(),33,17,5); //1
		//tn.sorters(); //1	
		
		
		//System.out.println(tn.getSectionals().get(0 + 1).getHost().getName());
		view = new Interface(tn,fm);
		//loads a saved tournament
		fm.save(tn);
//		tn = fm.importExists("Tournament");
		

		
		
		
		
	}

}
