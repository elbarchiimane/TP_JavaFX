package ma.enset.tpjavafxorm.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ApplicationJavaFX  extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root= FXMLLoader.load(ApplicationJavaFX.class.getResource("views/ProductView.fxml"));
        Scene scene=new Scene(root,600,500);
        stage.setScene(scene);
        stage.setTitle("Gestion des Produits");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }


}