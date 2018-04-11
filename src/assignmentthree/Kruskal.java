/*Authors: Ryan Hansen and Zach Miller
* Date: due, submitted 4/10/18
* Overview: Given our input graph, This class uses Kruskal's algorithm to find the
            Minimum spanning tree of it.
*/
package assignmentthree;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * @author Ryan
 */
public class Kruskal {
    
    private Graph G;
    private Graph T;
    Kruskal(Graph gr){
        
        G = gr;
    }
    
    
    //Kruskal's algorithm
    public Graph KruskalsMST(){
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
        T = new Graph();
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
                    for(int i=0; i<T.getNodeCount(); i++){ //move all nodes to new cluster
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
        for(int row=0; row<T.getNodeCount(); row++){
            for(int column=0; column<T.getNodeCount(); column++){
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
}
