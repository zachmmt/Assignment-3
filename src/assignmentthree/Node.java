/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package assignmentthree;

/**
 *
 * @author Zach Miller
 */
public class Node {
    private int index;
    private char label;
    
    public Node(char label, int index){
        this.label = label;
        this.index = index;
    }
    
    public Node(char label){
        label = this.label;
    }
    
    public Node(){
        
    }

    public int getIndex() {
        return index;
    }

    public char getLabel() {
        return label;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setLabel(char label) {
        this.label = label;
    }
    
    
}
