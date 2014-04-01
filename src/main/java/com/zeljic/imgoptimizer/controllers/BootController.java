package com.zeljic.imgoptimizer.controllers;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.zeljic.imgoptimizer.storage.Item;
import com.zeljic.imgoptimizer.storage.Storage;
import com.zeljic.imgoptimizer.uil.Loader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

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

	@FXML
	private TableColumn<Item, String> tbcHash, tbcName, tbcSize, tbcStatus;

	@Override
	public void initialize(URL url, ResourceBundle bundle)
	{

	}

	@FXML
	private void btnFilesOnAction(ActionEvent e)
	{
		if (tgType.getSelectedToggle() != null)
		{
			btnFiles.setSelected(true);
			tlpSelect.setText("Select Files");
		} else
		{
			btnDirectory.setSelected(true);
			tlpSelect.setText("Select Directory");
		}
	}

	@FXML
	private void btnDirectoryOnAction(ActionEvent e)
	{
		if (tgType.getSelectedToggle() != null)
		{
			btnDirectory.setSelected(true);
			tlpSelect.setText("Select Directory");
		} else
		{
			btnFiles.setSelected(true);
			tlpSelect.setText("Select Files");
		}
	}

	@FXML
	private void btnBrowseOnAction(ActionEvent e)
	{
		Storage tmpStorage = Storage.getInstance();

		if (btnFiles.isSelected())
		{
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open Resource File");
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.jpg", "*.jpeg", "*.png", "*.bmp"));
			fileChooser.showOpenMultipleDialog(Loader.getInstance("Boot").getStage()).parallelStream().forEach(f -> tmpStorage.addItem(new Item(f)));
		} else
		{
			DirectoryChooser directoryChooser = new DirectoryChooser();
			directoryChooser.setTitle("Open Resource Directory");
			File folder = directoryChooser.showDialog(Loader.getInstance("Boot").getStage());

			ArrayList<String> allowed = new ArrayList<>(Arrays.asList(".jpg", ".jpeg", ".png", ".bmp"));

			Arrays.asList(folder.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name)
				{
					for (String ext : allowed)
						if (name.endsWith(ext))
							return true;

					return false;
				}
			})).parallelStream().forEach(f -> tmpStorage.addItem(new Item(f)));
		}
	}
}