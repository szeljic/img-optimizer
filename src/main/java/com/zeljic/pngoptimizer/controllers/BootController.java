package com.zeljic.pngoptimizer.controllers;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import com.zeljic.pngoptimizer.uil.Loader;

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
		List<File> lof;
		if(btnFiles.isSelected())
		{
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(
					new FileChooser.ExtensionFilter("All Images", "*.png", "*.jpg", "*.bmp", "*.jpeg"),
	                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
	                new FileChooser.ExtensionFilter("PNG", "*.png"),
	                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
	                new FileChooser.ExtensionFilter("JPEG", "*.jpeg")
	            );
			lof = fileChooser.showOpenMultipleDialog(Loader.getInstance("Boot").getStage());
		}
		else
		{
			DirectoryChooser directoryChooser = new DirectoryChooser();
			directoryChooser.setTitle("Open Resource Directory");
			File folder = directoryChooser.showDialog(Loader.getInstance("Boot").getStage());
			File[] listOfFiles = folder.listFiles();
			String name;
			lof = new ArrayList<File>();
			for(int i = 0; i < listOfFiles.length; i++)
			{
				if(listOfFiles[i].isFile())
				{
					name = listOfFiles[i].getName();
					if(name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".bmp") || name.endsWith(".jpeg"))
					{
						lof.add(listOfFiles[i]);
					}
				}
			}
		}
		ArrayList<String> listOfFilesPaths = new ArrayList<String>();		
		if(lof != null)
		{
			for(int i = 0; i < lof.size(); i++)
			{
				listOfFilesPaths.add(lof.get(i).getAbsolutePath());
			}
		}
		for(int i = 0; i < listOfFilesPaths.size(); i++)
		{
			System.out.println(listOfFilesPaths.get(i));
		}
		storedFilesPaths.setStoragedPaths(listOfFilesPaths);
	}

}
