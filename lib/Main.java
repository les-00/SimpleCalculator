import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static void main(String[] a)
	{
		System.out.println("a");
		launch(a);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Calculator calc = new Calculator(350, 250);
		
		Scene scene = new Scene(calc, 350, 250);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Basic Calclator");
		primaryStage.show();
	}
}
