package com.weather.beans;
import java.util.HashMap;

public class City {
	private String name = "PARIS";
	private Integer temperature;
	private String conditions;
	private String icon;
	private Integer probaRain;
	private Integer wind;
	private HashMap<String, String>[] nextDays;
	
	public String getName() {
		return this.name;
	}
	public Integer getTemperature() {
		return this.temperature;
	}
	public String getConditions() {
		return this.conditions;
	}
	public String getIcon() {
		return this.icon;
	}
	public HashMap<String, String>[] getNextDays() {
		return this.nextDays;
	}
	public Integer getProbaRain() {
		return probaRain;
	}
	public Integer getWind() {
		return wind;
	}
	public void setName(String n) {
		this.name = n;
	}
	public void setTemperature(Integer t) {
		this.temperature = t;
	}
	public void setConditions(String c) {
		this.conditions = c;
	}
	public void setIcon(String i) {
		this.icon = i;
	}
	public void setNextDays(HashMap<String, String>[] hm) {
		this.nextDays = hm;
	}
	public void setProbaRain(Integer probaRain) {
		this.probaRain = probaRain;
	}
	public void setWind(Integer wind) {
		this.wind = wind;
	}
}
