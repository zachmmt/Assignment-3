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
    private String label;
    
    public Node(String label, int index){
        label = this.label;
        index = this.index;
    }
    
    public Node(String label){
        label = this.label;
    }

    public int getIndex() {
        return index;
    }

    public String getLabel() {
        return label;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    
}
