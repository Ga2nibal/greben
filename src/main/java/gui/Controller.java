package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {

    @FXML
    private Button trainingButton;

    @FXML
    public void onClicktrainingButtonMethod(){
        trainingButton.setText("Thanks!");
    }
}
