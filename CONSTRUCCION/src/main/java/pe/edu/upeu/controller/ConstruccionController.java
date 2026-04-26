package pe.edu.upeu.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pe.edu.upeu.model.Construccion;
import pe.edu.upeu.service.ConstruccionService;
import pe.edu.upeu.service.ConstruccionServiceImp;

public class ConstruccionController {
    @FXML
    Button btnGuardar, btnActualizar, btnLimpiar, btnEliminar;
    @FXML
    TextField txtNumeroLicencia, txtDireccion, txtBuscar;
    @FXML
    ComboBox<String> cbxTipoObra;
    
    @FXML
    TableView<Construccion> regConstruccionTabla;
    private TableColumn<Construccion, String> colNumeroLicencia, colTipoObra, colDireccion;
    ObservableList<Construccion> construcciones;
    int index=-1;
    ConstruccionService cs=ConstruccionServiceImp.getInstance();
    
    @FXML
    public void initialize(){
        cbxTipoObra.getItems().addAll("Nueva construcción", "Ampliación", "Remodelación");
        definirColumnas();
        listar();
        agregarEventoSeleccion();
        desacActBotton(true);
        btnEliminar.setOnAction(e->{
            if(index!=-1){
                cs.delete(index);
                listar();
                limpiarForm();
                desacActBotton(true);
            }
        });
        btnLimpiar.setOnAction(e->{
            limpiarForm();
        });

        btnGuardar.setOnAction(e->{
            guardarConstruccion();
        });
        btnActualizar.setOnAction(e->{
            if (index!=-1){
                guardarConstruccion();
            }
        });
    }
    
    void desacActBotton(boolean valor){
        btnActualizar.setDisable(valor);
        btnEliminar.setDisable(valor);
    }
    
    void guardarConstruccion(){
        Construccion c=new Construccion();
        c.setNumeroLicencia(txtNumeroLicencia.getText());
        c.setTipoObra(cbxTipoObra.getValue());
        c.setDireccion(txtDireccion.getText());
        if(index==-1){
            cs.save(c);
        }else{
            cs.update(c, index);
            limpiarForm();
            index=-1;
        }
        listar();
    }
    
    void limpiarForm(){
        txtNumeroLicencia.setText("");
        cbxTipoObra.getSelectionModel().clearSelection();
        txtDireccion.setText("");
        index=-1;
        regConstruccionTabla.getSelectionModel().clearSelection();
        desacActBotton(true);
        btnGuardar.setDisable(false);
    }

    public void definirColumnas(){
        colNumeroLicencia=new TableColumn<>("Nro Licencia");
        colTipoObra=new TableColumn<>("Tipo de Obra");
        colDireccion=new TableColumn<>("Dirección");
        regConstruccionTabla.getColumns().addAll(colNumeroLicencia, colTipoObra, colDireccion);
    }

    private void listar(){
        colNumeroLicencia.setCellValueFactory(
                cellData->new SimpleStringProperty(cellData.getValue().getNumeroLicencia()));
        colTipoObra.setCellValueFactory(
                cellData->new SimpleStringProperty(cellData.getValue().getTipoObra()));
        colDireccion.setCellValueFactory(
                cellData->new SimpleStringProperty(cellData.getValue().getDireccion()));
        construcciones= FXCollections.observableArrayList(cs.findAll());
        regConstruccionTabla.setItems(construcciones);
    }

    private void agregarEventoSeleccion(){
        regConstruccionTabla.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue)->{
                   if(newValue!=null){
                       index=regConstruccionTabla.getItems().indexOf(newValue);
                       txtNumeroLicencia.setText(newValue.getNumeroLicencia());
                       cbxTipoObra.setValue(newValue.getTipoObra());
                       txtDireccion.setText(newValue.getDireccion());
                       desacActBotton(false);
                       btnGuardar.setDisable(true);
                   }
                });
    }
}
