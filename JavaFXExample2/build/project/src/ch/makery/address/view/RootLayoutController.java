package ch.makery.address.view;

import java.io.File;

import ch.makery.address.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class RootLayoutController {

	//Reference to the main application
	private MainApp mainApp;
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	//Create an empty address book
	
	@FXML
	private void handleNew() {
		mainApp.getPersonData().clear();
		mainApp.setPersonFilePath(null);
	}
	
	@FXML
	private void handleOpen()
	{
		FileChooser fileChooser = new FileChooser();
		
		//Set Extension Filter
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
		fileChooser.getExtensionFilters().add(extFilter);
		
		//SHow open file dialog
		File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
		
		if (file!= null ) {
			mainApp.loadPersonDataFromFile(file);
		}
	
		
		
	}
	//Saves the file to the person file that is currently open. If there is no
    // * open file, the "save as" dialog is shown.
	@FXML
	private void handleSave() {
		File personFile = mainApp.getpersonFilePath();
		if(personFile != null) {
			mainApp.savePersonDataToFile(personFile);
		}else {
			handleSaveAs();
		}
	}
	   /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("AddressApp");
        alert.setHeaderText("About");
        alert.setContentText("Author: Javaughn Boothe");

        alert.showAndWait();
    }
    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.savePersonDataToFile(file);
        }
    }
    @FXML
    private void handleShowBirthdayStatistics() {
      mainApp.showBirthdayStatistics();
    }
    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }
}

