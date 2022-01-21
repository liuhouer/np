package cn.northpark.DAG;

import java.util.Map;

/**
 * Task: 这里用了一个单独的类来表示边,主要是考虑到带权值的边
 * 可以看出,Edge类封装了一个顶点和一个double类型变量
 * 若不需要考虑权值,可以不需要单独创建一个Edge类来表示边,只需要一个保存顶点的列表即可
 *
 * @author liuhouer
 * @param <T>
 */
public class Edge<T> implements java.io.Serializable {


    private VertexInterface<T> start;// 起点 -用于迪杰斯特拉算法往前寻址/也可以添加新的构造方法 表示的结构 更清晰

    private VertexInterface<T> vertex;// 终点

    private double weight;//权值

    public Map<String, String> colInfos;//标识标点关联标点的字段信息等等

    public Map<String, String> getColInfos() {
		return colInfos;
	}


    /**
     * Vertex 起点 /终点 /权值
     * @2020年10月14日
     * @author zhanygang
     * @param startVertex
     * @param endVertex
     * @param edgeWeight
     */
    protected Edge(VertexInterface<T> startVertex, VertexInterface<T> endVertex, double edgeWeight) {
        start = startVertex;
        vertex = endVertex;
        weight = edgeWeight;
    }

	//Vertex 类本身就代表顶点对象,因此在这里只需提供 endVertex，就可以表示一条边了
    protected Edge(VertexInterface<T> endVertex, double edgeWeight) {
        vertex = endVertex;
        weight = edgeWeight;
    }

    /**
     * 传入下一个顶点和顶点关联的信息
     *
     * @param endVertex
     * @param colInfo
     */
    protected Edge(VertexInterface<T> endVertex, Map<String, String> colInfo) {
        vertex = endVertex;
        colInfos = colInfo;
    }

    protected VertexInterface<T> getEndVertex() {
        return vertex;
    }

    protected double getWeight() {
        return weight;
    }

    //显示的设置用于迪杰斯特拉算法=======================
    public VertexInterface<T> getStart() {
        return start;
    }

    public void setStart(VertexInterface<T> start) {
        this.start = start;
    }
    //显示的设置用于迪杰斯特拉算法=======================

    @Override
    public String toString() {
        return "Edge{" +
                "vertex=" + vertex +
                ", weight=" + weight +
                ", colInfos=" + colInfos +
                '}';
    }
}
