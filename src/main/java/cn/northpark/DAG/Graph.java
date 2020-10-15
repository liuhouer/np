package cn.northpark.DAG;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author liuhouer
 * @param <T>
 */
@Slf4j
public class Graph<T extends Comparable> implements GraphInterface<T>, java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Map<T, VertexInterface<T>> vertices;//map 对象用来保存图中的所有顶点.T 是顶点标识,VertexInterface为顶点对象
    private int edgeCount;//记录图中 边的总数

    public Graph() {
        vertices = new LinkedHashMap<>();//按顶点的插入顺序保存顶点,这很重要,因为这会影响到后面图的遍历算法的正确性
    }

    @Override
    public void addVertex(T vertexLabel) {
        //若顶点相同时,新插入的顶点将覆盖原顶点,这是由LinkedHashMap的put方法决定的
        //每添加一个顶点,会创建一个LinkedList列表,它存储该顶点对应的邻接点,或者说是与该顶点相关联的边
        vertices.put(vertexLabel, new Vertex(vertexLabel));//new Vertex 对象,会创建一个LinkedList,该LinkedList用来表示该顶点的邻接表
    }

    @Override
    public void addVertex(T vertexLabel, int index) {
        //若顶点相同时,新插入的顶点将覆盖原顶点,这是由LinkedHashMap的put方法决定的
        //每添加一个顶点,会创建一个LinkedList列表,它存储该顶点对应的邻接点,或者说是与该顶点相关联的边
        vertices.put(vertexLabel, new Vertex(vertexLabel, index+1));//new Vertex 对象,会创建一个LinkedList,该LinkedList用来表示该顶点的邻接表
    }

    @Override
    public void addVertex(T vertexLabel, Map<String, String> diminfos) {
        //若顶点相同时,新插入的顶点将覆盖原顶点,这是由LinkedHashMap的put方法决定的
        //每添加一个顶点,会创建一个LinkedList列表,它存储该顶点对应的邻接点,或者说是与该顶点相关联的边
        vertices.put(vertexLabel, new Vertex(vertexLabel, diminfos));//new Vertex 对象,会创建一个LinkedList,该LinkedList用来表示该顶点的邻接表
    }

    @Override
    public boolean addEdge(T begin, T end, double edgeWeight) {
        boolean result = false;
        VertexInterface<T> beginVertex = vertices.get(begin);//获得表示边的起始顶点
        VertexInterface<T> endVertex = vertices.get(end);//获得表示 边的终点

        if (beginVertex != null && endVertex != null)
            result = beginVertex.connect(endVertex, edgeWeight);//起始点与终点连接,即成一条边
        if (result)
            edgeCount++;
        return result;//当添加重复边时会返回 false
    }

    @Override
    public boolean addEdge(T begin, T end) {
        return addEdge(begin, end, 0);//当添加重复边时会返回 false
    }


    @Override
    public boolean addEdge(T begin, T end, Map<String, String> colInfo) {
        // TODO Auto-generated method stub
        boolean result = false;
        VertexInterface<T> beginVertex = vertices.get(begin);//获得表示边的起始顶点
        VertexInterface<T> endVertex = vertices.get(end);//获得表示 边的终点

        if (beginVertex != null && endVertex != null)
            result = beginVertex.connect(endVertex, colInfo);//起始点与终点连接,即成一条边
        if (result)
            edgeCount++;
        return result;//当添加重复边时会返回 false
    }

    @Override
    public boolean hasEdge(T begin, T end) {
        boolean found = false;
        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);
        if (beginVertex == null || endVertex == null || beginVertex.hasNeighbor() == false)
            return found;
        Iterator<VertexInterface<T>> neighbors = beginVertex.getNeighborInterator();
        while (!found && neighbors.hasNext()) {//在起始顶点的邻接表中查找 结束顶点endVertex
            VertexInterface<T> neighbor = neighbors.next();
            if (endVertex.equals(neighbor))
                found = true;//若找到了,结束顶点endVertex. 说明beginVertex和endVertex之间是有边的
        }//end while
        return found;
    }

    @Override
    public Edge getEdge(T begin, T end) {
        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);
        if (hasEdge(begin, end)) {
            List<Edge> edgeList = beginVertex.getEdgeList();

            if (!CollectionUtils.isEmpty(edgeList)) {
                for (Edge e1 : edgeList) {
                    if (e1.getEndVertex().equals(endVertex)) {
                        return e1;
                    }

                }
            }

        }
        return null;
    }


    @Override
    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    @Override
    public int getSizeOfVertices() {
        return vertices.size();
    }

    @Override
    public int getSizeOfEdges() {
        return edgeCount;
    }

    @Override
    public void clear() {
        vertices.clear();
        edgeCount = 0;
    }

    @Override
    public Queue<T> getDepthFirstTraversal(T origin) {
        resetVertices();//先将所有的顶点初始化
        LinkedList<VertexInterface<T>> vertexStack = new LinkedList<>();//辅助DFS递归遍历
        Queue<T> traversalOrder = new LinkedList<>();//保存DFS遍历顺序

        VertexInterface<T> originVertex = vertices.get(origin);//根据起始顶点的标识获得起始顶点
        originVertex.visit();//访问起始顶点,起始顶点的出度不能为0(只考虑多于一个顶点的连通图),若为0,它就没有邻接点了
        vertexStack.push(originVertex);//各个顶点的入栈顺序就是DFS的遍历顺序
        traversalOrder.offer(originVertex.getLabel());//每当一个顶点入栈时,就将它入队列,从而队列保存了整个遍历顺序

        while (!vertexStack.isEmpty()) {
            VertexInterface<T> topVertex = vertexStack.peek();
            //找到该顶点的一个未被访问的邻接点,从该邻接点出发又去遍历邻接点的邻接点,也即遍历了所有的边--复杂度O(E)
            VertexInterface<T> nextNeighbor = topVertex.getUnvisitedNeighbor();
            if (nextNeighbor != null) {
                nextNeighbor.visit();
                //由于用的是if,在这里push邻接点后,下一次while循环pop的是该邻接点,然后又获得它的邻接点,---DFS
                vertexStack.push(nextNeighbor);
                traversalOrder.offer(nextNeighbor.getLabel());
            } else
                vertexStack.pop();//当某顶点的所有邻接点都被访问了时,直接将该顶点pop,这样下一次while pop 时就回溯到前一个顶点
        }//end while
        return traversalOrder;
    }

    @Override
    public Queue<T> getBreadthFirstTraversal(T origin) {//origin 标识遍历的初始顶点
        resetVertices();//将顶点的必要数据域初始化,复杂度为O(V)
        Queue<VertexInterface<T>> vertexQueue = new LinkedList<>();//保存遍历过程中遇到的顶点,它是辅助遍历的,有出队列操作
        Queue<T> traversalOrder = new LinkedList<>();//保存遍历过程中遇到的 顶点标识--整个图的遍历顺序就保存在其中,无出队操作
        VertexInterface<T> originVertex = vertices.get(origin);//根据顶点标识获得初始遍历顶点
        originVertex.visit();//访问该顶点
        traversalOrder.offer(originVertex.getLabel());
        vertexQueue.offer(originVertex);

        while (!vertexQueue.isEmpty()) {
            VertexInterface<T> frontVertex = vertexQueue.poll();//出队列,poll()在队列为空时返回null
            Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborInterator();
            while (neighbors.hasNext())//对于 每个顶点都遍历了它的邻接表,即遍历了所有的边,复杂度为O(E)
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited()) {
                    nextNeighbor.visit();//广度优先遍历未访问的顶点
                    traversalOrder.offer(nextNeighbor.getLabel());
                    vertexQueue.offer(nextNeighbor);//将该顶点的邻接点入队列
                }
            }//end inner while
        }//end outer while
        return traversalOrder;
    }

    @Override
    public Stack<T> getTopologicalSort() {
        /**
         *相比于《算法导论》中的拓扑排序借助了DFS复杂度为O(V+E),该算法的时间复杂度较大
         *因为算法导论中介绍的图的数据结构与此处实现的图的数据结构不同
         *此算法的最坏时间复杂度为O(V*(V+E))==V * max{V,E}
         */
        resetVertices();//先将所有的顶点初始化

        Stack<T> vertexStack = new Stack<>();//存放已访问的顶点的栈,该栈就是一个拓扑序列
        int numberOfVertices = vertices.size();//获得图中顶点的个数

        for (int counter = 1; counter <= numberOfVertices; counter++) {
            VertexInterface<T> nextVertex = getNextTopologyOrder();//获得一个未被访问的且出度为0的顶点
            if (nextVertex != null) {
                nextVertex.visit();
                vertexStack.push(nextVertex.getLabel());//遍历完成后,出栈就可以获得图的一个拓扑序列
            }
        }
        return vertexStack;
    }

    private VertexInterface<T> getNextTopologyOrder() {//最坏情况下复杂度为O(V+E)
        VertexInterface<T> nextVertex = null;
        Iterator<VertexInterface<T>> iterator = vertices.values().iterator();//获得图的顶点的迭代器
        boolean found = false;
        while (!found && iterator.hasNext()) {
            nextVertex = iterator.next();
            //寻找出度为0且未被访问的顶点
            if (nextVertex.isVisited() == false && nextVertex.getUnvisitedNeighbor() == null)
                found = true;
        }
        return nextVertex;
    }

    @Override
    public int getShortestPath(T begin, T end, Stack<T> path) {
        resetVertices();
        boolean done = false;//标记整个遍历过程是否完成
        Queue<VertexInterface<T>> vertexQueue = new LinkedList<>();
        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);

        beginVertex.visit();
        vertexQueue.offer(beginVertex);
        //Assertion: resetVertices() 已经对 beginVertex 执行了 setCost(0)

        while (!done && !vertexQueue.isEmpty()) {
            VertexInterface<T> frontVertex = vertexQueue.poll();
            Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborInterator();
            while (!done && neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited()) {
                    nextNeighbor.visit();
                    nextNeighbor.setPredecessor(frontVertex);
                    nextNeighbor.setCost(frontVertex.getCost() + 1);
                    vertexQueue.offer(nextNeighbor);
                }//end if

                if (nextNeighbor.equals(endVertex))
                    done = true;
            }//end inner while
        }//end outer while. and traverse over


        int pathLength = (int) endVertex.getCost();
        path.push(endVertex.getLabel());

        VertexInterface<T> vertex = endVertex;
        while (vertex.hasPredecessor()) {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        }


        return pathLength;
    }

    @Override
    public double getCheapestPath(T begin, T end, Stack<Edge<T>> path){
        return Dijkstra(begin,end,path);
    }

    @Override
    public double Dijkstra(T begin, T end, Stack<Edge<T>> path) {
        
        resetVertices();
        VertexInterface<T> beginVertex = vertices.get(begin);
        VertexInterface<T> endVertex = vertices.get(end);

        Preconditions.checkNotNull(beginVertex.getID(), "带权图-构造函数创建的顶点对象ID不能为空");


        // 使用索引堆记录当前找到的到达每个顶点的最短距离
        IndexMinHeap<Integer> ipq = new IndexMinHeap<Integer>(getSizeOfVertices() + 1);

        beginVertex.visit();
        ipq.insert(0, 9999); //0位置占位
        ipq.insert(beginVertex.getID(), 8888); //起点,设置个最大值，因为这个为0没意义

        Number[] distTo = new Number[getSizeOfVertices() + 1];     // distTo[i]存储从起始点s到i的最短路径长度
        Edge<T>[] from = new Edge[getSizeOfVertices() + 1];  // from[i]记录最短路径中, 到达i点的边是哪一条

        Map<Integer, VertexInterface<T>> vertexIDMap = new TreeMap<>();

        // 对于起始点s进行初始化
        distTo[beginVertex.getID()] = 0.0;

        //起点设置
        vertexIDMap.put(beginVertex.getID(), beginVertex);

        while (!ipq.isEmpty() && vertexIDMap.get(ipq.getMinIndex()) != null) {//排除最小索引堆的0占位符
            int v = ipq.extractMinIndex();

            //1.从起点找到所有相邻点，并且标记所有花费
            VertexInterface<T> frontVertex = vertexIDMap.get(v);
            Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborInterator();
            while (neighbors.hasNext()) {
                VertexInterface<T> nextNeighbor = neighbors.next();
                //一次遍历过程完成map的映射
                vertexIDMap.put(nextNeighbor.getID(), nextNeighbor);

                // 如果nextNeighbor点以前没有访问过,
                // 或者访问过, 但是通过当前的v点到nextNeighbor点距离更短, 则进行更新
                Edge edge = getEdge(frontVertex.getLabel(), nextNeighbor.getLabel());
                Double weight = edge.getWeight();

                int id = nextNeighbor.getID();

                if (from[id] == null || distTo[v].doubleValue() + weight < distTo[id].doubleValue()) {
                    distTo[id] = distTo[v].doubleValue() + weight;
                    //设置前驱节点表示为路径
                    edge.setStart(frontVertex);
                    from[id] = edge;
                    //设置前驱节点表示为路径

                    if (ipq.contain(id)) {

                        ipq.change(id, distTo[id].intValue());
                    } else {

                        ipq.insert(id, distTo[id].intValue());
                    }
                }
            }//end inner while
        }//end outer while. and traverse over

        int endID = endVertex.getID();
        Edge<T> e = from[endID];

        while (e.getStart() != beginVertex) {
            path.push(e);
            e = from[e.getStart().getID()];
        }
        path.push(e); //e.parent = start , add e
        //最短路径长度
        return distTo[endID].intValue();
    }

    //设置顶点的初始状态,时间复杂度为O(V)
    protected void resetVertices() {
        Iterator<VertexInterface<T>> vertexIterator = vertices.values().iterator();
        while (vertexIterator.hasNext()) {
            VertexInterface<T> nextVertex = vertexIterator.next();
            nextVertex.unVisit();
            nextVertex.setCost(0);
            nextVertex.setPredecessor(null);
        }//end while
    }

    @Override
    public void printVertex() {
        Iterator<VertexInterface<T>> vertexIterator = vertices.values().iterator();
        while (vertexIterator.hasNext()) {
            VertexInterface<T> nextVertex = vertexIterator.next();
            System.out.println(nextVertex.toString());
        }//end while
    }

}
