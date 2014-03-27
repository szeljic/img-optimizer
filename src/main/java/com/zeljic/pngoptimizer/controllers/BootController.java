package com.zeljic.pngoptimizer.controllers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import com.zeljic.pngoptimizer.imageitem.Item;
import com.zeljic.pngoptimizer.storage.Storage;

public class BootController implements Initializable
{
	private Storage storedFilesPaths = new Storage();

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
		if(tgType.getSelectedToggle() != null)
		{
			btnFiles.setSelected(true);
			tlpSelect.setText("Select Files");
		}
		else
		{
			btnDirectory.setSelected(true);
			tlpSelect.setText("Select Directory");
		}
	}

	@FXML
	private void btnDirectoryOnAction(ActionEvent e)
	{
		if(tgType.getSelectedToggle() != null)
		{
			btnDirectory.setSelected(true);
			tlpSelect.setText("Select Directory");
		}
		else
		{
			btnFiles.setSelected(true);
			tlpSelect.setText("Select Files");
		}
	}

	@FXML
	private void btnBrowseOnAction(ActionEvent e)
	{
		ArrayList<File> listOfFiles = new ArrayList<File>();
		if(btnFiles.isSelected())
		{
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			try
			{
				listOfFiles = new ArrayList<File>(fileChooser.showOpenMultipleDialog(new Stage()));
			}
			catch(Exception ex)
			{
			}
		}
		else{
			DirectoryChooser directoryChooser = new DirectoryChooser();
			directoryChooser.setTitle("Open Resource Directory");
			try
			{
				listOfFiles.add(directoryChooser.showDialog(new Stage()));
			}
			catch(Exception ex)
			{
			}
		}
		ArrayList<String> listOfFilesPaths = new ArrayList<String>();
		for(int i =0; i < listOfFiles.size(); i++)
		{
			listOfFilesPaths.add(listOfFiles.get(i).getPath());
		}
		storedFilesPaths.setStoragedPaths(listOfFilesPaths);
	}

}
