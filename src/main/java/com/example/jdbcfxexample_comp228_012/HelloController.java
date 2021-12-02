package com.example.jdbcfxexample_comp228_012;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;

import static java.lang.Integer.parseInt;

public class HelloController {
    @FXML
    private TextField studentIdField;
    @FXML
    private TextField studentNameField;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button dropButton;
    @FXML
    private Button createButton;
    @FXML
    private TableView table;
    @FXML
    private TableColumn studentIdColumn;
    @FXML
    private TableColumn studentNameColumn;

    public void initialize() throws SQLException{
        populateData();

    }

    public void onAdd(ActionEvent actionEvent) throws SQLException {
        DBUtil.insertData("COMP228_012", parseInt(studentIdField.getText()),studentNameField.getText());
        populateData();
    }

    public void onDelete(ActionEvent actionEvent) throws SQLException {
        Student student = (Student) table.getSelectionModel().getSelectedItem();
        DBUtil.delete("COMP228_012", student.getS_id());
        populateData();
    }

    public void onDrop(ActionEvent actionEvent) throws SQLException {
        DBUtil.dropTable("COMP228_012");
    }

    public void onCreate(ActionEvent actionEvent)throws SQLException  {
        DBUtil.createTable("COMP228_012");
    }

    public void populateData() throws SQLException{
        ResultSet rs = DBUtil.query("COMP228_012", "SELECT * FROM");
        //create list of objects that we want to show in the table
        ObservableList<Student> students = FXCollections.observableArrayList();

        //add objects one by one to the list
        while (rs.next()){
            Student student = new Student(rs.getInt("s_id"), rs.getString("s_name"));
            students.add(student);
        }

        //assign each attribute of the Student class (entity) to each column of the table
        studentIdColumn.setCellValueFactory(new PropertyValueFactory("s_id"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory("s_name"));

        //clear the table
        table.getItems().clear();
        //add data to the table
        table.getItems().addAll(students);

        //sort the table by id

        studentIdColumn.setSortType(TableColumn.SortType.ASCENDING);
        table.getSortOrder().add(studentIdColumn);
        table.sort();

    }
}