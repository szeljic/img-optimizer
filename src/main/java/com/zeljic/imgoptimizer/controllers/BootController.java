package com.zeljic.imgoptimizer.controllers;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import com.zeljic.imgoptimizer.storage.Item;
import com.zeljic.imgoptimizer.storage.Output;
import com.zeljic.imgoptimizer.storage.Storage;
import com.zeljic.imgoptimizer.uil.Loader;
import com.zeljic.imgoptimizer.utils.NumberUtils;

public class BootController implements Initializable
{

	@FXML
	private ToggleButton btnFiles, btnDirectory;

	@FXML
	private ToggleGroup tgType;

	@FXML
	private TextField txtLocation, txtOutputDirectory;

	@FXML
	private Button btnBrowse, btnOptimize, btnCancel, btnAll, btnNone, btnInverse, btnClearAll, btnBrowseOutput;

	@FXML
	private TableView<Item> tblMain;

	@FXML
	private TitledPane tlpSelect;

	@FXML
	private Label lblInfo;

	@FXML
	private ProgressBar pbMain;

	@FXML
	private TableColumn<Item, String> tbcName, tbcStatus;

	@FXML
	private TableColumn<Item, Item> tbcType;

	@FXML
	private TableColumn<Item, Boolean> tbcHash;

	@FXML
	private TableColumn<Item, Long> tbcSize;

	@Override
	public void initialize(URL url, ResourceBundle bundle)
	{
		prepareTblMain();
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
			List<File> fcResult = fileChooser.showOpenMultipleDialog(Loader.getInstance("Boot").getStage());

			if (fcResult != null && fcResult.size() > 0)
			{
				Storage.getInstance().clearStorage();
				fcResult.parallelStream().forEach(f -> tmpStorage.addItem(new Item(f)));

				txtLocation.setText(fcResult.get(0).getParentFile().getAbsolutePath());
			}
		} else
		{
			DirectoryChooser directoryChooser = new DirectoryChooser();
			directoryChooser.setTitle("Open Resource Directory");
			File folder = directoryChooser.showDialog(Loader.getInstance("Boot").getStage());

			ArrayList<String> allowed = new ArrayList<String>(Arrays.asList(".jpg", ".jpeg", ".png", ".bmp"));

			if (folder != null)
			{
				Storage.getInstance().clearStorage();
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

				txtLocation.setText(folder.getAbsolutePath());
			}
		}

		tblMain.setItems(tmpStorage.getObservableList());
	}

	@FXML
	private void btnAllOnAction(ActionEvent e)
	{
		tblMain.getItems().forEach(item -> item.getCheckProperty().set(true));
	}

	@FXML
	private void btnNoneOnAction(ActionEvent e)
	{
		tblMain.getItems().forEach(item -> item.getCheckProperty().set(false));
	}

	@FXML
	private void btnInverseOnAction(ActionEvent e)
	{
		tblMain.getItems().forEach(item -> item.getCheckProperty().set(!item.getCheckProperty().get()));
	}

	@FXML
	private void btnClearAllOnAction(ActionEvent e)
	{
		Storage.getInstance().clearStorage();
		tblMain.getItems().clear();
	}

	@FXML
	private void btnBrowseOutputOnAction(ActionEvent e)
	{
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Open Resource Directory");
		File folder = directoryChooser.showDialog(Loader.getInstance("Boot").getStage());

		if (folder != null && folder.isDirectory())
		{
			Output.outputDir = folder;
			txtOutputDirectory.setText( folder.getAbsolutePath() );
		}
	}

	private void prepareTblMain()
	{
		tbcName.setCellValueFactory(item -> item.getValue().getPathProperty());
		tbcSize.setCellValueFactory(item -> item.getValue().getSizeProperty().asObject());
		tbcType.setCellValueFactory(item -> item.getValue().getItemProperty());

		tbcHash.setCellValueFactory(item -> item.getValue().getCheckProperty());
		tbcHash.setCellFactory(CheckBoxTableCell.forTableColumn(tbcHash));

		tbcSize.setCellFactory(new Callback<TableColumn<Item,Long>, TableCell<Item,Long>>() {

			@Override
			public TableCell<Item, Long> call(TableColumn<Item, Long> column)
			{
				TableCell<Item, Long> cell = new TableCell<Item, Long>(){
					@Override
					protected void updateItem(Long value, boolean empty)
					{
						if(empty)
						{
							super.updateItem(value, empty);
							return;
						}

						setGraphic(new Label( NumberUtils.readableFileSize(value) ));

						super.updateItem(value, empty);
					}
				};

				cell.setAlignment(Pos.CENTER_RIGHT);

				return cell;
			}
		});

		tbcType.setCellFactory(new Callback<TableColumn<Item, Item>, TableCell<Item, Item>>() {

			@Override
			public TableCell<Item, Item> call(TableColumn<Item, Item> column)
			{
				TableCell<Item, Item> cell = new TableCell<Item, Item>() {
					@Override
					protected void updateItem(Item item, boolean empty)
					{
						if (empty)
						{
							setGraphic(null);
							return;
						}

						String path = "";

						switch (item.getFileTypeProperty().get()) {
						case JPG:
						case JPEG:
							path = "/gfx/ico-jpg.png";
							break;

						case PNG:
							path = "/gfx/ico-png.png";
							break;

						case BMP:
							path = "/gfx/ico-bmp.png";

						default:
							path = "/gfx/ico-unknown.png";
							break;
						}

						setGraphic(new ImageView(new Image(getClass().getResourceAsStream(path))));
					};
				};

				cell.setAlignment(Pos.CENTER);

				return cell;
			}
		});
	}
}