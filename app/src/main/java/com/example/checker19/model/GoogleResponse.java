package com.example.checker19.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class GoogleResponse{

	@SerializedName("results")
	private List<ResultsItem> results;

	public List<ResultsItem> getResults(){
		return results;
	}
}