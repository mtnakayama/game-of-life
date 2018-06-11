/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

/**
 * Classic rules for Conway's Game of Life
 * @author Matthew
 */
public class ConwayRules implements RuleInterface {

    @Override
    public boolean nextState(boolean cellAlive, int neighbors) {
        if(cellAlive) {
            return (neighbors == 2 || neighbors == 3);
        } else { /* cell dead */
            return (neighbors == 3);  // reproduction
        }
    }
}
