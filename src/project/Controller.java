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
import javafx.scene.control.*;
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

    //Table Data
    @FXML
    public TableColumn<PaliwoDetails, String> tableName;

    @FXML
    public TableColumn<PaliwoDetails, String> tableCost;

    @FXML
    public TableView<PaliwoDetails> tablePaliwo;


    private ObservableList<PaliwoDetails> data;

    //Login
    @FXML
    public Button loginButton;

    @FXML
    public TextField tf_username;

    @FXML
    public PasswordField pf_password;

    @FXML
    public Label login_label;

    //Register
    @FXML
    public Button registerButton;

    @FXML
    public TextField register_login;

    @FXML
    public PasswordField register_password;

    @FXML
    public PasswordField register_repassword;

    @FXML
    public Label register_label;

    //Edit
    @FXML
    public TextField nameTextField;

    @FXML
    public TextField costTextField;

    @FXML
    public Button addButton;

    @FXML
    public Button changeButton;
    //

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
    public void afterLogin(ActionEvent actionEvent) throws IOException, SQLException {

        String username, password;

        username = tf_username.getText();
        password = pf_password.getText();

        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();

        Statement statement = connection.createStatement();
        String sql="SELECT * FROM USER WHERE login"+
                " = '" + username + "' and password = '" + password + "'";

        Resultset resultSet = (Resultset) statement.executeQuery(sql);

        if(((ResultSet) resultSet).next()){
            System.out.print("Jestem w ifie");
            Parent blah = FXMLLoader.load(getClass().getResource("mainPanel.fxml"));
            Scene scene = new Scene(blah);
            scene.setFill(Color.TRANSPARENT);
            Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
        }
        else {
            login_label.setVisible(true);
            login_label.setText("Nieprawidłowe dane logowania.");
        }

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

    public void afterRegister(ActionEvent actionEvent) throws IOException, SQLException {
        //register_label.setVisible(false);
        String new_username, new_password, new_repassword;

        new_username = register_login.getText();
        new_password = register_password.getText();
        new_repassword = register_repassword.getText();
        System.out.print(new_password+"//"+new_repassword);
        if (new_repassword.equals(new_password)) {
            System.out.print("Jestem w ifie");
            ConnectionClass connectionClass = new ConnectionClass();
            Connection connection = connectionClass.getConnection();

            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM user WHERE login" +
                    " = '" + new_username + "'";

            Resultset resultSet = (Resultset) statement.executeQuery(sql);

            if (!((ResultSet) resultSet).next()) {
                System.out.print("Jestem w drugim ifie");
                String sql2= "INSERT INTO USER (login, password) VALUES('"+new_username+"','"+new_password+"')";
                //insert into user (login,password) VALUES('admin2','admin2')
                statement = connection.createStatement();
                statement.executeUpdate(sql2);
                register_label.setVisible(true);
                register_label.setTextFill(Color.GREEN);
                register_label.setText("Pomyślnie utworzono użytkownika.\nMożesz się zalogować.");
            }
            else{
                register_label.setVisible(true);
                register_label.setText("Istnieje taki użytkownik.");
            }
        }
        else{
            register_label.setVisible(true);
            register_label.setText("Powtórzone hasło nie jest zgodne.\nPopraw hasło.");
        }
    }

    public static void deleteRowByName(int nazwa) throws ClassNotFoundException, SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement = connection.createStatement();
        String sql = "delete from test where nazwa = '"+nazwa+"'";

        statement.executeUpdate(sql);
    }

    @FXML
    private void deleteRow(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        deleteRowByName(Integer.parseInt(nameTextField.getText()));
    }

    public static void addNewRow(int nazwa, double cena) throws ClassNotFoundException, SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement = connection.createStatement();
        String sql = "insert into test (nazwa, cena) values ('"+nazwa+"','"+cena+"')";

        statement.executeUpdate(sql);
    }

    @FXML
    private void addRow(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        addNewRow(Integer.parseInt(nameTextField.getText()),Double.parseDouble(costTextField.getText()));
    }

    public static void changeRowByName(int nazwa, double cena) throws ClassNotFoundException, SQLException {
        ConnectionClass connectionClass = new ConnectionClass();
        Connection connection = connectionClass.getConnection();
        Statement statement = connection.createStatement();
        String sql = "update test set cena = '"+cena+"' where nazwa = '"+nazwa+"'";

        statement.executeUpdate(sql);
    }

    @FXML
    private void changeRow(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        changeRowByName(Integer.parseInt(nameTextField.getText()),Double.parseDouble(costTextField.getText()));
    }
}
