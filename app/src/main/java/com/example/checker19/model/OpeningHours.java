package com.example.checker19.model;

import com.google.gson.annotations.SerializedName;

public class OpeningHours{

	@SerializedName("open_now")
	private boolean openNow;

	public boolean isOpenNow(){
		return openNow;
	}
}