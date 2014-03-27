package com.zeljic.pngoptimizer.storage;

import java.util.ArrayList;

public class Storage 
{
	private ArrayList<String> storagedPaths = new ArrayList<String>();

	public ArrayList<String> getStoragedPaths() 
	{
		return storagedPaths;
	}

	public void setStoragedPaths(ArrayList<String> storagedPaths) 
	{
		this.storagedPaths = storagedPaths;
	}
}
