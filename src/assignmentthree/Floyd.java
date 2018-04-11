/*Authors: Ryan Hansen and Zach Miller
* Date: due, submitted 4/10/18
* Overview: Given our input graph, This class uses Floyd=Warshall's algorithm to find all
            traversable paths and outputs a new adjacency matrix to depict it.
*/
package assignmentthree;

/**
 *
 * @author Ryan
 */
public class Floyd {
    private Graph G;
    private Graph T;
    Floyd(Graph gr){
        
        G = gr;
    }
    
    public Graph floydWarshallsSP(){
        
        //create output graph with nodes (no edges)
        T = new Graph();
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
}
