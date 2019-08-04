package ch.makery.address.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.Date;

import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;

public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label lblFirstName;
    @FXML
    private Label lblLastName;
    @FXML
    private Label lblStreet;
    @FXML
    private Label lblPostalCode;
    @FXML
    private Label lblCity;
    @FXML
    private Label lblBirthday;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public PersonOverviewController() {
    }

    private void showPersonDetails(Person person) {
    	if(person!= null) {
    		//fill the labels with info from the person object
    		lblFirstName.setText(person.getFirstName());
    		lblLastName.setText(person.getLastName());
    		lblStreet.setText(person.getStreet());
    		lblPostalCode.setText(Integer.toString(person.getPostalCode()));
    		lblCity.setText(person.getCity());
    	    lblBirthday.setText((DateUtil.format(person.getBirthday())));
    	}else {
    		lblFirstName.setText("");
    		lblLastName.setText("");
    		lblStreet.setText("");
    		lblPostalCode.setText("");
    		lblCity.setText("");
    		lblBirthday.setText("");
    	}
    }
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
      
    	firstNameColumn.setCellValueFactory(
    			cellData -> cellData.getValue().firstNameProperty());
    	 lastNameColumn.setCellValueFactory(
    	            cellData -> cellData.getValue().lastNameProperty());
    	 // Clear person details.
    	    showPersonDetails(null);

    	    // Listen for selection changes and show the person details when changed.
    	    personTable.getSelectionModel().selectedItemProperty().addListener(
    	            (observable, oldValue, newValue) -> showPersonDetails(newValue));
 }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeletePerson() {
    	int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            personTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
            mainApp.getPersonData().add(tempPerson);
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    
}