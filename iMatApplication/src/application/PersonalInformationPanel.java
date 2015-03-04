package application;

import java.io.IOException;

import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

public class PersonalInformationPanel extends ScrollPane {
	
	@FXML
	private TextField txtfSurname;
	@FXML
	private TextField txtfLastname;
	@FXML
	private TextField txtfEmail;
	@FXML
	private TextField txtfAdress;
	@FXML
	private TextField txtfPostcode;
	@FXML
	private TextField txtfCity;
	@FXML
	private TextField txtfPhone;
	@FXML
	private Button goToPaymentBtn;
	MainPanel mainPanel;
	ChoosePayment choosePayment;
	
	IMatDataHandler imat = IMatDataHandler.getInstance();
	Customer customer = imat.getCustomer();
	
	public PersonalInformationPanel(MainPanel mainPanel, ChoosePayment choosePayment, Customer customer){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("personalInformation.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		this.mainPanel = mainPanel;
		this.choosePayment = choosePayment;
		
		System.out.println(customer.getFirstName()); //finns inte... customer måste skickas med i konstrucktorn tror jag... -ish
		System.out.println("asd");
		
		try {
    		fxmlLoader.load();
    		BooleanBinding bb = new BooleanBinding() {
    		    {
    		       super.bind(txtfSurname.textProperty(),txtfLastname.textProperty(),
    		    		   txtfEmail.textProperty(),txtfAdress.textProperty(),
    		    		   txtfPostcode.textProperty(),txtfCity.textProperty(),
    		    		   txtfPhone.textProperty());
    		    }
    		    @Override
    		    protected boolean computeValue() {
    		      return (txtfSurname.getText().isEmpty() || txtfLastname.getText().isEmpty()
    		            || txtfEmail.getText().isEmpty() || txtfAdress.getText().isEmpty()
    		            || txtfPostcode.getText().isEmpty() || txtfCity.getText().isEmpty()
    		            || txtfPhone.getText().isEmpty());
    		       }
    		    };

    		    goToPaymentBtn.disableProperty().bind(bb);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
	}
	
	public void pInfSetText(){
		txtfSurname.setText(customer.getFirstName());
		txtfLastname.setText(customer.getLastName());
		txtfEmail.setText(customer.getEmail());
		txtfAdress.setText(customer.getAddress());
		txtfPostcode.setText(customer.getPostCode());
		txtfCity.setText(customer.getPostAddress());
		txtfPhone.setText(customer.getPhoneNumber());
	}
	
	public void register(ActionEvent evt){
		 
		customer.setFirstName(txtfSurname.getText());
		customer.setLastName(txtfLastname.getText());
		customer.setEmail(txtfEmail.getText());
		customer.setAddress(txtfAdress.getText());
		customer.setPostCode(txtfPostcode.getText());
		customer.setPostAddress(txtfCity.getText());
		customer.setPhoneNumber(txtfPhone.getText());
		mainPanel.changeScreen(choosePayment);		
	
	}
}
