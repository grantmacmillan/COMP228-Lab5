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

    //Game
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

    //Player
    @FXML
    private TextField playerIdField;
    @FXML
    private TextField playerFnameField;
    @FXML
    private TextField playerLnameField;
    @FXML
    private TextField playerAddressField;
    @FXML
    private TextField playerPCField;
    @FXML
    private TextField playerProvinceField;
    @FXML
    private TextField playerNumField;
    @FXML
    private Button playerAddButton;
    @FXML
    private Button playerDeleteButton;
    @FXML
    private Button playerEditButton;
    @FXML
    private Button playerUpdateButton;
    @FXML
    private TableView playerTable;
    @FXML
    private TableColumn playerIdColumn;
    @FXML
    private TableColumn playerFnameColumn;
    @FXML
    private TableColumn playerLnameColumn;
    @FXML
    private TableColumn playerAddressColumn;
    @FXML
    private TableColumn playerPCColumn;
    @FXML
    private TableColumn playerProvinceColumn;
    @FXML
    private TableColumn playerNumColumn;

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

    public void onPlayerAdd(ActionEvent actionEvent) throws SQLException{
        DBUtil.insertPlayerData(parseInt(playerIdField.getText()),playerFnameField.getText(), playerLnameField.getText(),playerAddressField.getText(),playerPCField.getText(),playerProvinceField.getText(),Long.parseLong(playerNumField.getText()));
        populateData();
    }

    public void onPlayerDelete(ActionEvent actionEvent) throws SQLException {
        Player player = (Player) playerTable.getSelectionModel().getSelectedItem();
        DBUtil.delete("Player", player.getP_id(), "player_id");
        populateData();
    }

    public void onPlayerUpdate(ActionEvent actionEvent) {
    }

    public void onPlayerEdit(ActionEvent actionEvent) {
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


        //Player
        rs = DBUtil.query("Player", "SELECT * FROM");
        ObservableList<Player> players = FXCollections.observableArrayList();

        while (rs.next()){
            Player player = new Player(rs.getInt("player_id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"), rs.getString("postal_code"), rs.getString("province"), rs.getLong("phone_number"));
            players.add(player);
        }
        playerIdColumn.setCellValueFactory(new PropertyValueFactory("p_id"));
        playerFnameColumn.setCellValueFactory(new PropertyValueFactory("p_fName"));
        playerLnameColumn.setCellValueFactory(new PropertyValueFactory("p_lName"));
        playerAddressColumn.setCellValueFactory(new PropertyValueFactory("p_address"));
        playerPCColumn.setCellValueFactory(new PropertyValueFactory("p_postalCode"));
        playerProvinceColumn.setCellValueFactory(new PropertyValueFactory("p_province"));
        playerNumColumn.setCellValueFactory(new PropertyValueFactory("p_phoneNum"));

        playerTable.getItems().clear();
        playerTable.getItems().addAll(players);

        playerIdColumn.setSortType(TableColumn.SortType.ASCENDING);
        playerTable.getSortOrder().add(playerIdColumn);
        playerTable.sort();
    }
    public void onDrop(ActionEvent actionEvent) throws SQLException {
        DBUtil.dropTable("COMP228_012");
    }

    public void onCreate(ActionEvent actionEvent)throws SQLException  {
        DBUtil.createTable("COMP228_012");
    }


}