package com.example;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SecondaryController {


    @FXML
    TextField usernameTextField;
    @FXML
    PasswordField passwordText;
    @FXML
    PasswordField confirmPassword;
    @FXML
    TextField firstNameTextField;
    @FXML
    TextField lastNameTextField;
    @FXML
    Label registerTry;
    
    @FXML
    private void initialize(){

    }

    @FXML
    private void handleRegister(){
        String username = usernameTextField.getText();
        String password = passwordText.getText();
        String firstName = firstNameTextField.getText();
        String lastName = lastNameTextField.getText();
        String confPassword = confirmPassword.getText();

        if (username.isBlank() && password.isBlank() && firstName.isBlank() && lastName.isBlank() && confPassword.isBlank()) {
          registerTry.setText("All fields are required");
       return;
}

     if (username.isBlank() || password.isBlank() || confPassword.isBlank() || firstName.isBlank() || lastName.isBlank()) {
    registerTry.setText("Please fill in all the fields");
    return;
} 

                if(password.length()<8){
                    registerTry.setText("Password must be at least 8 characters long.");
                        return;
                }
                if(password.length()>64){
                    registerTry.setText("Password must not exceed 64 characters");
                    return;
                }
                if (!password.matches(".*\\d.*")) {
        registerTry.setText("Password must contain at least one number");
        return;
    }

    if (!password.matches(".*[a-z].*")) {
        registerTry.setText("Password must contain at least one lowercase letter");
        return;
    }

    if (!password.matches(".*[A-Z].*")) {
        registerTry.setText("Password must contain at least one uppercase letter");
        return;
    }

    if (!password.matches(".*[!@#$%^&*()_+=|<>?{}\\\\[\\\\]~-].*")) {
        registerTry.setText("Password must contain at least one special character (e.g. !, @, #, %)");
        return;
    }

                

                 
                if(!password.equals(confPassword)){
                        registerTry.setText("Passwords do not match");
                        return;
                }

               

                 if(usernameExist(username)){
                    registerTry.setText("Username already used. Try another one!!");
                    return;
                 }

        if(isValidRegister(firstName, lastName, username, password)){
            registerTry.setText("you are successfully registered");
            
            try{
                App.setRoot("login");
            }catch(IOException e){
                e.printStackTrace();
            }

        }else{
            registerTry.setText("Registration failed. Try again.");
        }        

        



    }


    @FXML void handleCancel(ActionEvent eActionEvent){
        try{
            App.setRoot("login");
        }catch(IOException ex){
            ex.printStackTrace();
        }

    }


    private boolean isValidRegister(String firstName, String lastName,String username,String password){

        String query= "INSERT into login(firstname,lastname,username,password) VALUES(? ,? , ? , ?)";
        DatabaseConnection db = new DatabaseConnection();

        try(Connection conn = db.getConnection()){
            PreparedStatement ps = conn.prepareStatement(query);

           
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, username);
            ps.setString(4, password);

            int affectedRows = ps.executeUpdate();  

            return affectedRows > 0;

        }catch(SQLException e){
            e.printStackTrace();
            return false;

        }












    }


        private boolean usernameExist(String username){
            String query= "SELECT userid FROM login WHERE username = ?";
        DatabaseConnection db = new DatabaseConnection();

        try(Connection conn = db.getConnection()){
            PreparedStatement ps = conn.prepareStatement(query);

            
            ps.setString(1, username);
            

            try(ResultSet r = ps.executeQuery()){
                return r.next();
            }

        }catch(SQLException e){
            e.printStackTrace();
            return false;

        }

        }


        

    }

