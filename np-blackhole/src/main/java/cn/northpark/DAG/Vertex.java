package cn.northpark.DAG;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuhouer
 * @param <T>
 * 顶点的实现
 */
class Vertex<T> implements VertexInterface<T>, java.io.Serializable {

    private T label;//标识标点,可以用不同类型来标识顶点如String,Integer....
    private AtomicInteger id ;//标识标点序号，用于对应索引堆的序号
    private List<Edge> edgeList;//到该顶点邻接点的边,实际以java.util.LinkedList存储
    private boolean visited;//标识顶点是否已访问
    private VertexInterface<T> previousVertex;//该顶点的前驱顶点
    private double cost;//顶点的权值,与边的权值要区别开来
    private Map<String, String> diminfos;//标识顶点的一些必须信息

    public Vertex(T vertexLabel) {

        label = vertexLabel;
        edgeList = new LinkedList<Edge>();//是Vertex的属性,说明每个顶点都有一个edgeList用来存储所有与该顶点关系的边
        visited = false;
        previousVertex = null;
        cost = 0;
    }

    public Vertex(T vertexLabel, int index) {

        label = vertexLabel;
        edgeList = new LinkedList<Edge>();//是Vertex的属性,说明每个顶点都有一个edgeList用来存储所有与该顶点关系的边
        visited = false;
        previousVertex = null;
        cost = 0;
        id = new AtomicInteger(index);
    }

    public Vertex(T vertexLabel, Map<String, String> diminfo) {
        label = vertexLabel;
        edgeList = new LinkedList<Edge>();//是Vertex的属性,说明每个顶点都有一个edgeList用来存储所有与该顶点关系的边
        visited = false;
        previousVertex = null;
        cost = 0;
        diminfos = diminfo;
    }

    

    /**
     * Task: 遍历该顶点邻接点的迭代器--为 getNeighborInterator()方法 提供迭代器
     * 由于顶点的邻接点以边的形式存储在java.util.List中,因此借助List的迭代器来实现
     */
    private class NeighborIterator implements Iterator<VertexInterface<T>> {

        Iterator<Edge> edgesIterator;

        private NeighborIterator() {
            edgesIterator = edgeList.iterator();//获得 LinkedList 的迭代器
        }

        @Override
        public boolean hasNext() {
            return edgesIterator.hasNext();
        }

        @Override
        public VertexInterface<T> next() {
            VertexInterface<T> nextNeighbor = null;
            if (edgesIterator.hasNext()) {
                Edge edgeToNextNeighbor = edgesIterator.next();//LinkedList中存储的是Edge
                nextNeighbor = edgeToNextNeighbor.getEndVertex();//从Edge对象中取出顶点
            } else
                throw new NoSuchElementException();
            return nextNeighbor;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Task: 生成一个遍历该顶点所有邻接边的权值的迭代器
     * 权值是Edge类的属性,因此先获得一个遍历Edge对象的迭代器,取得Edge对象,再获得权值
     *
     * @权值的类型
     *
     */
    private class WeightIterator implements Iterator {//这里不知道为什么,用泛型报编译错误???

        private Iterator<Edge> edgesIterator;

        private WeightIterator() {
            edgesIterator = edgeList.iterator();
        }

        @Override
        public boolean hasNext() {
            return edgesIterator.hasNext();
        }

        @Override
        public Object next() {
            Double result;
            if (edgesIterator.hasNext()) {
                Edge edge = edgesIterator.next();
                result = edge.getWeight();
            } else throw new NoSuchElementException();
            return (Object) result;//从迭代器中取得结果时,需要强制转换成Double
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    @Override
    public T getLabel() {
        return label;
    }

    @Override
    public Integer getID() {
        return id.get();
    }

    @Override
    public void visit() {
        this.visited = true;
    }

    @Override
    public void unVisit() {
        this.visited = false;
    }

    @Override
    public boolean isVisited() {
        return visited;
    }

    @Override
    public boolean connect(VertexInterface<T> endVertex, double edgeWeight) {
        // 将"边"(边的实质是顶点)插入顶点的邻接表
        boolean result = false;
        if (!this.equals(endVertex)) {//顶点互不相同
            Iterator<VertexInterface<T>> neighbors = this.getNeighborInterator();
            boolean duplicateEdge = false;
            while (!duplicateEdge && neighbors.hasNext()) {//保证不添加重复的边
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor)) {
                    duplicateEdge = true;
                    break;
                }
            }//end while
            if (!duplicateEdge) {
                edgeList.add(new Edge(endVertex, edgeWeight));//添加一条新边
                result = true;
            }//end if
        }//end if
        return result;
    }


    @Override
    public boolean connect(VertexInterface<T> endVertex, Map<String, String> colInfo) {
        // 将"边"(边的实质是顶点)插入顶点的邻接表
        boolean result = false;
        if (!this.equals(endVertex)) {//顶点互不相同
            Iterator<VertexInterface<T>> neighbors = this.getNeighborInterator();
            boolean duplicateEdge = false;
            while (!duplicateEdge && neighbors.hasNext()) {//保证不添加重复的边
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor)) {
                    duplicateEdge = true;
                    break;
                }
            }//end while
            if (!duplicateEdge) {
                edgeList.add(new Edge(endVertex, colInfo));//添加一条带关联信息的新边
                result = true;
            }//end if
        }//end if
        return result;
    }

