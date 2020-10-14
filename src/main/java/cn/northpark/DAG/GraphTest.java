package cn.northpark.DAG;

import java.util.Stack;

/**
 * @author liuhouer
 * @date 2020年10月14日 09:25:10
 */
public class GraphTest {
    public static void main(String[] args) {
        //   1
        //  / \
        // 2   3
        //    /  \
        //   5    4
        //         \
        //          6
        //           \
        //            5

        //不带权 【1，5】 = 1-》3——》5
        //带权   【1，5】 = 1-3-4-6-5

        //   1
        // 2/ \1
        // 2   3
        //    /6 \1
        //   5    4
        //         \1
        //          6
        //           \1
        //            5

        GraphInterface<String> graph = new Graph<>();
        System.out.println("Adding vertexs...  添加顶点");
        // 添加顶点==============================================================
//        for (int i = 0; i < 6; i++) {
//            graph.addVertex(i+1+"",graph.getSizeOfVertices());
            graph.addVertex("0",graph.getSizeOfVertices());
            graph.addVertex("1",graph.getSizeOfVertices());
            graph.addVertex("2",graph.getSizeOfVertices());
            graph.addVertex("3",graph.getSizeOfVertices());
            graph.addVertex("4",graph.getSizeOfVertices());
            graph.addVertex("5",graph.getSizeOfVertices());
            graph.addVertex("8",graph.getSizeOfVertices());
//        }

        System.out.println("Adding Edge.java...  添加边  ");
        // 添加边==============================================================
//        graph.addEdge("1","2",2);
//        graph.addEdge("1","3",1);
//        graph.addEdge("3","5",6);
//        graph.addEdge("3","4",1);
//        graph.addEdge("4","6",1);
//        graph.addEdge("6","5",1);




        graph.addEdge("5" ,"8", 0);
        graph.addEdge("0" ,"1", 5);
        graph.addEdge("0" ,"2", 2);
        graph.addEdge("0" ,"3", 6);
        graph.addEdge("1" ,"4", 1);
        graph.addEdge("2" ,"1", 1);
        graph.addEdge("2" ,"4", 5);
        graph.addEdge("2" ,"3", 3);
        graph.addEdge("3" ,"4", 2);

        graph.printVertex();

        Stack<Edge<String>> path = new Stack<>();
//        int shortestPath = graph.getShortestPath("1", "5", path);
        graph.getCheapestPath("0", "4", path);

        while (!path.isEmpty()){
            Edge<String> pop = path.pop();
            System.err.println(pop.getStart().getLabel());
            System.err.println(" ↓ ");
        }
        System.err.println("4");

    }
}
