package pe.edu.upeu.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Map;

public class MainguiController {
    @FXML BorderPane bp;

    @FXML
    MenuBar menuBar;

    @FXML
    MenuItem menuItemConstruccion, menuItemSalir;

    @FXML
    TabPane tabPane;

    @FXML
    public void initialize(){
        MenuItemListener mi=new MenuItemListener();
        menuItemConstruccion.setOnAction(mi::handle);
        menuItemSalir.setOnAction(mi::handle);
    }

    class MenuItemListener{
        Map<String, String[]> menus=Map.of(
                "menuItemConstruccion", new String[]{"/view/main_construccion.fxml", "Reg. Construccion", "T"},
                "menuItemSalir", new String[]{"/view/login.fxml", "Salir", "C"}
        );

        public void handle(ActionEvent e){
            String id=((MenuItem)e.getSource()).getId();
            if(menus.containsKey(id)){
                String[] items=menus.get(id);
                if(items[2].equals("C")){
                    Platform.exit();
                    System.exit(0);
                }else{
                    abrirTabPaneFXML(items[0],items[1]);
                }
            }
        }
        private void abrirTabPaneFXML(String fxmlPath, String tittle){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
                Parent root = fxmlLoader.load();
                ScrollPane scrollPane = new ScrollPane(root);
                scrollPane.setFitToWidth(true);
                scrollPane.setFitToHeight(true);
                Tab newTab = new Tab(tittle, scrollPane);
                tabPane.getTabs().clear();
                tabPane.getTabs().add(newTab);
            }catch (IOException ex){
                throw new RuntimeException(ex);
            }
        }

    }


}
