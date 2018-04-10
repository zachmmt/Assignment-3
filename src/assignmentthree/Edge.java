/*Authors: Ryan Hansen and Zach Miller
* Date: due, submitted 4/10/18
* Overview: This program constructs a graph from an input adjacency
*           matrix in the form of a comma separated file. It then demonstrates
*           Prim's, Kustal's, and Floyd-Warshall's Algorithms. Controls
*           for the program are found at Main.java line 102. Inputs can
*           be controlled by editing input\\input.cvs.
*/

package assignmentthree;

public class Edge {
    private int weight;
    private Node tail;
    private Node head;
    
    public Edge(Node tail, Node head, int weight){
        this.tail = tail;
        this.head = head;
        this.weight = weight;
    }
    
    //getters
    public int getWeight() {
        return weight;
    }
    public Node getTail() {
        return tail;
    }
    public Node getHead() {
        return head;
    }

    //setters
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void setTail(Node tail) {
        this.tail = tail;
    }
    public void setHead(Node head) {
        this.head = head;
    }
    
    
    
}
