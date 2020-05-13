package org.datavelger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * JavaFX App
 */
public class App extends Application {
    final String componentPath = "komponenter";

    private static Scene scene;

    private void createPaths(){
        ArrayList<String> compTypes = new ArrayList<>(7);
        compTypes.addAll(Arrays.asList("Grafikkort", "Hovedkort", "Minne", "Mus", "Prosessor", "Skjerm", "Tastatur"));
        for (String type : compTypes){
            File file = new File(componentPath+"/"+type);
            //Lager mapper om de ikke eksisterer
            if(!file.exists()) {
                if (file.mkdir()) {
                    System.out.println("Directory, " + file.getPath() + ", created successfully");
                } else {
                    System.out.println("Sorry couldn’t create specified directory, " + file.getPath());
                }
            }
        }

    }

    @Override
    public void start(Stage stage) throws IOException {
        createPaths();

        scene = new Scene(loadFXML("main"));
        stage.setScene(scene);
        stage.setTitle("Komponent-velger");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}