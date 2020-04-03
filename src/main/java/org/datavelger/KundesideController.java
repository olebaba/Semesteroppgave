package org.datavelger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class KundesideController implements Initializable{

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
        String labelText = "error";
        int positionY = 63, brandPosition = 150, typePosition = 300, sizePosition = 450;
        int i = 0;
        for (Boolean b : booleans){
            if (b != null && b) {
                labelText = radioComps[i].getText();
                System.out.println(i);

                Label label = new Label(labelText);
                label.setLayoutY(positionY);
                nodes.add(label);

                ChoiceBox<String> brandBox = new ChoiceBox<>();
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
                nodes.add(sizeBox);

                positionY += 50;
            }
            i++;
        }


        innerPane.getChildren().addAll(nodes);
    }

    public void switchToMain() throws IOException {
        App.setRoot("main");
    }


    @FXML
    Button back, choose, choose1;
    @FXML
    ChoiceBox<String> brand, type, size;
    @FXML
    RadioButton comp1, comp2, comp3, comp4, comp5, comp6, comp7, comp8;
    RadioButton[] radioComps;
    @FXML
    Pane innerPane;
}
