package com.example.checker19.model;

import com.google.gson.annotations.SerializedName;

public class Geometry{

	@SerializedName("location")
	private Location location;

	public Location getLocation(){
		return location;
	}
}