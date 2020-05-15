package org.datavelger;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.datavelger.Exceptions.InvalidNameException;
import org.datavelger.Exceptions.InvalidPriceException;
import org.datavelger.classes.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ResourceBundle;

public class CostumerpageController implements Initializable {
    Stage window;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        radioComps = new RadioButton[]{comp1, comp2, comp3, comp4, comp5, comp6, comp7, comp8};

        btnBack.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                switchToMain();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnPreviousConf.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            try {
                switchToPreviousConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnChoose.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
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
        String labelText = "";
        int positionY = 15, positionX = 20, positionYchosen = 50;
        int i = 0;
        for (Boolean b : booleans){
            if (b != null && b) {
                labelText = radioComps[i].getText();

                Label lblComp = new Label(labelText);
                lblComp.setLayoutX(positionX);
                lblComp.setDisable(true);
                Button add = new Button("Velg " + labelText.toLowerCase());
                Button remove = new Button("Fjern valgt " + labelText.toLowerCase());
                //String chosen = "Du har valgt følgende " + labelText.toLowerCase() + ": ";

                nodes.addAll(Arrays.asList(lblComp, add, remove));
                /*for (Node node : nodes){
                    node.setLayoutY(positionY);
                }*/
                lblComp.setLayoutY(positionY);
                add.setLayoutY(positionY);
                remove.setLayoutY(positionY);

                lblComp.setStyle("-fx-font-weight: bold");
                add.setStyle("-fx-font-weight: bold");
                remove.setStyle("-fx-font-weight: bold");

                //TODO legge dette i css:

                add.setPrefSize(150,30);
                remove.setPrefSize(200, 30);
                add.setLayoutX(150);
                remove.setLayoutX(325);


                //Kaller på metoden i showChooser med navnet på knappen som er valgt
                String finalLabelText = labelText;
                add.setOnAction(event -> {
                    try {
                        showChooser.pressedButton(finalLabelText);
                        innerPane.getChildren().remove(btnChoose);
                        lblComp.setDisable(false);
                        lblComp.setText(showChooser.order != null ?
                                showChooser.order.getGraphicsCard() : "");
                        //innerPane.getChildren().addAll(chosencomp);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });



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



    public void switchToMain() throws IOException {
        App.setRoot("main");
    }
    public void switchToPreviousConfig() throws IOException{
        App.setRoot("orderedConfigs");
    }



    @FXML
    Button btnBack, btnChoose, btnChoose1, btnPreviousConf;
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
