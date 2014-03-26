package com.zeljic.pngoptimizer.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import com.zeljic.pngoptimizer.imageitem.Item;

public class BootController implements Initializable
{

	@FXML
	private ToggleButton btnFiles, btnDirectory;

	@FXML
	private ToggleGroup tgType;

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

	@FXML
	private void btnFilesOnAction(ActionEvent e)
	{
	    btnFiles.setSelected(true);
	    tlpSelect.setText("Files");
	}

	@FXML
	private void btnDirectoryOnAction(ActionEvent e)
	{
		btnDirectory.setSelected(true);
		tlpSelect.setText("Directory");
	}

}
