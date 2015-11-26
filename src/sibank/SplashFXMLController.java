/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sibank;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import static sibank.SiBank.primarystage;

/**
 * FXML Controller class
 *
 * @author Lazaro
 */
public class SplashFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ProgressBar test;
    public static Scene scene;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        splash_action();
    }  
    public void splash_action() {

        test.setVisible(true);
        Task task = createTask();
        test.progressProperty().bind(task.progressProperty());
        new Thread(task).start();

    }
    
    private Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            public Void call() {
                for (double i = 0; i < 3; i = i + 0.1) {
                    if (isCancelled()) {
                        break;
                    }
                    updateProgress(i, 3);
                    updateMessage("Please wait logging out");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        return null;
                    }
                }
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Parent root = null;
                        try {
                            root = (Parent) FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                        } catch (IOException ex) {

                        }
                        if (scene == null) {
                            scene = new Scene(root);
                            primarystage.setScene(scene);
                        } else {
                            primarystage.getScene().setRoot(root);
                        }
                        primarystage.show();
                        
                    }
                });
                test.setVisible(false);
                updateMessage(0 + "");
                updateProgress(3, 3);
                return null;
            }
        };
    }
    
    
    
}
