package com.zeljic.pngoptimizer.uil;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Loader
{

	private URL _path;
	private Stage _stage;
	private Scene _scene;
	private FXMLLoader _loader;
	
	private static HashMap<String, Loader> _loaders = new HashMap<String, Loader>();

	protected Loader(String name, Stage stage, URL path)
	{
		_path = path;
		_stage = stage;

		_loader = new FXMLLoader(_path);

		try
		{
			_scene = new Scene(_loader.load());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static Loader setInstance(String name, Stage stage, URL path)
	{
		Loader tmp = new Loader(name, stage, path);
		_loaders.put(name, tmp);
		
		return tmp;
	}
	
	public static Loader getInstance(String name)
	{
		return _loaders.get(name);
	}

	public <T> T getController(Class<T> clazz)
	{
		return clazz.cast(_loader.getController());
	}

	public Stage getStage()
	{
		return _stage;
	}

	public Scene getScene()
	{
		return _scene;
	}

}
