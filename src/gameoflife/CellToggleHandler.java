/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

/**
 * Called when a cell is clicked to be toggled.
 * @param x x-coordinate of clicked cell
 * @param y y-coordinate of clicked 
 * @author Matthew
 */

@FunctionalInterface
public interface CellToggleHandler {
    void toggle(int x, int y);
}
