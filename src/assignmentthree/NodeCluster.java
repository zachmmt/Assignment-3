package assignmentthree;

public class NodeCluster {
    final private Node [] nodes;
    
    //clusters made empty with specified size
    public NodeCluster(int nodeCount){
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
}
