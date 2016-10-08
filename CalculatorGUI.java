//Kathryn, Raveesha, and Hannah
//Ladan Section 0401


import java.util.ArrayList;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class CalculatorGUI extends Application {

	private TextArea displayArea;
	
	private Calculator calc = new Calculator();
	private State currentState = calc.RFO;
	private boolean equalClear;

	
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
		
		Button[] allButtons = {one, two, three, four, five, six, seven, eight, nine, posNeg, zero, period, plus, minus, multiply, divide, square, squareRoot, inverse, clear, clearEntry, equal};
		
		Button[] numButtons = {one, two, three, four, five, six, seven, eight, nine, posNeg, zero, period};
		ArrayList<Button> numbers = new ArrayList<Button>();
		for (Button b : numButtons){
			numbers.add(b);
		}
		
		Button[] operatorButtons = {plus, minus, multiply, divide};
		ArrayList<Button> operators = new ArrayList<Button>();
		for (Button b : operatorButtons){
			operators.add(b);
		}
		
		Button[] rfoOperatorsButtons = {square, squareRoot, inverse};
		ArrayList<Button> rfoOperators = new ArrayList<Button>();

		for (Button b : rfoOperatorsButtons){
			rfoOperators.add(b);
		}
		
		/*set action*/
		for(Button b: allButtons){
			b.setOnAction(e -> {
				String s = displayArea.getText() + b.getText();
				displayArea.setText(s);
				
				if (numbers.contains(b)){
					if (equalClear){
						displayArea.setText("");
						equalClear = false;
						displayArea.setText(b.getText());
						calc.v1 = "";
					}
					currentState.addToNumber(b.getText());
				} else if (operators.contains(b)){
					equalClear = false;
					currentState = currentState.changeState(b.getText());
				} else if (rfoOperators.contains(b)){
					equalClear = false;
					currentState = currentState.changeState(b.getText());
					displayArea.setText(calc.calculate());
					equalClear = true;
					currentState = calc.RFO;
					calc.v1 = calc.calculate();
				}else if (b == clear){
					equalClear = false;
					displayArea.setText("");
					currentState = calc.RFO;
					calc.v1 = "";
					calc.v2 = "";
				} else if (b== clearEntry){
					equalClear = false;
					if (currentState == calc.RFO){
						calc.v1 = "";
						displayArea.setText("");
					} else {
						displayArea.setText(displayArea.getText().substring(0, displayArea.getText().length()-calc.v2.length()-2));
						calc.v2 = "";

					}
				} else if (b == equal){
					displayArea.setText(calc.calculate());
					currentState = calc.RFO;
					equalClear = true;
					calc.v1 = calc.calculate();
					calc.v2 = "";
				}
				
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