package com.javafxexample;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author moscac
 */
public class App extends Application {

    private static final String MAIN_VIEW = "view/Main.fxml";
    private static final String PERSON_VIEW = "view/PersonOverview.fxml";

    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showPersonOverview();

        /*Parent root = FXMLLoader.load(getClass().getResource(MAIN_VIEW));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();*/
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getViewURL(MAIN_VIEW));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getViewURL(PERSON_VIEW));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    private URL getViewURL(String view)
    {
        URL url = getClass().getResource(view);
        if(url == null)
        {
            System.err.println("View not found: "+view);
            System.exit(-1);
        }
        
        return url;
    }
}
