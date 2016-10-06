//Kathryn, Raveesha, and Hannah
//Ladan Section 0401

package view;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import model.*;

public class CalculatorGUI extends Application{

	private TextArea displayArea;
	private State stepOn;
	private Calculator calc;
	
	@SuppressWarnings({ "restriction", "static-access" })
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		
		int sceneWidth = 250, sceneHeight = 400;
		displayArea = new TextArea();
		displayArea.setPrefSize(sceneWidth, sceneHeight / 6);
		displayArea.setEditable(false);
		displayArea.setWrapText(true);
		
		
		
		/*function buttons*/
		Button plus = new Button("+");
		Button clearEntry = new Button("CE");
		Button clear = new Button("C");
		Button minus = new Button("-");
		Button square = new Button("x^2");
		Button squareRoot = new Button("sqrt");
		Button inverse = new Button("1/x");
		Button multiply = new Button("*");
		Button divide = new Button("/");
		Button posNeg = new Button("+/-");
		Button equal = new Button("=");
		
		/*Number Buttons*/
		Button one = new Button("1");
		Button two = new Button("2");
		Button three = new Button("3");
		Button four = new Button("4");
		Button five = new Button("5");
		Button six = new Button("6");
		Button seven = new Button("7");
		Button eight = new Button("8");
		Button nine = new Button("9");
		
		Button zero = new Button("0");
		Button period = new Button(".");
		
		Button[] numButtons = {one, two, three, four, five, six, seven, eight, nine, posNeg, zero, period};
		
		/*set action*/
		for(Button b: numButtons){
			b.setOnAction(e -> {
				String s = displayArea.getText() + b.getText();
				displayArea.setText(s);
			});
		}
		
		GridPane gridPane = new GridPane();
		gridPane.add(squareRoot, 0, 0);
		gridPane.add(square, 1, 0);
		gridPane.add(inverse, 2, 0);
		gridPane.add(clearEntry,0, 1);
		gridPane.add(clear, 1, 1);
		gridPane.add(divide, 3, 1);
		gridPane.add(multiply, 3, 2);
		gridPane.add(minus, 3, 3);
		gridPane.add(plus, 3, 4);
		gridPane.add(equal,3, 5);
		
		for(int i = 2, count = 0; i <= 5; i++){
			for (int j = 0; j < 3; j++){
				gridPane.add(numButtons[count++], j, i);
			}
		}
		
		for(Node n : gridPane.getChildren()){
			((Button) n).setPrefSize(sceneWidth/4, sceneHeight/6);
		}
		BorderPane pane = new BorderPane();
		pane.setTop(displayArea);
		pane.setCenter(gridPane);
		
		/* Stage */
		Scene scene = new Scene(pane, sceneWidth , sceneHeight);
		stage.setTitle("Calculator");
		stage.setScene(scene);
		stage.show(); 
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
