package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class PrimaryController {


    @FXML
    TextField usernameTextField;
    @FXML
    PasswordField passwordTextField;
    @FXML
    TextField passwordText;
    @FXML
    Button loginButton;
    @FXML
    Label loginTry;
    @FXML
    CheckBox showPasswordCheckBox;
    @FXML
    Button register;


    @FXML
    public void initialize(){
        passwordTextField.textProperty().bindBidirectional(passwordText.textProperty());
    }


    @FXML
    public void handleLogin(ActionEvent event) {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (username.isBlank() && password.isBlank()) {
            loginTry.setText("Please enter your username and password");
            return;
        }
        if (username.isBlank()) {
            loginTry.setText("Please enter your username");
            return;
        }
        if (password.isBlank()) {
            loginTry.setText("Please enter your password");
            return;
        }

       
        if (isValidLogin(username, password)) {
            loginTry.setText("Welcome, " + username + "!");
            

            try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            BorderPane root = loader.load();
            PageController pagec = loader.getController();
            pagec.displayUsername(username);
             App.setRoot(root);
               }catch(IOException ex){
            ex.printStackTrace();
}
            
        } else {
            loginTry.setText("Username or password incorrect");
        }
    }

   
    private boolean isValidLogin(String username, String password) {
        String sql = "SELECT userid FROM login WHERE username = ? AND password = ?";
        DatabaseConnection dbConn = new DatabaseConnection();

        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


     @FXML
private void handleShowPassword() {
    boolean show = showPasswordCheckBox.isSelected();
    
    passwordTextField.setVisible(!show);
    passwordTextField.setManaged(!show);

    passwordText.setVisible(show);
    passwordText.setManaged(show);
}


@FXML
private void handleRegister(ActionEvent e){
   try{
    App.setRoot("register");
}catch(IOException ex){
    ex.printStackTrace();
}
}

    }

