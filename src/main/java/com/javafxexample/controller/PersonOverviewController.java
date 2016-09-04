package com.javafxexample.controller;

import com.javafxexample.App;
import com.javafxexample.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * All fields and methods where the fxml file needs access must be annotated with @FXML. 
 * Actually, only if they are private, but it's better to have them private and mark 
 * them with the annotation!
 * The initialize() method is automatically called after the fxml file has been loaded. 
 * At this time, all the FXML fields should have been initialized already.
 * The setCellValueFactory(...) that we set on the table colums are used to 
 * determine which field inside the Person objects should be used for the 
 * particular column. The arrow -> indicates that we're using a Java 8 feature called Lambdas. 
 * 
 * WARNING
 * We're only using StringProperty values for our table columns in this example. 
 * When you want to ucom.javafxexample.controllerse IntegerProperty or DoubleProperty, the setCellValueFactory(...)
 * must have an additional asObject():
 *  myIntegerColumn.setCellValueFactory(cellData -> cellData.getValue().myIntegerProperty().asObject());
 * 
 * @author user
 */
public class PersonOverviewController {

    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // Reference to the main application.
    private App mainApp;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public PersonOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }
}
