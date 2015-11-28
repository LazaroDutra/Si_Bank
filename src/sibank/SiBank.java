/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sibank;




import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Lazaro
 */
public class SiBank extends Application {
    public static Stage primarystage;
    @Override
    public void start(Stage stage) throws Exception {
        primarystage = stage; 
        Parent root = FXMLLoader.load(getClass().getResource("SplashFXML.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(null);
        stage.setScene(scene);
        stage.resizableProperty().set(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

        
    }
}
    
