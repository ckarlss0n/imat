package application;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;

public class ShoppingCartItem extends BorderPane {

	private Product theProduct;
	private int panelId;
	private double amount;
	private double price;

	public ShoppingCartItem(Product p) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"shoppingCartSmallItem.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

		theProduct = p;
		panelId = p.getProductId();

		setNameAmountNPrice(p.getName(), 1.0, p.getPrice());

	}

	@FXML
	private Label lblName;

	@FXML
	private Label lblAmount;

	@FXML
	private Label lblPrice;

	DecimalFormat twoDec = new DecimalFormat("#.00");

	DecimalFormat noDec = new DecimalFormat("#");

	public void setNameAmountNPrice(String name, double amount, double price) {
		this.price = price;
		this.amount = amount;

		lblName.setText(name);

		lblAmount.setText(String.valueOf(noDec.format(amount)) + " st");

		lblPrice.setText(String.valueOf(twoDec.format(price)) + " kr");
	}

	public void setPrice(double price) {

		lblPrice.setText(String.valueOf(twoDec.format(price) + " kr"));
	}

	public Product getProduct(Product p) {
		return theProduct;
	}

	public int getAmount() {
		return (int) amount;
	}

	public int getPanelId() {
		return panelId;
	}

	public void decreaseAmount(ActionEvent evt) {
		decreaseAmount();
	}

	public void decreaseAmount(){
		if (amount - 1 > 0) {
			setAmount(amount - 1);
		} else {
			setAmount(0);
		}
	}
	
	public void increaseAmount(ActionEvent evt) {
		increaseAmount();
	}

	public void increaseAmount(){
		setAmount(amount + 1);
	}
	
	public void removeFromCart(ActionEvent evt) {
		setAmount(0);
	}

	public void setAmount(double amount) {
		if(amount == 0){
			System.out.println("Remove the schoppingCartItem");
		} else {
			this.amount = amount;
			lblAmount.setText(String.valueOf(noDec.format(amount)) + " st");
			setPrice(this.theProduct.getPrice() * amount);
		}
	}

}