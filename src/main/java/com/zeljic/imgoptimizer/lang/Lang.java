package com.zeljic.imgoptimizer.lang;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

import org.apache.log4j.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Lang
{
	private static HashMap<String, StringProperty> _keys = new HashMap<String, StringProperty>();

	public static void setKeys(HashMap<String, String> keys)
	{
		keys.entrySet().parallelStream().forEach(e -> setKey(e.getKey(), e.getValue()));
	}

	public static void setKeysByURL(URL url)
	{
		Properties props = new Properties();

		try
		{
			props.load(url.openStream());
		} catch (IOException e)
		{
			Logger.getRootLogger().error(e);
		}

		props.entrySet().parallelStream().forEach(e -> setKey(e.getKey().toString(), e.getValue().toString()));
	}

	private static void setKey(String key, String value)
	{
		if(_keys.containsKey(key))
			_keys.get(key).set(value);

		_keys.put(key, new SimpleStringProperty(value));
	}

	public static StringProperty get(String key)
	{
		if (!_keys.containsKey(key))
			setKey(key, key);

		return _keys.get(key);
	}
}
