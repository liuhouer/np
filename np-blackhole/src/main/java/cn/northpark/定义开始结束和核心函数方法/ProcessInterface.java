package cn.northpark.定义开始结束和核心函数方法;

/**
 * @author bruce
 * @date 2022年06月21日 16:46:15
 */
public interface ProcessInterface<T> {
    void open();
    T process();
    void close();
}
