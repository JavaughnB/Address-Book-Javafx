package ch.makery.address.view;

import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonEditDialogController {
		@FXML
		private TextField txtFirstName;
		@FXML
		private TextField txtLastName;
		@FXML
		private TextField txtStreet;
		@FXML
		private TextField txtCity;
		@FXML
		private TextField txtPostalCode;
		@FXML
		private TextField txtBirthday;
	
	private Stage dialogStage;
	private Person person;
	private boolean okClicked =false;
	
	@FXML
	private void initialize() {
		
	}
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setPerson(Person person) {
		this.person = person;
		
		    txtFirstName.setText(person.getFirstName());
	        txtLastName.setText(person.getLastName());
	        txtStreet.setText(person.getStreet());
	        txtPostalCode.setText(Integer.toString(person.getPostalCode()));
	        txtCity.setText(person.getCity());
	        txtBirthday.setText(DateUtil.format(person.getBirthday()));
	        txtBirthday.setPromptText("dd.mm.yyyy");

	}
    /**
     * Called when the user clicks ok.
     */
	@FXML
	private void handleOk() {
		if(isInputValid())
		{
			person.setFirstName(txtFirstName.getText());
			person.setLastName(txtLastName.getText());
			person.setStreet(txtStreet.getText());
			person.setPostalCode(Integer.parseInt(txtPostalCode.getText()));
			
			okClicked = true;
			dialogStage.close();
		}
	}
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	  public boolean isOkClicked() {
	        return okClicked;
	    }
	private boolean isInputValid() {
		String errorMessage="";
		
		if (txtFirstName.getText() == null|| txtFirstName.getText().length() == 0) {
			errorMessage +="First name is not valid! \n";
		}
		if (txtLastName.getText() == null|| txtLastName.getText().length() == 0) {
			errorMessage +="Last name is not valid! \n";
		}
		if (txtStreet.getText() == null|| txtStreet.getText().length() == 0) {
			errorMessage +="Street name is not valid! \n";
		}
		
		if(txtPostalCode.getText() == null || txtPostalCode.getText().length() == 0)
		{
			errorMessage += "Postal code not valid! \n";
		}else {
			//attempt to parse the postal code into an integer
			try {
				Integer.parseInt(txtPostalCode.getText());
			}catch(NumberFormatException e) {
			errorMessage += "not a valid postal code (must be an integer)";			
			e.printStackTrace();
			}
		}
		
		if (txtCity.getText() == null|| txtCity.getText().length() == 0) {
			errorMessage +="City name is not valid! \n";
		}
		if (txtBirthday.getText() == null || txtBirthday.getText().length() == 0) {
	           errorMessage += "No valid birthday!\n";
	    } else {
	            if (!DateUtil.validDate(txtBirthday.getText())) {
	                errorMessage += "Not a valid birthday. Use the format dd.mm.yyyy!\n";
	            }
	    }
		 if (errorMessage.length() == 0) {
	            return true;
	        } else {
	            // Show the error message.
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Invalid Fields");
	            alert.setHeaderText("Please correct invalid fields");
	            alert.setContentText(errorMessage);
	            
	            alert.showAndWait();
	            
	            return false;
	        }

		
	}
	
}
