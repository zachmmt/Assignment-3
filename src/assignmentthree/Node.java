/*Authors: Ryan Hansen and Zach Miller
* Date: due, submitted 4/10/18
* Overview: This program constructs a graph from an input adjacency
*           matrix in the form of a comma separated file. It then demonstrates
*           Prim's, Kustal's, and Floyd-Warshall's Algorithms. Controls
*           for the program are found at Main.java line 102. Inputs can
*           be controlled by editing input\\input.cvs.
*/

package assignmentthree;

public class Node {
    private int index;
    private char label;
    private NodeCluster cluster;
    private int primKey;
    
    public Node(){
    }

    //getters
    public int getIndex() {
        return index;
    }
    public char getLabel() {
        return label;
    }
    public NodeCluster getCluster() {
        return cluster;
    }
    public int getPrimKey() {
        return primKey;
    }
    
    //setters
    public void setIndex(int index) {
        this.index = index;
    }
    public void setLabel(char label) {
        this.label = label;
    }
    public void setCluster(NodeCluster cluster) {
        this.cluster = cluster;
    }
    public void setPrimKey(int primKey) {
        this.primKey = primKey;
    }
    
}
