package com.zeljic.imgoptimizer.storage;

import java.io.File;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import com.zeljic.imgoptimizer.storage.TypeItem.Type;
import com.zeljic.imgoptimizer.utils.FileUtils;

public class Item
{
	private StringProperty pathProperty;
	private LongProperty sizeProperty;
	private ObjectProperty<Type> fileTypeProperty;
	private ObjectProperty<File> fileProperty;
	private BooleanProperty checked;
	private ObjectProperty<Item> instance;

	public Item(File file)
	{
		getFileProperty().set(file);
		getPathProperty().set(file.getAbsolutePath());
		getSizeProperty().set(file.length());
		getFileTypeProperty().set(FileUtils.getExtension(getPathProperty().get()));
		getCheckProperty().set(true);

		getItemProperty().set(this);
	}

	public StringProperty getPathProperty()
	{
		if (pathProperty == null)
			pathProperty = new SimpleStringProperty();

		return pathProperty;
	}

	public LongProperty getSizeProperty()
	{
		if (sizeProperty == null)
			sizeProperty = new SimpleLongProperty();

		return sizeProperty;
	}

	public ObjectProperty<Type> getFileTypeProperty()
	{
		if (fileTypeProperty == null)
			fileTypeProperty = new SimpleObjectProperty<Type>();

		return fileTypeProperty;
	}

	public ObjectProperty<File> getFileProperty()
	{
		if (fileProperty == null)
			fileProperty = new SimpleObjectProperty<File>();

		return fileProperty;
	}

	public ObjectProperty<Item> getItemProperty()
	{
		if (instance == null)
			instance = new SimpleObjectProperty<>();

		return instance;
	}

	public BooleanProperty getCheckProperty()
	{
		if (checked == null)
			checked = new SimpleBooleanProperty();

		return checked;
	}
}
