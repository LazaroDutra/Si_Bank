/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sibank;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
         test.setStyle("-fx-accent: red;");
    }  

    public void splash_action() {
        primarystage.initStyle(StageStyle.TRANSPARENT);
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
                        primarystage.close();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                        Parent root1;
                        try {
                            root1 = (Parent) fxmlLoader.load();
                            Stage stage = new Stage();
                            stage.setTitle("Si Bank");
                            stage.setResizable(false);
                            stage.setScene(new Scene(root1));  
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(SplashFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
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
