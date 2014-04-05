package com.zeljic.imgoptimizer.storage;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Storage
{
	private ArrayList<Item> items = new ArrayList<Item>();
	private static Storage instance;

	protected Storage()
	{
	}

	public ArrayList<Item> getStoragedPaths()
	{
		return items;
	}

	public void setStoragedPaths(ArrayList<Item> storagedPaths)
	{
		this.items = storagedPaths;
	}

	public void clearStorage()
	{
		items.clear();
	}

	public void addItem(Item item)
	{
		items.add(item);
	}

	public static Storage getInstance()
	{
		return instance == null ? (instance = new Storage()) : instance;
	}

	public ObservableList<Item> getObservableList()
	{
		return FXCollections.observableArrayList(items);
	}
}