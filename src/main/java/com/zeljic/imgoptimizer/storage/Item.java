package com.zeljic.imgoptimizer.storage;

import java.io.File;

public class Item
{
	private String path;
	private File file;
	private long size;

	public Item(File file)
	{
		this.file = file;
		this.path = file.getAbsolutePath();
		this.size = file.length();
	}

	public String getPath()
	{
		return path;
	}

	public long getSize()
	{
		return size;
	}

	public void setFile(File file)
	{
		this.file = file;
	}

	public File getFile()
	{
		return file;
	}
}
