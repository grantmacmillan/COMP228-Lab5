package com.example.jdbcfxexample_comp228_012;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class HelloController {

    //Game
    @FXML
    private TextField gameIdField;
    @FXML
    private TextField gameNameField;
    @FXML
    private Button addButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button dropButton;
    @FXML
    private Button createButton;
    @FXML
    private TableView gameTable;
    @FXML
    private TableColumn gameIdColumn;
    @FXML
    private TableColumn gameNameColumn;

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
    
    //Player + Game
    @FXML
    private DatePicker playerGameDate;
    @FXML
    private TextField playerGameScore;
    @FXML
    private TextField playerGameId;
    @FXML
    private ComboBox playerGameSelectPlayer;
    @FXML
    private ComboBox playerGameSelectGame;
    @FXML
    private TableView playerGameTable;
    @FXML
    private TableColumn playerGamePGIDColumn;
    @FXML
    private TableColumn playerGamePIDColumn;
    @FXML
    private TableColumn playerGameGIDColumn;
    @FXML
    private TableColumn playerGameScoreColumn;
    @FXML
    private TableColumn playerGameDateColumn;

    //Ratings
    @FXML
    private ComboBox ratePlayerCombo;
    @FXML
    private TableView rateTable;
    @FXML
    private TableColumn rateName;
    @FXML
    private TableColumn rateScore;
    @FXML
    private TableColumn rateDate;

    public void initialize() throws SQLException{
        populateData();

    }

    public void onAdd(ActionEvent actionEvent) throws SQLException {
        DBUtil.insertData("Game", parseInt(gameIdField.getText()),gameNameField.getText());
        populateData();
    }

    public void onGameDelete(ActionEvent actionEvent) throws SQLException {
        Game game = (Game) gameTable.getSelectionModel().getSelectedItem();
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
        clearTextFields();
    }

    public void onPlayerUpdate(ActionEvent actionEvent) throws SQLException{
        DBUtil.updatePlayerData(parseInt(playerIdField.getText()),playerFnameField.getText(), playerLnameField.getText(),playerAddressField.getText(),playerPCField.getText(),playerProvinceField.getText(),Long.parseLong(playerNumField.getText()));
        populateData();
    }
    public void onPlayerGameAdd(ActionEvent actionEvent) throws SQLException{
        Integer g_id = 0, p_id = 0;

        for (int i = 0; i < gameTable.getItems().size(); i++) {
            Game game = (Game)gameTable.getItems().get(i);
            if(game.getG_title().equals(playerGameSelectGame.getValue())) {
                g_id = game.getG_id();
            }
        }
        for (int i = 0; i < playerTable.getItems().size(); i++) {
            Player player = (Player)playerTable.getItems().get(i);
            if(player.getP_fName().equals(playerGameSelectPlayer.getValue())) {
                p_id = player.getP_id();
            }
        }
        DBUtil.insertPlayerGameData(parseInt(playerGameId.getText()), g_id,p_id, Date.valueOf(playerGameDate.getValue()), parseInt(playerGameScore.getText()));
        populateData();
    }

    public void onPlayerGameDelete(ActionEvent actionEvent) throws SQLException{
        PlayerAndGame playerAndGame = (PlayerAndGame) playerGameTable.getSelectionModel().getSelectedItem();
        DBUtil.delete("PlayerAndGame", playerAndGame.getP_g_id(), "player_game_id");
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
        gameIdColumn.setCellValueFactory(new PropertyValueFactory("g_id"));
        gameNameColumn.setCellValueFactory(new PropertyValueFactory("g_title"));

        //clear the gameTable
        gameTable.getItems().clear();
        //add data to the gameTable
        gameTable.getItems().addAll(games);

        //sort the table by id

        gameIdColumn.setSortType(TableColumn.SortType.ASCENDING);
        gameTable.getSortOrder().add(gameIdColumn);
        gameTable.sort();


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


        //Player Game Table
        rs = DBUtil.query("PlayerAndGame", "SELECT * FROM");
        ObservableList<PlayerAndGame> playerAndGames = FXCollections.observableArrayList();
        while (rs.next()){
            PlayerAndGame playerAndGame = new PlayerAndGame(rs.getInt("player_game_id"), rs.getInt("game_id"), rs.getInt("player_id"), rs.getDate("playing_date"), rs.getInt("score"));
            playerAndGames.add(playerAndGame);
        }
        playerGamePGIDColumn.setCellValueFactory(new PropertyValueFactory("p_g_id"));
        playerGameGIDColumn.setCellValueFactory(new PropertyValueFactory("g_id"));
        playerGamePIDColumn.setCellValueFactory(new PropertyValueFactory("p_id"));
        playerGameScoreColumn.setCellValueFactory(new PropertyValueFactory("score"));
        playerGameDateColumn.setCellValueFactory(new PropertyValueFactory("playing_date"));

        playerGameTable.getItems().clear();
        playerGameTable.getItems().addAll(playerAndGames);

        playerGamePGIDColumn.setSortType(TableColumn.SortType.ASCENDING);
        playerGameTable.getSortOrder().add(playerGamePGIDColumn);
        playerGameTable.sort();
        //rate game combo boxes

        //games combobox
        playerGameSelectGame.getItems().clear();
        playerGameSelectPlayer.getItems().clear();
        ArrayList<String> gameNames = new ArrayList<>();

        for(Object row : gameTable.getItems()){
            gameNames.add((String)gameNameColumn.getCellObservableValue(row).getValue());
        }
        playerGameSelectGame.getItems().addAll(gameNames);

        //player combobox
        ArrayList<String> playerNames = new ArrayList<>();

        for(Object row : playerTable.getItems()){
            playerNames.add((String)playerFnameColumn.getCellObservableValue(row).getValue());
        }
        playerGameSelectPlayer.getItems().addAll(playerNames);

       // playerGameSelectGame.getItems().add()
        ratePlayerCombo.getItems().clear();
        ratePlayerCombo.getItems().addAll(playerNames);



    }







    public void onDrop(ActionEvent actionEvent) throws SQLException {
        DBUtil.dropTable("COMP228_012");
    }

    public void onCreate(ActionEvent actionEvent)throws SQLException  {
        DBUtil.createTable("COMP228_012");
    }


    public void onPlayerSelected(MouseEvent mouseEvent) throws SQLException{
        Player player = (Player) playerTable.getSelectionModel().getSelectedItem();

        playerIdField.setText(player.getP_id().toString());
        playerFnameField.setText(player.getP_fName());
        playerLnameField.setText(player.getP_lName());
        playerAddressField.setText(player.getP_address());
        playerPCField.setText(player.getP_postalCode());
        playerProvinceField.setText(player.getP_province());
        playerNumField.setText(player.getP_phoneNum().toString());
    }
    public void onRatePlayerChosen(ActionEvent actionEvent) {
        //Use name of player to get p_id
        //use p_id to get PlayerAndGame rows for that p_id
        Integer p_id = 0;
        ArrayList<PlayerAndGame> playerGameList = new ArrayList<>();
        ArrayList<PlayerRating> playerRatings = new ArrayList<>();
        for (int i = 0; i < playerTable.getItems().size(); i++) {
            Player player = (Player)playerTable.getItems().get(i);
            if(player.getP_fName().equals(ratePlayerCombo.getValue())) {
                p_id = player.getP_id();
            }
        }
        for (int i = 0; i < playerGameTable.getItems().size(); i++) {
            PlayerAndGame playerGame = (PlayerAndGame)playerGameTable.getItems().get(i);
            if(playerGame.getP_id().equals(p_id)) {
                playerGameList.add(playerGame);
            }
        }

        for(int i = 0; i < playerGameList.size(); i++) {
            for (int j = 0; j < gameTable.getItems().size(); j++) {
                Game game = (Game)gameTable.getItems().get(j);
                if(game.getG_id().equals(playerGameList.get(i).getG_id())) {
                    PlayerRating rating = new PlayerRating(playerGameList.get(i).getPlaying_date(), playerGameList.get(i).getScore(), game.getG_title());
                    playerRatings.add(rating);
                }
            }
            rateName.setCellValueFactory(new PropertyValueFactory("g_name"));
            rateScore.setCellValueFactory(new PropertyValueFactory("score"));
            rateDate.setCellValueFactory(new PropertyValueFactory("playing_date"));

            rateTable.getItems().clear();
            rateTable.getItems().addAll(playerRatings);

            rateDate.setSortType(TableColumn.SortType.DESCENDING);
            rateTable.getSortOrder().add(rateDate);
            rateTable.sort();

        }

    }

    public void clearTextFields(){
        playerIdField.clear();
        playerFnameField.clear();
        playerLnameField.clear();
        playerAddressField.clear();
        playerPCField.clear();
        playerProvinceField.clear();
        playerNumField.clear();
    }



}