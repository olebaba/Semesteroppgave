package org.datavelger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class CostumerpageController implements Initializable {
    Stage window;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        radioComps = new RadioButton[]{comp1, comp2, comp3, comp4, comp5, comp6, comp7, comp8};

        back.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                switchToMain();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        previousConf.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                switchToPreviousConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        choose.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            innerPane.getChildren().clear();
            addChosenComps(checkSelected());
        });
    }

    private Boolean[] checkSelected(){ //finner hvilke knapper som er trykket
        //System.out.println(radioComps[6].getText());
        Boolean[] selectedComps = new Boolean[radioComps.length];
        int i = 0;
        for (RadioButton rb : radioComps){
             selectedComps[i++] = rb.isSelected();
        }
        return selectedComps;
    }

    private void addChosenComps(Boolean[] booleans){ //legger valgte komponenter i programmet
        Collection<Node> nodes = new ArrayList<>();
        String labelText;
        int positionY = 15, positionX = 20, positionYchosen = 50;
        int i = 0;
        for (Boolean b : booleans){
            if (b != null && b) {
                labelText = radioComps[i].getText();
                //System.out.println(i);

                Label label = new Label(labelText+": ");
                label.setLayoutY(positionY);
                label.setLayoutX(positionX);
                nodes.add(label);
                String lowerCase = labelText.toLowerCase();
                //System.out.println("Du har valgt "+lowerCase);
                Button add = new Button("Velg "+lowerCase);
                Button remove = new Button("Fjern valgt "+lowerCase);
                String chosen = "Du har valgt følgende "+lowerCase+" :";
                Label showChosen = new Label(chosen);
                nodes.add(add);
                nodes.add(remove);
                add.setPrefSize(150,30);
                remove.setPrefSize(200, 30);
                add.setLayoutX(150);
                remove.setLayoutX(325);
                add.setFont(new Font("Arial",16));
                remove.setFont(new Font("Arial", 16));
                add.setLayoutY(positionY);
                remove.setLayoutY(positionY);

                //Kaller på metoden i showChooser med navnet på knappen som er valgt
                add.setOnAction(event -> showChooser.pressedButton(lowerCase));

                 /* System.out.println("Du har valgt grafikkort");
                    Button graphics = new Button("Velg grafikkort");
                    graphics.setOnAction(this::handle);
                    graphics.setLayoutX(brandPosition);
                    graphics.setLayoutY(positionY);
                    nodes.add(graphics);
                }/*else if(i==1){
                    System.out.println("Du har valgt skjerm");
                    Button screen = new Button("Velg skjerm");
                    screen.setLayoutX(brandPosition);
                    screen.setLayoutY(positionY);
                    nodes.add(screen);
                }
               /* ChoiceBox<String> brandBox = new ChoiceBox<>();
                brandBox.getItems().addAll("Merke", "Asus", "LG", "Samsung");
                brandBox.setLayoutX(brandPosition);
                brandBox.setLayoutY(positionY);
                brandBox.setValue(brandBox.getItems().get(0));
                nodes.add(brandBox);

                ChoiceBox<String> typeBox = new ChoiceBox<>();
                typeBox.getItems().addAll("Type", "LCD", "OLED");
                typeBox.setLayoutX(typePosition);
                typeBox.setLayoutY(positionY);
                typeBox.setValue(typeBox.getItems().get(0));
                nodes.add(typeBox);

                ChoiceBox<String> sizeBox = new ChoiceBox<>();
                sizeBox.getItems().addAll("Størrelse", "24\"", "27\"", "32\"");
                sizeBox.setLayoutX(sizePosition);
                sizeBox.setLayoutY(positionY);
                sizeBox.setValue(sizeBox.getItems().get(0));
                nodes.add(sizeBox);*/

                positionY += 60;
            }
            i++;
        }
        innerPane.getChildren().addAll(nodes);
    }

   /* public void choosenButton(int buttonPick){

        graphics.setLayoutX(150);
        removeGraphics.setLayoutX(300);
        graphics.setLayoutY(63);
        removeGraphics.setLayoutY(63);
        innerPane.getChildren().addAll(graphics,removeGraphics);
        graphics.setOnAction(event -> showChooser.pressedGraphics("Velg grafikkort",3));
    }*/

    public void switchToMain() throws IOException {
        App.setRoot("main");
    }
    public void switchToPreviousConfig() throws IOException{
        App.setRoot("orderedConfigs");
    }



    @FXML
    Button back, choose, choose1, previousConf;
    @FXML
    ChoiceBox<String> brand, type, size;
    @FXML
    RadioButton comp1, comp2, comp3, comp4, comp5, comp6, comp7, comp8;
    RadioButton[] radioComps;
    @FXML
    Pane innerPane;
    @FXML
    AnchorPane anchorPane;
    @FXML
    ScrollPane scrolling;
}
