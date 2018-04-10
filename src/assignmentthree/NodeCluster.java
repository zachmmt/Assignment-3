/*Authors: Ryan Hansen and Zach Miller
* Date: due, submitted 4/10/18
* Overview: This program constructs a graph from an input adjacency
*           matrix in the form of a comma separated file. It then demonstrates
*           Prim's, Kustal's, and Floyd-Warshall's Algorithms. Controls
*           for the program are found at Main.java line 102. Inputs can
*           be controlled by editing input\\input.cvs.
*/

package assignmentthree;

public class NodeCluster {
    final private Node [] nodes;
    final private int nodeCount;
    
    //clusters made empty with specified size
    public NodeCluster(int nodeCount){
        this.nodeCount = nodeCount;
        nodes = new Node [nodeCount];
    }

    //returns node at a given index
    public Node getNode(int index){
        return nodes[index];
    }
    
    //sets the node at a given index
    public void setNode(Node node, int index){
        nodes[index] = node;
    }
    
    //adds a Node to the next open space in the array. returns boolean based on succes
    public boolean addNode(Node node){
        boolean nodeIn = false;
        int i=0;
        while(!nodeIn){
            if(nodes[i] != null){
                nodes[i] = node;
                nodeIn = true;
                return true;
            }else{
                i++;
            }
        }
        return false;
    }
}
