package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import model.MotionPeriod;
import util.MotionDetector;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Controller {

    @FXML
    public Button runButton;
    @FXML
    public Button addSetButton;
    @FXML
    public Button selectInputButton;
    @FXML
    public Button selectOutputButton;
    @FXML
    public TextField volleyWindowText;
    @FXML
    public TextField otherWindowText;
    @FXML
    private Button trainingButton;


    private MotionDetector motionDetector;
    private File trainingDir;
    private int otherWindow;
    private int volleyWindow;
    private File inputFile;
    private File outFile;

    @FXML
    public void onClicktrainingButtonMethod(ActionEvent actionEvent) throws IOException{

       updateWindowsVlues();

        motionDetector = new MotionDetector(trainingDir,
                volleyWindow, otherWindow);

        trainingButton.setText(trainingButton.getText() + "!!");
    }

    @FXML
    public void onClickRunButtonMethod(ActionEvent actionEvent)throws IOException {
        updateWindowsVlues();

        List<MotionPeriod> predictedPeriod = motionDetector.predictMotionPeriods(inputFile);

        List<String> lines = predictedPeriod.stream().map(mp -> mp.toString()).collect(Collectors.toList());
        Path file = Paths.get(outFile.getAbsolutePath());
        Files.write(file, lines, Charset.forName("UTF-8"));
    }

    @FXML
    public void onClickAddSetButtonMethod(ActionEvent actionEvent) {

        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Select training data");
        File defaultDirectory = new File(".");
        chooser.setInitialDirectory(defaultDirectory);
        trainingDir = chooser.showDialog(null);
    }

    @FXML
    public void onClickSelectInputButtonMethod(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        inputFile = fileChooser.showOpenDialog(null);
    }

    @FXML
    public void onClickSselectOutputButtonMethod(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        outFile = fileChooser.showOpenDialog(null);
    }

    private void updateWindowsVlues(){
        volleyWindow = Integer.parseInt(volleyWindowText.getText());
        otherWindow = Integer.parseInt(otherWindowText.getText());
    }
}
