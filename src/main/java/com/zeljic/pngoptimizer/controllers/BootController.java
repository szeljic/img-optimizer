package com.zeljic.pngoptimizer.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.zeljic.pngoptimizer.imageitem.Item;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

public class BootController implements Initializable
{
	
	@FXML
	private RadioButton rbTypeFiles, rbTypeDirectory;
	
	@FXML
	private TextField txtLocation;
	
	@FXML
	private Button btnBrowse, btnOptimize, btnCancel;
	
	@FXML
	private TableView<Item> tblMain;
	
	@FXML
	private TitledPane tlpSelect;
	
	@FXML
	private Label lblInfo;
	
	@FXML
	private ProgressBar pbMain;

	@Override
	public void initialize(URL url, ResourceBundle bundle)
	{
		
	}

	
	
}
