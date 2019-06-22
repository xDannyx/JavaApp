package project;

import com.mysql.cj.protocol.Resultset;
import dbconnect.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import paliwo.PaliwoDetails;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public Label textLabel;

    @FXML
    public TableColumn<PaliwoDetails, String> tableName;

    @FXML
    public TableColumn<PaliwoDetails, String> tableCost;

    @FXML
    public TableView<PaliwoDetails> tablePaliwo;

    private ObservableList<PaliwoDetails> data;

    double x = 0;
    double y = 0;

    @FXML
    public Button closeButton;

    @FXML
    void dragged(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();

        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    //funkcja do zamkniecia aplikacji
    public void closeWindow(javafx.event.ActionEvent actionEvent) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //zmiana na panel rejestracji
    @FXML
    public void switchToRegisterPanel(ActionEvent actionEvent) throws IOException {
        Parent blah = FXMLLoader.load(getClass().getResource("registerPanel.fxml"));
        Scene scene = new Scene(blah);
        scene.setFill(Color.TRANSPARENT);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }


    //zmiana na panel logowania
    @FXML
    public void switchToLoginPanel(ActionEvent actionEvent) throws IOException {
        Parent blah = FXMLLoader.load(getClass().getResource("loginPanel.fxml"));
        Scene scene = new Scene(blah);
        scene.setFill(Color.TRANSPARENT);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    //zmiana na glowny panel
    @FXML
    public void switchToEditPanel(ActionEvent actionEvent) throws IOException {
        Parent blah = FXMLLoader.load(getClass().getResource("editPanel.fxml"));
        Scene scene = new Scene(blah);
        scene.setFill(Color.TRANSPARENT);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    //zmiana na panel edycji
    @FXML
    public void switchToMainPanel(ActionEvent actionEvent) throws IOException {
        Parent blah = FXMLLoader.load(getClass().getResource("mainPanel.fxml"));
        Scene scene = new Scene(blah);
        scene.setFill(Color.TRANSPARENT);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    //przejscie do glownego poanelu PO zalogowaniu
    /** TO JEST DO EDYCJI, PÓKI CO TYLKO TYMCZASOWO ŻEBY ZMIENIĆ PANEL **/
    @FXML
    public void afterLogin(ActionEvent actionEvent) throws IOException {
        Parent blah = FXMLLoader.load(getClass().getResource("mainPanel.fxml"));
        Scene scene = new Scene(blah);
        scene.setFill(Color.TRANSPARENT);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    @FXML
    public void showResults(ActionEvent actionEvent) throws SQLException {
        try {
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            Statement statement = connection.createStatement();
            //statement.executeUpdate(sql);
            data= FXCollections.observableArrayList();
            String sql="SELECT * FROM test";
            Resultset resultSet = (Resultset) statement.executeQuery(sql);
        /*while (((ResultSet) resultSet).next()) {
            textLabel.setText(((ResultSet) resultSet).getString(1).concat(" ").concat((((ResultSet) resultSet).getString(2))).concat(" ").concat((((ResultSet) resultSet).getString(3))));
        }*/

            while(((ResultSet) resultSet).next()) {
                data.add(new PaliwoDetails(((ResultSet) resultSet).getString(2),((ResultSet) resultSet).getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableCost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        tablePaliwo.setItems(null);
        tablePaliwo.setItems(data);
    }
}
