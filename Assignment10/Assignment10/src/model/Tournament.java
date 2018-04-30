package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Tournament implements Serializable{


	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	ArrayList<Sectionals> sectionals;
	ArrayList<Regionals> regionals;
	ArrayList<SemiState> semiState;
	Finals finals;
	ArrayList<School> schoolList;

	public Tournament(ArrayList<School> schoolList, int secSize, int regSize, int semiStateSize) {

		sectionals = new ArrayList<Sectionals>();
		regionals = new ArrayList<Regionals>();
		semiState = new ArrayList<SemiState>();
		this.schoolList = schoolList;

		for (int i = 1; i < secSize+1; i++) {
			sectionals.add(new Sectionals());
		}

		for (int i = 1; i < regSize+1; i++) {
			regionals.add(new Regionals());
		}

		for (int i = 1; i < semiStateSize+1; i++) {
			semiState.add(new SemiState());
		}

	}
	
	public void sorters() {
		secSorter();
		regSorter();
		semiSorter();
	}

	public void secSorter() {
		for (int i = 1; i < (sectionals.size()); i++) {
			for (int j = 0; j < schoolList.size(); j++) {
				if (!(schoolList.get(j).getSectionalNum().compareToIgnoreCase("null") == 0)) {
					Integer num = Integer.parseInt(schoolList.get(j).getSectionalNum());
					if (num == i) {
						if(schoolList.get(j).getSectionalHost().compareToIgnoreCase("true")==0) {
							sectionals.get(i).setHost(schoolList.get(j));
							
						}
						sectionals.get(i).participating.add(schoolList.get(j));
						}
					}
				}
			}
		
	}
	
	public void regSorter() {
		for (int i = 1; i < (regionals.size()); i++) {
			for (int j = 0; j < schoolList.size(); j++) {
				if (!(schoolList.get(j).getRegionalNum().compareToIgnoreCase("null") == 0)) {
					Integer num = Integer.parseInt(schoolList.get(j).getRegionalNum());				
					if (num == i) {
						if(schoolList.get(j).getRegionalsHost().compareToIgnoreCase("true")==0) {
							regionals.get(i).setHost(schoolList.get(j));
						}					
						regionals.get(i).participating.add(schoolList.get(j));
						}
					
					}
				}
			}	
	}
	
	
	public void semiSorter() {
		for (int i = 1; i < semiState.size(); i++) {
			for (int j = 0; j < schoolList.size(); j++) {
				if (!(schoolList.get(j).getSemi_stateNum().compareToIgnoreCase("null") == 0)) {	
					Integer num = Integer.parseInt(schoolList.get(j).getSemi_stateNum());
					if (num == i) {
						if(schoolList.get(j).getSemiStateHost().compareToIgnoreCase("true")==0) {
							semiState.get(i).setHost(schoolList.get(j));
						}
						semiState.get(i).participating.add(schoolList.get(j));

					}
				}
			}
		}


}	

	public ArrayList<Sectionals> getSectionals() {
		return sectionals;
	}

	public void setSectionals(ArrayList<Sectionals> sectionals) {
		this.sectionals = sectionals;
	}

	public ArrayList<Regionals> getRegionals() {
		return regionals;
	}

	public void setRegionals(ArrayList<Regionals> regionals) {
		this.regionals = regionals;
	}

	public ArrayList<SemiState> getSemiState() {
		return semiState;
	}

	public void setSemiState(ArrayList<SemiState> semiState) {
		this.semiState = semiState;
	}

	public Finals getFinals() {
		return finals;
	}

	public void setFinals(Finals finals) {
		this.finals = finals;
	}

	public ArrayList<School> getSchoolList() {
		return schoolList;
	}

	public void setSchoolList(ArrayList<School> schoolList) {
		this.schoolList = schoolList;
	}
	
	
	
}
