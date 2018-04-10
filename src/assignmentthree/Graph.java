/*Authors: Ryan Hansen and Zach Miller
* Date: due, submitted 4/10/18
* Overview: This program constructs a graph from an input adjacency
*           matrix in the form of a comma separated file. It then demonstrates
*           Prim's, Kustal's, and Floyd-Warshall's Algorithms. Controls
*           for the program are found at Main.java line 102. Inputs can
*           be controlled by editing input\\input.cvs.
*/

package assignmentthree;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Graph{
    private int nodeCount;
    private int edgeCount;
    private Edge [][] edges;
    private Node [] nodes;
    
    public Graph(){
    }
    
    //Sets the size of the graph and initializes its matrices
    public void startGraph(int nodeCount){
        this.nodeCount = nodeCount;
        edges = new Edge [nodeCount][nodeCount];
        nodes = new Node [nodeCount];
    }
    
    //Adds a node to a non-full(!) graph
    public void addNode(Node a){
        int i = 0;
        while(nodes[i] != null){
            i++;
        }
        nodes[i] = a;
        nodes[i].setIndex(i);
    }
    
    //Adds an Edge object to the graph (one directional)
    public void addEdge(Edge edge){
        edges[edge.getTail().getIndex()][edge.getHead().getIndex()] = edge;
    }
    
    //Increases edgeCount by one
    public void incEdgeCount(){
        edgeCount++;
    }
    
    //Creates a copy of a Graph
    
    
    //Algorithms
    //Prim's algorithm
    public static Graph PrimsMST(Graph G){
        //Set primKeys
        G.getNode(0).setPrimKey(0);
        for(int i=1; i<G.getNodeCount(); i++){
            G.getNode(i).setPrimKey(Integer.MAX_VALUE);
        }
        
        
        
        //Initialize priority queue
        Comparator<Node> comparator = new NodeComparator();
        PriorityQueue<Node> NodePQ = new PriorityQueue<>(comparator);
        for(int i=0; i<G.getNodeCount(); i++){
            NodePQ.add(G.getNode(i));
        }
        
        //Initialize T with nodes
        Graph T = new Graph();
        T.startGraph(G.getNodeCount());
        for(int i=0; i<G.getNodeCount(); i++){
            T.addNode(G.getNode(i));
        }
        
        //Prim's process
        while(!NodePQ.isEmpty()){
            Node u = NodePQ.poll();
            for(int column=0; column<G.getNodeCount(); column++){
                Node v = G.getEdge(u.getIndex(), column).getHead();
                if(NodePQ.contains(v) && G.getEdge(u.getIndex(), v.getIndex()).getWeight() < v.getPrimKey()){
                    T.addEdge(new Edge(u, v, G.getEdge(u.getIndex(), v.getIndex()).getWeight()));
                    v.setPrimKey(G.getEdge(u.getIndex(), v.getIndex()).getWeight());
                }
            }
        }
        
        //Fill in self and non-existent edges
        for(int r=0; r<T.getNodeCount(); r++){
            for(int c=0; c<T.getNodeCount(); c++){
                if(T.getEdge(r,c) == null){     //didn't receive an edge
                    if(r==c){                   //self edge
                        T.addEdge(new Edge(T.getNode(r), T.getNode(c), 0));
                    }else{
                        T.addEdge(new Edge(T.getNode(r), T.getNode(c), Integer.MAX_VALUE));
                    }
                }
            }
        }
        
        return T;
    }
    
    //Kruskal's algorithm
    public static Graph KruskalsMST(Graph G){
        //generate cluster
        for(int i=0; i<G.getNodeCount(); i++){
            G.getNodeMatrix()[i].setCluster(new NodeCluster(G.getNodeCount()));
        }
        
        //Initialize priority queue
        Comparator<Edge> comparator = new EdgeComparator();
        PriorityQueue<Edge> EdgePQ = new PriorityQueue<>(comparator);
        
        //fill priority queue
        for(int column=1; column<=G.getNodeCount()-1; column++){
            for(int row=0; row<column; row++){
                if(G.getEdgeMatrix()[row][column].getWeight() != Integer.MAX_VALUE){
                    EdgePQ.add(G.getEdgeMatrix()[row][column]);
                }
            }
        }
        
        //create output graph with nodes (no edges)
        Graph T = new Graph();
        T.startGraph(G.getNodeCount());
        for(int i=0; i<G.getNodeCount(); i++){
            T.addNode(G.getNode(i));
        }
        
        //adding nodes, merging clusters, printing edges
        System.out.print("Edges in Kruskal's MST are: ");
        boolean firstEdge = true;
        while(T.getEdgeCount() < T.getNodeCount()-1){
            if(EdgePQ.isEmpty()){ //We have run out of edges, and the graph is still unconnected: no MST
                System.out.println();
                System.out.println("There is no MST");
                break;
            }else{ //We have an edge and need to check it out
                Edge nextEdge = EdgePQ.poll();
                Node nextTail = nextEdge.getTail();
                Node nextHead = nextEdge.getHead();
                if(nextTail.getCluster() != nextHead.getCluster()){ //These clusters are unconnected. Add edge to MST
                    for(int i=0; i<T.nodeCount; i++){ //move all nodes to new cluster
                        if(nextTail.getCluster().getNode(i) != null){
                            nextTail.getCluster().getNode(i).setCluster(nextHead.getCluster());
                            nextHead.getCluster().addNode(nextTail.getCluster().getNode(i));
                        }
                    }
                    
                    if(!firstEdge){
                        System.out.print(", ");
                    }
                    
                    //add edge to graph
                    T.addEdge(nextEdge);
                    Edge reverse = new Edge(nextEdge.getHead(), nextEdge.getTail(), nextEdge.getWeight());
                    T.addEdge(reverse);
                    T.incEdgeCount();
                    System.out.print(nextEdge.getTail().getLabel());
                    System.out.print(nextEdge.getHead().getLabel());
                    firstEdge = false;
                }
            }
        }
        System.out.println();
        
        //Fill in empty and self edges
        for(int row=0; row<T.nodeCount; row++){
            for(int column=0; column<T.nodeCount; column++){
                if(T.getEdge(row, column) == null){
                    if(row == column){ //connects node to self: weight=0
                        Edge selfEdge = new Edge(T.getNode(row), T.getNode(row), 0);
                        T.addEdge(selfEdge);
                    }else{
                        Edge falseEdge =  new Edge(T.getNode(row), T.getNode(column), Integer.MAX_VALUE);
                        T.addEdge(falseEdge);
                    }
                }
            }
        }
        return T;
    }
    
    //FloydWarshallsSP
    public static Graph floydWarshallsSP(Graph G){
        
        //create output graph with nodes (no edges)
        Graph T = new Graph();
        T.startGraph(G.getNodeCount());
        for(int i=0; i<G.getNodeCount(); i++){
            T.addNode(G.getNode(i));
        }
        
        //Copy edges from G
        for(int row=0; row<T.getNodeCount(); row++){
            for(int column=0; column<T.getNodeCount(); column++){
                T.addEdge(new Edge(G.getNode(row), G.getNode(column), G.getEdge(row,column).getWeight()));
            }
        }
        
        //Begin output
        System.out.println("Begin Floyd-Warshall process");
        System.out.println();
        System.out.println("Copied graph:");
        T.printMatrix();
        
        //move connections from G to T
        for(int k=0; k<T.getNodeCount(); k++){
            for(int i=0; i<T.getNodeCount(); i++){
                for(int j=0; j<T.getNodeCount(); j++){
                    if(T.getEdge(i,k).getWeight() != Integer.MAX_VALUE && T.getEdge(k,j).getWeight() != Integer.MAX_VALUE){
                        if(T.getEdge(i,j).getWeight() > T.getEdge(i,k).getWeight() + T.getEdge(k,j).getWeight()){
                            T.getEdge(i,j).setWeight(T.getEdge(i,k).getWeight() + T.getEdge(k,j).getWeight());

                            //Print adjacency matrix for every iteration it changes
                            System.out.println("Iteration (i,j,k): " + "(" + i + "," + j + "," + k + ")");
                            T.printStepMatrix(i,j);
                        }
                    }
                }
            }
        }
        
        
        
        
        
        return T;
    }
    
    //Printers
    //Prints a list of the nodes, index & label
    public void printNodes(){
        for(int i=0; i<nodeCount; i++){
            System.out.println("Node " + i + " has label " + nodes[i].getLabel());
        }
    }
    //Prints the adjacency matrix of the graph
    public void printMatrix(){
        //Title
        System.out.println("Printing adjacency matrix:");
        
        //First row (nodes)
        for(int i=0; i<nodeCount; i++){
            System.out.print(nodes[i].getLabel());
            if(i<nodeCount-1){
                System.out.print(",");
            }
        }
        System.out.println();
        
        //Rest of the rows (edges)
        for (int i=0; i<nodeCount; i++){
            for (int j=0; j<nodeCount; j++){
                if(edges[i][j].getWeight() != Integer.MAX_VALUE){
                    System.out.print(edges[i][j].getWeight());
                }else{
                    System.out.print("-");
                }
                if(j<nodeCount-1){
                    System.out.print(",");
                }
            }
            System.out.println();
        }
        
        //Note:
        //System.out.println("Note: - denotes non-adjacency, 0 denotes (imaginary) self edge");
        System.out.println();
    }
    
    //puts a * before and after the changed edge (step matrix)
    public void printStepMatrix(int row, int column){
        //Title
        System.out.println("Printing step adjacency matrix:");
        
        //First row (nodes)
        for(int i=0; i<nodeCount; i++){
            System.out.print(nodes[i].getLabel());
            if(i<nodeCount-1){
                System.out.print(",");
            }
        }
        System.out.println();
        
        //Rest of the rows (edges)
        for (int i=0; i<nodeCount; i++){
            for (int j=0; j<nodeCount; j++){
                
                //change marker 1
                if(i==row && j==column){
                    System.out.print("*");
                }
                
                //print value
                if(edges[i][j].getWeight() != Integer.MAX_VALUE){
                    System.out.print(edges[i][j].getWeight());
                }else{
                    System.out.print("-");
                }
                
                //change marker 2 and optional comma
                if(i==row && j==column){
                    System.out.print("*");
                }
                if(j<nodeCount-1){
                    System.out.print(",");
                }
            }
            System.out.println();
        }
        
        //Note:
        //System.out.println("Note: - denotes non-adjacency, 0 denotes (imaginary) self edge");
        System.out.println();
    }
    
    //getters
    public Node getNode(int i){
        return nodes[i];
    }
    public Node [] getNodeMatrix(){
        return this.nodes;
    }
    public int getNodeCount(){
        return nodeCount;
    }
    public Edge [][] getEdgeMatrix(){
        return edges;
    }
    public int getEdgeCount(){
        return edgeCount;
    }
    public Edge getEdge(int row, int column){
        return edges[row][column];
    }

}
