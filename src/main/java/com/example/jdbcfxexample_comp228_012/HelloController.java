package com.example.jdbcfxexample_comp228_012;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        DBUtil.insertData("Game", parseInt(studentIdField.getText()),studentNameField.getText());
        populateData();
    }

    public void onGameDelete(ActionEvent actionEvent) throws SQLException {
        Game game = (Game) table.getSelectionModel().getSelectedItem();
        DBUtil.delete("Game", game.getG_id(), "game_id");
        populateData();
    }

    public void populateData() throws SQLException{
        ResultSet rs = DBUtil.query("Game", "SELECT * FROM");
        //create list of objects that we want to show in the table
        ObservableList<Game> games = FXCollections.observableArrayList();

        //add objects one by one to the list
        while (rs.next()){
            Game game = new Game(rs.getInt("game_id"), rs.getString("game_title"));
            games.add(game);
        }

        //assign each attribute of the Student class (entity) to each column of the table
        studentIdColumn.setCellValueFactory(new PropertyValueFactory("g_id"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory("g_title"));

        //clear the table
        table.getItems().clear();
        //add data to the table
        table.getItems().addAll(games);

        //sort the table by id

        studentIdColumn.setSortType(TableColumn.SortType.ASCENDING);
        table.getSortOrder().add(studentIdColumn);
        table.sort();

    }
    public void onDrop(ActionEvent actionEvent) throws SQLException {
        DBUtil.dropTable("COMP228_012");
    }

    public void onCreate(ActionEvent actionEvent)throws SQLException  {
        DBUtil.createTable("COMP228_012");
    }
}