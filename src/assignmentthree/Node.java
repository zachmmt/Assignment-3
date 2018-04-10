package assignmentthree;

public class Node {
    private int index;
    private char label;
    private NodeCluster cluster;
    
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
    
}
