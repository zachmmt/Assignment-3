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
