/*Authors: Ryan Hansen and Zach Miller
* Date: due, submitted 4/10/18
* Overview: Given our input graph, This class uses Primm's algorithm to find the
            Minimum spanning tree of it.
*/
package assignmentthree;

import java.util.Comparator;
import java.util.PriorityQueue;


public class Prim {
    private Graph G;
    private Graph T;
    Prim(Graph gr){
        
        G = gr;
    }
    
    
    //Prim's algorithm
    public Graph PrimsMST(){
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
        T = new Graph();
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
        
        //Print MST edges to output
        System.out.print("- Prim's MST Edges");   
        for (int i=0; i<T.getNodeCount(); i++){
            for (int j=0; j<T.getNodeCount(); j++){
                if(T.edges[i][j].getWeight() != Integer.MAX_VALUE && T.edges[i][j].getWeight() != 0){
                    System.out.print(", ");
                    System.out.print(T.edges[i][j].getTail().getLabel());
                    System.out.print(T.edges[i][j].getHead().getLabel());
                    
                    
                }
                        
            }
        }
        System.out.println(" -");
        System.out.println();
        return T;
    }
}
