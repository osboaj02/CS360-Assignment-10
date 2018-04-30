package model;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class FileManager {

/*	@nathan Lantz
 * 	file manager imports, exports, saves and reads
 * 
 * 
 * 
 * 	
 */
	String loadedFile;
	ArrayList<School> schools;
	LocationFinder finder;
	Tournament tournament;
	public FileManager() {
		schools = new ArrayList<School>();		
		finder = new LocationFinder();
	}
	
	public void importNew() {
		//imports a school list from a csv File.
		String csvFile = "data.csv";
		BufferedReader br = null;
		String line = "";
		String[] data = null;
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine())!=null) {				
					 data = line.split(",");
					 schools.add(new School(data[0],
							 data[1],
							 data[2],
							 data[3],
							 data[4],
							 data[5],
							 data[6],
							 data[7],
							 data[8],
							 data[9],
							 data[10],
							 data[11],
							 data[12],
							 data[13],
							 data[14],
							 data[15]));				 				 					 
		}
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("loaded File");
					
	}
	
	public void getLocation() {
		String address =null;
		for (int i=0; i< schools.size();i++) {
			address = (schools.get(i).getAddress() + "," + schools.get(i).getCity() + " IN " + schools.get(i).getAreaCode());
			finder.find(address);	
			schools.get(i).setLat(finder.getFlat());
			schools.get(i).setLng(finder.getFlng());
			System.out.println(i+ " of " + schools.size() + " schools " + "lat: " + schools.get(i).getLat() + " long: " + schools.get(i).getLng());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public void distance(Tournament tournament) {
		this.tournament=tournament;
		double lat1;  
		double lat2; 
		double lon1; 
		double lon2; 
		double el1=0;
		double el2=0;
		

	    final int R = 6371; // Radius of the earth

	    for(int i=1; i < tournament.getSectionals().size();i++) {	
	    	 lat1 = tournament.getSectionals().get(i).getHost().getLat();
	    	 lon1 = tournament.getSectionals().get(i).getHost().getLng();   	
	    	for (int g=0; g<tournament.getSectionals().get(i).getParticipating().size();g++) {
	    		lat2 = tournament.getSectionals().get(i).getParticipating().get(g).getLat();
	    		lon2 = tournament.getSectionals().get(i).getParticipating().get(g).getLng();
	    		
	    		
	    	    double latDistance = Math.toRadians(lat2 - lat1);
	    	    double lonDistance = Math.toRadians(lon2 - lon1);
	    	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	    	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	    	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    	    double distance = R * c * 1000; // convert to meters

	    	    double height = el1 - el2;

	    	    distance = Math.pow(distance, 2) + Math.pow(height, 2);
	    	    distance = Math.sqrt(distance);
	    	    distance = (distance/1609.34);
	    	    
	    	    DecimalFormat formatter = new DecimalFormat("#0.00");
	    	    distance = Math.round(distance*100.0)/100.0;
	    	    
	    	    tournament.getSectionals().get(i).getParticipating().get(g).setDistance(distance);
	    	}
	    	
	    }
	    
	}	 
	
	public void distanceOne(int index, int index1) {
		double lat1 = tournament.getSectionals().get(index).getHost().getLat();  
		double lat2 = tournament.getSectionals().get(index).getParticipating().get(index1).getLat(); 
		double lon1 =  tournament.getSectionals().get(index).getHost().getLng();
		double lon2 = tournament.getSectionals().get(index).getParticipating().get(index1).getLng();  
		double el1=0;
		double el2=0;
		

	    final int R = 6371; // Radius of the earth


	    		
	    		
	    	    double latDistance = Math.toRadians(lat2 - lat1);
	    	    double lonDistance = Math.toRadians(lon2 - lon1);
	    	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	    	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	    	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    	    double distance = R * c * 1000; // convert to meters

	    	    double height = el1 - el2;

	    	    distance = Math.pow(distance, 2) + Math.pow(height, 2);
	    	    distance = Math.sqrt(distance);
	    	    distance = (distance/1609.34);
	    	    
	    	    DecimalFormat formatter = new DecimalFormat("#0.00");
	    	    distance = Math.round(distance*100.0)/100.0;
	    	    
	    	    tournament.getSectionals().get(index).getParticipating().get(index1).setDistance(distance);
	    	
	    	
	    
	    
	}	 
	

	
	public void findOne(int i) {
		String address = null;
		address = (schools.get(i).getAddress() + "," + schools.get(i).getCity() + " IN " + schools.get(i).getAreaCode());
		System.out.println(address);
		finder.find(address);	
		schools.get(i).setLat(finder.getFlat());
		schools.get(i).setLng(finder.getFlng());
		System.out.println(i+ " of " + schools.size() + " schools " + "lat: " + schools.get(i).getLat() + " long: " + schools.get(i).getLng());
		try {
			Thread.sleep(1250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void findOneExists(Tournament t,int j, int i) {
		String address = null;
		address = (t.getSectionals().get(j).getParticipating().get(i).getAddress() + "," + t.getSectionals().get(j).getParticipating().get(i).getCity() + " IN " + 
				t.getSectionals().get(j).getParticipating().get(i).getAreaCode());
		System.out.println(address);
		finder.find(address);	
		t.getSectionals().get(j).getParticipating().get(i).setLat(finder.getFlat());
		t.getSectionals().get(j).getParticipating().get(i).setLng(finder.getFlng());
		System.out.println(i+ " of " + t.getSectionals().get(j).getParticipating().size() + " schools " + "lat: " +t.getSectionals().get(j).getParticipating().get(i).getLat() + 
				" long: " + t.getSectionals().get(j).getParticipating().get(i).getLng());
		try {
			Thread.sleep(1250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void save(Tournament tournament) {
	    
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream(getLoadedFile());
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(tournament);
	         out.close();
	         fileOut.close();
	         System.out.println("Serialized data is saved in " + getLoadedFile().getName());
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
	}
	
	public void saveAs(Tournament tournament, String fileName) {
		File f = new File(fileName);
		try {
			FileOutputStream fOut = 
			new FileOutputStream(f);
			ObjectOutputStream out = new ObjectOutputStream(fOut);
			out.writeObject(tournament);
			out.close();
			fOut.close();
			System.out.println("New serialized data is saved in " + f.getName());
		} catch(IOException i) {
			i.printStackTrace();
		}
	}
	
	public Tournament importExists(String fileName) {
		Tournament tournament;
		loadedFile = fileName;
	    try {
	         FileInputStream fileIn = new FileInputStream(fileName);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         tournament = (Tournament) in.readObject();
	         
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	         return null;
	      } catch (ClassNotFoundException c) {
	         System.out.println("not found");
	         c.printStackTrace();
	         return null;
	      }
	    System.out.println("loaded File");
	    return tournament;
		
	}
	
	public File getLoadedFile() {
		File f = null;
		
		f = new File(loadedFile);
		
		return f;
	}
	//getters for school
	public ArrayList<School> getSchools() {
		return schools;
	}

	public void setSchools(ArrayList<School> schools) {
		this.schools = schools;
	}
	
	
	
	
	
	
}
