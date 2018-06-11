/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.util.function.Consumer;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Holds displays the current state of the game and handles mouse clicks.
 * @author Matthew
 */

public class Display {
    private final int cellWidth;
    
    private final Pane pane = new Pane();
    private final Canvas canvas;
    
    private CellToggleHandler onToggle;
    
    /**
     * Constructor for Display
     * @param width number of cells wide
     * @param height number of cells tall
     * @param cellWidth width of pixels per cell
     */
    public Display(int width, int height, int cellWidth) {
        this.cellWidth = cellWidth;
        
        canvas = new Canvas(width * cellWidth, height * cellWidth);
        
        canvas.setOnMouseClicked((MouseEvent event) -> {
            final int cellX = (int)(event.getX() / cellWidth);
            final int cellY = (int)(event.getY() / cellWidth);

            if(onToggle != null) {
                onToggle.toggle(cellX, cellY);
            }
        });
        
        pane.getChildren().add(canvas);
    }
    
    /**
     * Set the CellToggleHandler to be called when a cell is clicked.
     * @param handler Handler which takes (x, y) coordinate of cell
     */
    public void setOnToggle(CellToggleHandler handler) {
        onToggle = handler;
    }
    
    /**
     * Get this object's root JavaFX node.
     * @return object which inherits from javafx.scene.node
     */
    public Pane getNode() {
        return pane;
    }
    
    private void drawCell(PixelWriter pixel, int x, int y, Color color) {        
        for(int iy = y * cellWidth; iy < y * cellWidth + cellWidth; ++iy) {
            for(int ix = x * cellWidth; ix < x * cellWidth + cellWidth; ++ix) {
                pixel.setColor(ix, iy, color);
            }
        }
    }

    /**
     * Draws Playfield to screen
     * @param field Playfield to be drawn
     */
    public void drawField(Playfield field) {
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        final PixelWriter pixelWriter = gc.getPixelWriter();
        
        for(int y = 0; y < field.getHeight(); ++y) {
            for(int x = 0; x < field.getWidth(); ++x) {
                final Color color = field.getCell(x, y) ? Color.WHITE : Color.BLUE;
                drawCell(pixelWriter, x, y, color);
            }
        }
    }
}
