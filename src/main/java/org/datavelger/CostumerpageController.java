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
import org.datavelger.classes.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class CostumerpageController implements Initializable {
    String folderpath = "komponenter";
    Stage window;
    Order order = new Order();
    static List<String> components = new ArrayList<>();

    public static void addComponent(String comp){
        components.add(comp);
    }

    private void createOrder(List<String> orderlist){
        List<Component> components = new ArrayList<>();
        for (int i = 1; i<orderlist.size()-1; i++){ //første og siste er ikke komponenter
            try {
                components.add(FileOpenerCsv.findComponent(orderlist.get(i)));
            }catch (IOException | ClassNotFoundException e ){
                //labInfo.setText(e.getLocalizedMessage());
                e.printStackTrace();
            }
        }

        order = new Order(orderlist.get(0), components.get(0), components.get(1), components.get(2),
                components.get(3), components.get(4), components.get(5), components.get(6), components.get(7));
    }

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
        btnOrder.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            createOrder(components);
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

    private void readComponentofType(String compType, ChoiceBox<Component> compChoices){
        FileOpenerBinary componentFolder = new FileOpenerBinary(folderpath + "/" + compType);
        componentFolder.setOnSucceeded(event -> {
            //layout.setDisable(false);
            List<Component> components = componentFolder.getValue();

            //compChoices.setItems(components);
        });
        componentFolder.setOnFailed(event -> {
            try {
                throw event.getSource().getException();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
           // layout.setDisable(false);
            //labInfo.setText("Fant ikke angitt fil.");
            System.out.println("Fant ikke angitt fil. Har du lagret componenttypen? Har du valgt riktig path?");
        });


        Thread thread = new Thread(componentFolder);
        thread.setDaemon(true);
        //layout.setDisable(true);
        thread.start();
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
    Button btnBack, btnChoose, btnChoose1, btnPreviousConf, btnOrder;
    @FXML
    ChoiceBox<String> brand, type, size;
    @FXML
    RadioButton comp1, comp2, comp3, comp4, comp5, comp6, comp7, comp8;
    RadioButton[] radioComps;
    @FXML
    Pane innerPane;
    @FXML
    AnchorPane anchorPane;
}
