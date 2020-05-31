package com.example.covid19.models;

public class LocationStats {
    
	public String province_state;
    public String country_region;
    private int confirmed;
    private int deaths;
    private int recovered;
    public int latestTotalCases;
    
	public String getProvince_State() {
		return province_state;
	}
	
	public void setProvince_State(String province_state) {
		this.province_state = province_state;
	}
	
	public String getCountry_Region() {
		return country_region;
	}
	
	public void setCountry_Region(String country_region) {
		this.country_region = country_region;
	}
	
	/*
	 * public int getLatestTotalCases() { return latestTotalCases; }
	 * 
	 * public void setLatestTotalCases(int latestTotalCases) { this.latestTotalCases
	 * = latestTotalCases; }
	 */

	public int getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}

	public int getRecovered() {
		return recovered;
	}

	public void setRecovered(int recovered) {
		this.recovered = recovered;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	
	@Override
    public String toString() {
        return "LocationStats{" +
                "province_state='" + province_state + '\'' +
                ", country_region='" + country_region + '\'' +
               // ", latestTotalCases=" + latestTotalCases + '\'' +
                ", confirmed='" + confirmed + '\'' +
                ", recovered='" + recovered + '\''+
                ", deaths='" + deaths +                  
                '}';
    }

}
