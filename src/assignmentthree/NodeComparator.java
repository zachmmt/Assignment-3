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

public class NodeComparator implements Comparator<Node>{

    @Override
    public int compare(Node o1, Node o2) {
        if(o1.getPrimKey() < o2.getPrimKey()){
            return -1;
        }else if(o1.getPrimKey() == o2.getPrimKey()){
            return 0;
        }else{
            return 1;
        }
    }

}