    @Override
    public boolean connect(VertexInterface<T> endVertex) {
        return connect(endVertex, 0);
    }

    @Override
    public Iterator<VertexInterface<T>> getNeighborInterator() {
        return new NeighborIterator();
    }

    @Override
    public Iterator getWeightIterator() {
        return new WeightIterator();
    }

    @Override
    public boolean hasNeighbor() {
        return !(edgeList.isEmpty());//邻接点实质是存储是List中
    }

    @Override
    public VertexInterface<T> getUnvisitedNeighbor() {//最坏情况下复杂度为O(E)
        VertexInterface<T> result = null;
        Iterator<VertexInterface<T>> neighbors = getNeighborInterator();
        while (neighbors.hasNext() && result == null) {//获得该顶点的第一个未被访问的邻接点
            VertexInterface<T> nextNeighbor = neighbors.next();
            if (!nextNeighbor.isVisited())
                result = nextNeighbor;
        }
        return result;
    }

    @Override
    public void setPredecessor(VertexInterface<T> predecessor) {
        this.previousVertex = predecessor;
    }

    @Override
    public VertexInterface<T> getPredecessor() {
        return this.previousVertex;
    }

    @Override
    public boolean hasPredecessor() {
        return this.previousVertex != null;
    }

    @Override
    public void setCost(double newCost) {
        cost = newCost;
    }

    @Override
    public double getCost() {
        return cost;
    }

    //判断两个顶点是否相同
    public boolean equals(Object other) {
        boolean result;
        if ((other == null) || (getClass() != other.getClass()))
            result = false;
        else {
            Vertex<T> otherVertex = (Vertex<T>) other;
            result = label.equals(otherVertex.label);//节点是否相同最终还是由标识 节点类型的类的equals() 决定
        }
        return result;
    }

    public Map<String, String> getDiminfo() {
        return diminfos;
    }

    public void setDiminfo(Map<String, String> diminfos) {
        this.diminfos = diminfos;
    }

	@Override
	public List<Edge> getEdgeList() {
		// TODO Auto-generated method stub
		return edgeList;
	}

    @Override
    public String toString() {
        return "Vertex{" +
                "label=" + label +
                ", id=" + id +
                ", edgeList=" + edgeList +
                ", visited=" + visited +
                ", previousVertex=" + previousVertex +
                ", cost=" + cost +
                ", diminfos=" + diminfos +
                '}';
    }
}
