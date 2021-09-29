package com.example.checker19.model;

import com.google.gson.annotations.SerializedName;

public class ResultsItem{

	@SerializedName("name")
	private String name;

	@SerializedName("opening_hours")
	private OpeningHours openingHours;

	@SerializedName("geometry")
	private Geometry geometry;

	@SerializedName("vicinity")
	private String vicinity;

	public String getName(){
		return name;
	}

	public OpeningHours getOpeningHours(){
		return openingHours;
	}

	public Geometry getGeometry(){
		return geometry;
	}

	public String getVicinity(){
		return vicinity;
	}
}