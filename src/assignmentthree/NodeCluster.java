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
