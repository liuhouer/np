package cn.northpark.test.datastruct.graph;

import java.util.Queue;
import java.util.Stack;

public class TestGraph {
	public static void main(String[] args) {
		GraphInterface<String> graph = new DirectedGraph<>();
		System.out.println("Graph is empty? " + graph.isEmpty());
		
		System.out.println("Adding vertexs...  添加顶点");
//		graph.addVertex("A");graph.addVertex("B"); 1持仓表 -2证券主表 -3股票 |4基金|5债券 -6股票分类- 7基金分类- 8债券分类
//		graph.addVertex("C");graph.addVertex("D");
//		graph.addVertex("E");
		graph.addVertex("1");graph.addVertex("2");
		graph.addVertex("3");graph.addVertex("4");
		graph.addVertex("5");graph.addVertex("6");
		graph.addVertex("7");graph.addVertex("8");
		System.out.println("Number of graph's vertex = " + graph.getNumberOfVertices());//5
		
		/*
		 *   <A,D>  <A,C>  <A,B>  <D,C>  <C,E>
		 *   <1,2>  <2,3>  <2,4>  <2,5>  <3,6>  <4,7> <5,8> 
		 */
		System.out.println("Adding edges...  添加边");
		graph.addEdge("1", "2");graph.addEdge("2", "3");
		graph.addEdge("2", "4");graph.addEdge("2", "5");
		graph.addEdge("3", "6");graph.addEdge("4", "7");
		graph.addEdge("5", "8");
		System.out.println("Number of graph's edge = " + graph.getNumberOfEdges());//5
		
		System.out.println("vertexs between 3 and 4 has Edges? " + graph.hasEdge("3", "4"));//false
		System.out.println("vertex between 4 and 5 has Edges? " + graph.hasEdge("4", "5"));//true
		
		System.out.println("Breadth First traverse graph with initial vertex '1'...   广度优先排序");
		Queue<String> bfsTraversalOrder = graph.getBreadthFirstTraversal("1");//A D C B E
		while(!bfsTraversalOrder.isEmpty())
			System.out.print(bfsTraversalOrder.poll() + " ");
		System.out.println();
		System.out.println("------------------------------------------------------------");
		
		System.out.println("\nDFS traverse graph with inital vertex '1'...   深度优先排序");
		Queue<String> dfsTraversalOrder = graph.getDepthFirstTraversal("1");
		while(!dfsTraversalOrder.isEmpty())
			System.out.print(dfsTraversalOrder.poll() + " ");
		System.out.println();
		System.out.println("------------------------------------------------------------");
		System.out.println("\nTopological Order   有向拓扑排序");
		System.out.println();
		System.out.println("------------------------------------------------------------");
		Stack<String> stack = graph.getTopologicalSort();
		while(!stack.isEmpty())
			System.out.print(stack.pop() + " ");
		
		System.out.println();
		//计算顶点的最短路径
		Stack<String> path = new Stack<String>();
		graph.getShortestPath("1", "4", path);
		
		//打印路径
		while(!path.isEmpty())
					System.out.print(path.pop() + "---> ");
		System.out.println();
		graph.getShortestPath("1", "8", path);
		
		//打印路径
				while(!path.isEmpty())
							System.out.print(path.pop() + "---> ");
				System.out.println();
		System.out.println("shortestPath---->"+path);
		System.out.println("\ncleanning graph");
		graph.clear();
		System.out.println("Now, number of vertexs = " + graph.getNumberOfVertices());
		
	}
}
