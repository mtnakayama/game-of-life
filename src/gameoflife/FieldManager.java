/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

/**
 * Holds a round-robin buffer of Playfields
 * @author Matthew
 */
public class FieldManager {

    private final int numFields;
    private final Playfield fields[];

    private int fieldIndex = 0;

    /**
     * Create a FieldManager buffer
     * @param width Width of cells in each Playfield.
     * @param height Height of cells in each Playfield
     * @param numFields total number of Playfields to hold (Must be >= 2).
     */
    public FieldManager(int width, int height, int numFields) {
        if (numFields < 2) {
            throw new RuntimeException("FieldManager must be created with numFields >= 2.");
        }
        this.numFields = numFields;
        fields = new Playfield[this.numFields];
        for (int i = 0; i < numFields; ++i) {
            fields[i] = new Playfield(width, height);
        }
        
    }

    /**
     * Create a FieldManager buffer that cycles through 2 playfields.
     * @param width Width of cells in each Playfield.
     * @param height Height of cells in each Playfield.
     */
    public FieldManager(int width, int height) {
        this(width, height, 2);
    }
    
    private int getNextIndex() {
        int next = fieldIndex + 1;
        return next % numFields;
    }

    /**
     * Get the current Playfield
     * @return Playfield which holds current state of the game.
     */
    public Playfield getCurrent() {
        return fields[fieldIndex];
    }

    /**
     * Move the game forward.
     * @param rules rules to generate next Playfield
     */
    public void generateNext(RuleInterface rules) {
        final Playfield oldField = getCurrent();
        fieldIndex = getNextIndex();
        final Playfield newField = getCurrent();
        
        oldField.generateNext(rules, newField);
    }
}
