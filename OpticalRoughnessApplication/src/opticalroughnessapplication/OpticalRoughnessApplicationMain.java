/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opticalroughnessapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author Matt
 */
public class OpticalRoughnessApplicationMain extends Application {
    Parent root;
    Scene scene;
    @Override
    public void start(Stage stage) throws Exception {
        root = FXMLLoader.load(getClass().getResource("OpticalRoughnessApplicationFXML.fxml"));
        
        scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Optical Roughness Data Viewer");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
