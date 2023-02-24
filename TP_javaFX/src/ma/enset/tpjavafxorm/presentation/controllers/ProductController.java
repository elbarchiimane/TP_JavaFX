package ma.enset.tpjavafxorm.presentation.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.tpjavafxorm.dao.CategoryDaoImpl;
import ma.enset.tpjavafxorm.dao.ProductDaoImpl;
import ma.enset.tpjavafxorm.dao.entities.Category;
import ma.enset.tpjavafxorm.dao.entities.Product;
import ma.enset.tpjavafxorm.services.CatalogueService;
import ma.enset.tpjavafxorm.services.CatalogueServiceImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProductController implements Initializable {
    @FXML
    private TextField textNom;
    @FXML
    private TextField textRef;
    @FXML
    private TextField textPrix;
    @FXML
    private TextField textSearch;
    @FXML
    private ComboBox<Category> comboCategorie;
    @FXML
    private TableView<Product> tableProduct;
    @FXML
    private TableColumn<Long,Product> idColumn;
    @FXML
    private  TableColumn<String,Product> nomColumn;
    @FXML
    private TableColumn<String,Product> refColumn;
    @FXML
    private TableColumn<Float,Product> prixColumn;
    @FXML
    private TableColumn<Category,Product> catColumn;

    private CatalogueService catalogueService;
   private Product selectedProduct;
    ObservableList<Product> data= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableProduct.setItems(data);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        refColumn.setCellValueFactory(new PropertyValueFactory<>("reference"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        catColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        catalogueService=new CatalogueServiceImpl(new CategoryDaoImpl(),new ProductDaoImpl());
        comboCategorie.getItems().addAll(catalogueService.getAllCategories());
        loadData();
        tableProduct.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
            @Override
            public void changed(ObservableValue<? extends Product> observableValue, Product oldSelection, Product newSelection) {
                if (newSelection != null) {
                    selectedProduct=newSelection;
                    textNom.setText(selectedProduct.getName());
                    textRef.setText(selectedProduct.getReference());
                    textPrix.setText(String.valueOf(selectedProduct.getPrix()));
                    //comboCategorie.setValue(selectedProduct.getCategory());
                }


            }
        });

        textSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
             public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
            data.clear();
            data.addAll(catalogueService.searchProductByQuery(newValue));
            }
         });
    }
    private void loadData(){
        data.clear();
        data.addAll(catalogueService.getAllProducts());
    }

    public void clearFields(){
        textNom.setText("");
        textRef.setText("");
        textPrix.setText("");

    }
    public void addProduct(){
    Product product=new Product();
        product.setName(textNom.getText());
        product.setReference(textRef.getText());
        product.setPrix(Float.parseFloat(textPrix.getText()));
        product.setCategory(comboCategorie.getSelectionModel().getSelectedItem());
        catalogueService.addProduct(product);
        clearFields();
    }
    public void deleteProduct(){
    Product p=tableProduct.getSelectionModel().getSelectedItem();
    catalogueService.deleteProduct(p);
    loadData();
    }
    public void updateProduct() {

        selectedProduct.setName(textNom.getText());
        selectedProduct.setReference(textRef.getText());
        selectedProduct.setPrix(Float.valueOf(textPrix.getText()));
        selectedProduct.setCategory(comboCategorie.getSelectionModel().getSelectedItem());
        catalogueService.updateProduct(selectedProduct);
        data.clear();
        data.addAll(catalogueService.getAllProducts());
        clearFields();


    }
}
