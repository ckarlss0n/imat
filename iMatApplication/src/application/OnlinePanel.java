package application;

import java.io.File;
import java.io.IOException;
import java.util.List;

import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.Product;
import se.chalmers.ait.dat215.project.ShoppingItem;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class OnlinePanel extends ScrollPane{
	
	@FXML
	private GridPane gridPane;
	
	private List<ShoppingItem> shoppingItems;
	
	public OnlinePanel(List<ShoppingItem> shoppingItems){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homeOnlinePanel.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
    		fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
		
		this.shoppingItems = shoppingItems;
		//generateProducts();
	}
	
	public void generateProducts(){
		gridPane.setPrefWidth(10*300);
		for(int i = 0; i<10; i++){
        	add(new SmallProductPanel(shoppingItems.get(i)), i);
        }
	}
	
	public void slideBackward(ActionEvent evt){
		System.out.println("Slide backward");
	}
	
	public void slideForward(ActionEvent evt){
		System.out.println("Slide forward");
	}
	
	public void addToCart(ActionEvent evt){
		System.out.println("Add to cart");
	}
	
	public void add(Node node, int col){
		gridPane.add(node, col, 0);
	}
	
	public void setWidth(int i){
		gridPane.setPrefWidth(i);
	}
}
