/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

/**
 * Interface for the Game of Life rules.
 * @author Matthew
 */

@FunctionalInterface
public interface RuleInterface {
    /**
     * Generates the next state of a cell.
     * @param cellAlive current state of this cell
     * @param neighbors number of alive neighbor cells
     * @return 
     */
    public boolean nextState(boolean cellAlive, int neighbors);
}
