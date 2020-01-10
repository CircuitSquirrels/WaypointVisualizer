package com.github.nawaplazyz;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

public class Controller {

    @FXML private Button addWaypoint;
    @FXML private TextField XPos;
    @FXML private TextField YPos;

    private final URL fieldPath = getClass().getResource("field.png");

    private double input_x = 15;
    private double input_y = 15;
    private int x = 0;
    private int y = 0;
    private int index = 0;
    private final int radius = 20;

    private HashMap<Integer, String> waypoints = new HashMap<>();
    private GraphicsContext gc;

    @FXML
    public void Handler(ActionEvent event) {
        if (event.getSource() == addWaypoint) {
            try {
                input_x = Integer.parseInt(XPos.getText());
                input_y = Integer.parseInt(YPos.getText());
            } catch (NumberFormatException e) {
                input_x = 0;
                input_y = 0;
            }

            calculatePositions();
            drawField();
            waypoints.put(index, x + ";" + y);
            index++;
            addWaypoints();
        }
    }

    private void addWaypoints() {
        int draw_X;
        int draw_Y;
        for(int i : waypoints.keySet()) {
            String[] s = waypoints.get(i).split(";");
            draw_X = Integer.parseInt(s[0]);
            draw_Y = Integer.parseInt(s[1]);
            // Invert the XY for Canvas XY
            drawWaypoint(draw_Y, draw_X);
        }
    }

    private void drawWaypoint(int x, int y) {
        gc.fillOval(x, y, radius, radius);
    }

    void drawField() {
        File file = new File(fieldPath.getPath());
        Image map = new Image(file.toURI().toString());
        gc.drawImage(map, 0, 0);
    }

    private void calculatePositions() {
        input_x = input_x < 0 ? (input_x - 71) * -1 : 71 - input_x;
        input_y = input_y < 0 ? (input_y - 71) * -1 : 71 - input_y;
        x = (int) (input_x * 5.25);
        y = (int) (input_y * 5.25);
    }

    void setGc(GraphicsContext gc) {
        this.gc = gc;
    }
}

