
package com.techoffice.javafx.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.BadSqlGrammarException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class JavaFxTableViewUtil {
	
	/**
	 * Set the result map to the table view. The key would be the header of Table View
	 * 
	 * @param tableView JavaFx Table View
	 * @param results Result Map 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static TableView tableViewSetListOfMap(TableView tableView, List<Map<String, Object>> results){
		if (results.size() > 0){
			List<TableColumn> tableColumnList = new ArrayList<TableColumn>();
			Set<String> keySet = results.get(0).keySet();
			for (final String key: keySet){
		        TableColumn<Map<String, Object>, String> tableColumn = new TableColumn<Map<String, Object>, String>(key);
		        tableColumn.setCellValueFactory(new Callback<CellDataFeatures<Map<String, Object>, String>, ObservableValue<String>>(){
					public ObservableValue<String> call(CellDataFeatures<Map<String, Object>, String> param) {
						SimpleStringProperty simpleStringProperty;
						if (param.getValue().get(key) != null){
							simpleStringProperty = new SimpleStringProperty(param.getValue().get(key).toString());
						}else {
							simpleStringProperty = new SimpleStringProperty("(null)");
						}
						return simpleStringProperty;
					}
				});
		        tableColumnList.add(tableColumn);
			}
	        tableView.getColumns().addAll(tableColumnList);
	        ObservableList<Map<String, Object>> items = FXCollections.observableArrayList(results);
	        tableView.setItems(items);
		}
		return tableView;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static TableView tableViewSetBadSqlGrammarException(TableView tableView, BadSqlGrammarException exception){
		tableView.getColumns().clear();
        TableColumn exceptionTableColumn = new TableColumn("Exception Message");
        exceptionTableColumn.setCellValueFactory(new PropertyValueFactory("message"));
        ObservableList<Throwable> data = FXCollections.observableArrayList(exception.getRootCause());
        tableView.getColumns().add(exceptionTableColumn);
        tableView.setItems(data);
		return tableView;
	}
	
}
