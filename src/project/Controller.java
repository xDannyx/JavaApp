package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

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
}
