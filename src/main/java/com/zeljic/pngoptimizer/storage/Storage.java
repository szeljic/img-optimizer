package com.zeljic.pngoptimizer.storage;

import java.util.ArrayList;

public class Storage
{
	private ArrayList<Item> storagedPaths = new ArrayList<Item>();
	private static Storage instance;

	protected Storage()
	{
	}

	public ArrayList<Item> getStoragedPaths()
	{
		return storagedPaths;
	}

	public void setStoragedPaths(ArrayList<Item> storagedPaths)
	{
		this.storagedPaths = storagedPaths;
	}

	public void clearStorage()
	{
		storagedPaths.clear();
	}

	public void addItem(Item item)
	{
		storagedPaths.add(item);
	}

	public static Storage getInstance()
	{
		return instance == null ? (instance = new Storage()) : instance;
	}
}