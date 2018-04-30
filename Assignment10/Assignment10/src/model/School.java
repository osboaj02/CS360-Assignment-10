package model;

import java.io.Serializable;

public class School implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name; // 1
	private String nineth;// 2
	private String tenth;// 3
	private String eleventh;// 4
	private String twelfth;// 5
	private String total;// 6
	private String address;// 7
	private String city;// 8
	private String state;// 9
	private String areaCode;// 10
	private String phoneNo;// 11
	private String sectionalNum;// 12
	private String regionalNum;// 13
	private String semi_stateNum;// 14
	private String sectionalHost;// 15
	private String regionalsHost;// 16
	private String semiStateHost;// 17
	private String finals;// 18
	private Float lat;
	private Float lng;
	private double Distance;

	public School(String name, String nineth, String tenth, String eleventh, String twelfth, String total,
			String address, String city, String state, String areaCode, String sectionalsNum, String regionalsNum,
			String semiStateNum, String sectionalHost, String regionalHost, String semi_stateHost) {

		this.name = name; // 0
		this.nineth = nineth;// 1
		this.tenth = tenth;// 2
		this.eleventh = eleventh;// 3
		this.twelfth = twelfth;// 4
		this.total = total;// 5
		this.address = address;// 6
		this.city = city;// 7
		this.state = state;// 8
		this.areaCode = areaCode;// 9
		this.sectionalNum = sectionalsNum;// 10
		this.regionalNum = regionalsNum;// 11
		this.semi_stateNum = semiStateNum;// 12
		this.sectionalHost = sectionalHost;// 13
		this.regionalsHost = regionalHost;// 14
		this.semiStateHost = semi_stateHost;// 15

	}
	
	
	

	public double getDistance() {
		return Distance;
	}




	public void setDistance(double distance) {
		Distance = distance;
	}




	public Float getLat() {
		return lat;
	}



	public void setLat(Float lat) {
		this.lat = lat;
	}



	public Float getLng() {
		return lng;
	}



	public void setLng(Float lng) {
		this.lng = lng;
	}



	public String toString() {
		return name + " " +  lat + " " + lng;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNineth() {
		return nineth;
	}

	public void setNineth(String nineth) {
		this.nineth = nineth;
	}

	public String getTenth() {
		return tenth;
	}

	public void setTenth(String tenth) {
		this.tenth = tenth;
	}

	public String getEleventh() {
		return eleventh;
	}

	public void setEleventh(String eleventh) {
		this.eleventh = eleventh;
	}

	public String getTwelfth() {
		return twelfth;
	}

	public void setTwelfth(String twelfth) {
		this.twelfth = twelfth;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getSectionalNum() {
		return sectionalNum;
	}

	public void setSectionalNum(String sectionalNum) {
		this.sectionalNum = sectionalNum;
	}

	public String getRegionalNum() {
		return regionalNum;
	}

	public void setRegionalNum(String regionalNum) {
		this.regionalNum = regionalNum;
	}

	public String getSemi_stateNum() {
		return semi_stateNum;
	}

	public void setSemi_stateNum(String semi_stateNum) {
		this.semi_stateNum = semi_stateNum;
	}

	public String getSectionalHost() {
		return sectionalHost;
	}

	public void setSectionalHost(String sectionalHost) {
		this.sectionalHost = sectionalHost;
	}

	public String getRegionalsHost() {
		return regionalsHost;
	}

	public void setRegionalsHost(String regionalsHost) {
		this.regionalsHost = regionalsHost;
	}

	public String getSemiStateHost() {
		return semiStateHost;
	}

	public void setSemiStateHost(String semiStateHost) {
		this.semiStateHost = semiStateHost;
	}

	public String getFinals() {
		return finals;
	}

	public void setFinals(String finals) {
		this.finals = finals;
	}
	
	
	
	

}
