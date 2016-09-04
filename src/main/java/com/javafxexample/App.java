package com.javafxexample;

import com.javafxexample.config.View;
import com.javafxexample.controller.PersonOverviewController;
import com.javafxexample.model.Person;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
public class App extends Application {

    final static Logger logger = Logger.getLogger(App.class);
    private Stage primaryStage;
    private BorderPane rootLayout;

    /**
     * The data as an observable list of Persons.
     */
    private ObservableList<Person> personData = FXCollections.observableArrayList();

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

    public App() {
        // Add some sample data
        /**
         * We are working with JavaFX view classes that need to be informed
         * about any changes made to the list of persons. This is important,
         * since otherwise the view would not be in sync with the data. For this
         * purpose, JavaFX introduces some new Collection classes.
         */
        personData.add(new Person("Hans", "Muster"));
        personData.add(new Person("Ruth", "Mueller"));
        personData.add(new Person("Heinz", "Kurz"));
        personData.add(new Person("Cornelia", "Meier"));
        personData.add(new Person("Werner", "Meyer"));
        personData.add(new Person("Lydia", "Kunz"));
        personData.add(new Person("Anna", "Best"));
        personData.add(new Person("Stefan", "Meier"));
        personData.add(new Person("Martin", "Mueller"));
    }

    @Override
    public void start(Stage stage) throws Exception {
        logger.debug("Start application");

        this.primaryStage = stage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();

        showPersonOverview();

    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        logger.debug("initRootLayout");
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getViewURL(View.MAIN_VIEW));
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
        logger.debug("showPersonOverview");
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getViewURL(View.PERSON_VIEW));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);
            
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

    /**
     * Returns the data as an observable list of Persons.
     *
     * @return
     */
    public ObservableList<Person> getPersonData() {
        return personData;
    }

    private URL getViewURL(String view) {
        URL url = getClass().getResource(view);
        if (url == null) {
            logger.error("View not found: " + view);
            System.exit(-1);
        }
        return url;
    }
}
