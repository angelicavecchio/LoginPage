package com.example;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

public class PageController {
@FXML
Label setName;
@FXML
Button logOutButton;

@FXML
private void initialize(){

}

public void displayUsername(String username){
    setName.setText(username);
}

@FXML
private void handleLogOut(ActionEvent e){

    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setContentText("Any unsaved changes will be lost.");
    alert.setHeaderText("Are you sure you want to log out?");
    alert.setTitle("Confirm Logout");
    Optional<ButtonType> result = alert.showAndWait();

    if(result.isPresent() && result.get() == ButtonType.OK){
        try{
    App.setRoot("login");
}catch(IOException ex){
    ex.printStackTrace();
}
    }


    
  






}



}
