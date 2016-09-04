package com.javafxexample;

import com.javafxexample.config.AppImage;
import com.javafxexample.config.AppView;
import com.javafxexample.config.General;
import com.javafxexample.controller.PersonEditDialogController;
import com.javafxexample.controller.PersonOverviewController;
import com.javafxexample.model.Person;
import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
public class App extends Application {

    private final static Logger logger = Logger.getLogger(App.class);
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
        this.primaryStage.setTitle(General.APP_TITLE);

        // Set the application icon.
        this.primaryStage.getIcons().add(new Image(AppImage.APP_ICON));
    
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
            loader.setLocation(getViewURL(AppView.MAIN_VIEW));
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
            loader.setLocation(getViewURL(AppView.PERSON_VIEW));
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

    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     *
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showPersonEditDialog(Person person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getViewURL(AppView.PERSON_EDIT_VIEW));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    private URL getViewURL(String view) {
         logger.debug("getViewURL: " + view);
        URL url = getClass().getResource(view);
        if (url == null) {
            logger.error("View not found: " + view);
            System.exit(-1);
        }
        return url;
    }
}
