/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.util.BitSet;

/**
 * Holds a state of Game of Life.
 * 
 * @author Matthew
 */

public class Playfield {
    /**
     * Thrown on attempt to access an out-of-bounds cell.
     */
    public class OutOfBoundsError extends RuntimeException {
        public OutOfBoundsError(String msg) {
            super(msg);
        }
    }

    private final int width;
    private final int height;
    private final BitSet field;

    /**
     * Construct a Playfield
     * @param width number of cells wide
     * @param height number of cells high
     */
    public Playfield(int width, int height) {
        this.width = width;
        this.height = height;
        this.field = new BitSet(this.width * this.height);
    }
    
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    
    private boolean checkBounds(int x, int y) {
        return (x >= 0 && y >= 0 && x < width && y < height);
    }

    /**
     * Access state of a cell
     * @param x x-coordinate of cell
     * @param y y-coordinate of cell
     * @return true if cell is alive.
     */
    public boolean getCell(int x, int y) {
        if (!checkBounds(x, y)) {
            throw new OutOfBoundsError("Field index out of bounds.");
        }
        return field.get(y * width + x);
    }
    
    /**
     * Set the state of a cell
     * @param x x-coordinate of cell
     * @param y y-coordinate of cell
     * @param alive true if cell is alive
     */
    public void setCell(int x, int y, boolean alive) {
        if (!checkBounds(x, y)) {
            throw new OutOfBoundsError("Field index out of bounds.");
        }
        field.set(y * width + x, alive);
    }
    
    /**
     * Toggles value of a cell
     * @param x x-coordinate of cell
     * @param y y-coordinate of cell
     * @return the new value of the cell
     */
    public boolean toggleCell(int x, int y) {
        final boolean value = !getCell(x, y);
        setCell(x, y, value);
        
        return value;
    }

    /**
     * Counts the number of neighbors alive
     * @param x x-coordinate of cell
     * @param y y-coordinate of cell
     * @return count
     */
    private int countNeighbors(int x, int y) {
        final int[] neighborCoords = { /* pairs of X, Y offsets to test */
            -1, -1, 0, -1, 1, -1,
            -1, 0, /* id */ 1, 0,
            -1, 1,  0, 1,  1, 1
        };
        int count = 0;

        for (int i = 0; i < neighborCoords.length; i += 2) {
            int testX = x + neighborCoords[i];
            int testY = y + neighborCoords[i + 1];
            try {
                if (getCell(testX, testY)) {
                    ++count;
                }
            } catch (OutOfBoundsError err) {
            }
        }
        return count;
    }

    /**
     * Creates the next state of the game. 
     * @param rules The rules by which to kill or create cells.
     * @param nextField this field will hold the new game state.
     */
    public void generateNext(RuleInterface rules, Playfield nextField) {
        for(int y = 0; y < height; ++y) {
            for(int x = 0; x < width; ++x) {
                final int neighbors = countNeighbors(x, y);
                final boolean nextState = rules.nextState(this.getCell(x, y), neighbors);
                
                nextField.setCell(x, y, nextState);
            }
        }
    }
}
