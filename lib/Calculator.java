import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Calculator extends Pane {

	private IntegerProperty pressedOperation = null;
	private boolean equalPressed = false;
	private double input1 = 0;
	private double input2 = 0;
	private double answer = 0.0;
	private char prevOperation = '\0';
	
	public Calculator(int Width, int Height) {
		// TODO Auto-generated constructor stub
		pressedOperation = new SimpleIntegerProperty();
		
		//super(Width, Height);
		setWidth(Width);
		setHeight(Height);
		pressedOperation.set(0);
		pressedOperation.addListener(e ->
		{
			System.out.println("Pressed");
		});
		
		VBox pane = (VBox) makeCalculator();
		pane.setAlignment(Pos.CENTER);
		this.getChildren().add(pane);

		//isResizable();
	}
	
	
	private Pane makeCalculator()
	{
		VBox pane = new VBox();
		
		
		///Create a view are...Text field...or simple Text
		TextField text = new TextField("");
		
		pane.getChildren().add(text);
		pane.setSpacing(20);
		
		
		///Create number buttons
		VBox colOne = (VBox) makeNumButtons("1", "4", "7", text);
		VBox colTwo = (VBox) makeNumButtons("2", "5", "8", text);
		VBox colThree = (VBox) makeNumButtons("3", "6", "9", text);
		Button zero = new Button("0");
		zero.setOnMousePressed(e ->
		{
			if(equalPressed)
			{
				text.clear();
				text.setText("0");
			}
			else
				text.setText(text.getText() + "0");
			
			equalPressed = false;
		});
		
		Button clear = new Button("cls");
		
		clear.setOnMousePressed(e ->
		{
			input1 = 0;
			input2 = 0;
			answer = 0;
			text.clear();
		});
		
		colTwo.getChildren().add(zero);
		VBox colFour = new VBox();
		
		
		//Create operations column..containing operations(+,-,*,/)
		VBox operations = (VBox) makeOperationsButtons(text);
		Button eqButton = equalButton(text);
		colFour.setSpacing(10);
		colFour.getChildren().addAll(eqButton, clear);
		
		HBox hb = new HBox();
		hb.setSpacing(20);
		
		hb.getChildren().addAll(colOne, colTwo, colThree, operations, colFour);
		
		pane.getChildren().add(hb);
		
		return pane;
	}
	
	
	private Pane makeNumButtons(String s1, String s2, String s3, TextField text)
	{
		VBox colOne =  new VBox();
		colOne.setSpacing(10);
		Button b1 = new Button(s1);
		Button b2 = new Button(s2);
		Button b3 = new Button(s3);
		
		b1.setOnMousePressed(e ->
		{
			//Add one to text String
			if(equalPressed)
			{
				text.clear();
				text.setText(s1);
			}
			else
				text.setText(text.getText() + s1);
			
			equalPressed = false;
		});
		
		b2.setOnMousePressed(e ->
		{
			//Add one to text String
			if(equalPressed)
			{
				text.clear();
				text.setText(s2);
			}
			else
				text.setText(text.getText() + s2);
			
			equalPressed = false;
		});
		
		b3.setOnMousePressed(e ->
		{
			//Add one to text String
			if(equalPressed)
			{
				text.clear();
				text.setText(s3);
			}
			else
				text.setText(text.getText() + s3);
			
			equalPressed = false;
		});
		
		colOne.getChildren().addAll(b3, b2, b1);
		
		return colOne;
	}
	
	private Pane makeOperationsButtons(TextField text)
	{
		VBox colOne =  new VBox();
		colOne.setSpacing(5);
		
		//Operations
		Button add = new Button("+");
		Button subtract = new Button("-");
		Button divide = new Button("/");
		Button multiply = new Button("*");
		
		add.setOnMousePressed(e ->
		{
			/*store number on screen in input1
			 * clear text field
			 */
			if(text.getText().length() != 0) {
				input1 = Double.parseDouble(text.getText().trim());
				text.clear();
			}
			prevOperation = '+';
			System.out.println(prevOperation);
		});
		
		multiply.setOnMousePressed(e ->
		{
			/*store number on screen in input1
			 * clear text field
			 */
			if(text.getText().length() != 0) {
				input1 = Double.parseDouble(text.getText().trim());
				text.clear();
			
			}
			prevOperation = '*';
			System.out.println(prevOperation);
		});
		
		subtract.setOnMousePressed(e ->
		{
			/*store number on screen in input1
			 * clear text field
			 */
			if(text.getText().length() != 0) {
				input1 = Double.parseDouble(text.getText().trim());
				text.clear();
				
			}
			prevOperation = '-';
			System.out.println(prevOperation);
		});
		
		divide.setOnMousePressed(e ->
		{
			/*store number on screen in input1
			 * clear text field
			 */
			if(text.getText().length() != 0) {
				input1 = Double.parseDouble(text.getText().trim());
				text.clear();
		
			}
			prevOperation = '/';
			System.out.println(prevOperation);
		});
		
		equalPressed =false;
		colOne.getChildren().addAll(add, subtract, multiply, divide);
		
		return colOne;
	}
	
	private Button equalButton(TextField text)
	{
		Button b = new Button(" = ");
		
		b.setOnMousePressed(e ->
		{
			if(text.getText().isEmpty())
			{
				System.out.println("Empty String");
			}
			else if(prevOperation == '+')
			{
				input2 = Double.parseDouble(text.getText());
				answer = input1 + input2;
				text.setText(String.valueOf(answer));
			}
			
			else if(prevOperation == '-')
			{
				input2 = Double.parseDouble(text.getText());
				answer = input1 - input2;
				text.setText(String.valueOf(answer));
			}
			
			else if(prevOperation == '*')
			{
				input2 = Double.parseDouble(text.getText());
				answer = input1 * input2;
				text.setText(String.valueOf(answer));
			}
			
			else if(prevOperation == '/')
			{
				input2 = Double.parseDouble(text.getText());
				
				if(input2 != 0)
					answer = input1 / input2;
				else {
					System.out.println("division by 0");
					answer = 0;
				}
				text.setText(String.valueOf(answer));
			}

			pressedOperation.set(12);
			equalPressed = true;
			prevOperation = '\0';
		});
		
		return b;
	}
}
