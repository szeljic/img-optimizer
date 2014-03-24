package com.zeljic.pngoptimizer;

import javafx.application.Application;
import javafx.stage.Stage;

import com.zeljic.pngoptimizer.uil.Loader;

public class Boot extends Application
{

	@Override
	public void start(Stage stage) throws Exception
	{
		Loader loader = Loader.setInstance("Boot", stage, getClass().getResource("/fxml/Boot.fxml"));
		stage.setScene(loader.getScene());
		stage.setTitle("PNG Optimizer");
		
		stage.show();
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}

}
