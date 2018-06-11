/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox; 
import javafx.stage.Stage;

/**
 * Entrypoint (main) class of the program.
 * An interactive version of Conway's Game of Life.
 * @author Matthew
 */
public class GameOfLife extends Application {
    private final ConwayRules rules = new ConwayRules();
    private final int fieldHeight = 100; /* number of cells high */
    private final int fieldWidth = 100;
    
    private final FieldManager fieldManager = new FieldManager(fieldWidth, fieldHeight);
    
    private final int cellWidth = 4;
    
    
    @Override
    public void start(Stage primaryStage) {        
        VBox vBox = new VBox();

        Display display = new Display(fieldWidth, fieldHeight, cellWidth);
        display.setOnToggle((x, y) -> {
            final Playfield field = fieldManager.getCurrent();
            field.toggleCell(x, y);
            display.drawField(field);
        });
        
        Button nextButton = new Button("Next");
        nextButton.setOnAction((ActionEvent event) -> {
            fieldManager.generateNext(rules);
            display.drawField(fieldManager.getCurrent());
        });
        
        vBox.getChildren().addAll(display.getNode(), nextButton);
        
        Scene scene = new Scene(vBox);
        
        primaryStage.setTitle("Real Life");
        primaryStage.setScene(scene);
        display.drawField(fieldManager.getCurrent());
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
