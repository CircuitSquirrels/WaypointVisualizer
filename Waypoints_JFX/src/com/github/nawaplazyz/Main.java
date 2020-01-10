package com.github.nawaplazyz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Main extends Application {

    private final Canvas canvas = new Canvas(770, 750);

    @Override
    public void start(Stage waypointGUI) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI.fxml"));
        AnchorPane root = loader.load();

        Controller controller = loader.getController();
        controller.setGc(canvas.getGraphicsContext2D());
        controller.drawField();

        waypointGUI.setTitle("Waypoints");
        waypointGUI.setScene(new Scene(root));

        Stage field = new Stage();
        FlowPane pane = new FlowPane();

        pane.getChildren().add(canvas);
        field.setScene(new Scene(pane));

        field.show();
        waypointGUI.show();
    }

    public static void main(String... args) {
        launch(args);
    }
}
