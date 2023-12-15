import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ProstyProgram extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Prosty Program JavaFX");

        Button btn = new Button();
        btn.setText("Kliknij mnie!");

        // Obsługa zdarzenia kliknięcia przycisku
        btn.setOnAction(e -> {
            System.out.println("Hello, JavaFX!");
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));

        primaryStage.show();
    }
}
