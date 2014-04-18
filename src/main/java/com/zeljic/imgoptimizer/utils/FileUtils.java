package com.zeljic.imgoptimizer.utils;

import com.zeljic.imgoptimizer.storage.TypeItem.Type;

public class FileUtils
{
	public static Type getTypeByName(String name)
	{
		int idx = name.lastIndexOf(".");
		String strExt = (idx == -1 ? null : name.substring(idx + 1, name.length())).toLowerCase();

		switch (strExt) {
		case "jpeg":
			return Type.JPEG;
		case "jpg":
			return Type.JPG;
		case "png":
			return Type.PNG;
		case "bmp":
			return Type.BMP;
		default:
			return Type.UNKNOWN;
		}
	}
}
