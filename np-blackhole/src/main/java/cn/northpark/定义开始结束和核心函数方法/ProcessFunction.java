package cn.northpark.定义开始结束和核心函数方法;

/**
 * @author bruce
 * @date 2022年06月21日 16:46:15
 */
public abstract class ProcessFunction<T> implements ProcessInterface{
    @Override
    public void open() {
        System.err.println("统一执行open------");
    }

    @Override
    public final T process() {
        open();
        T result = call();
        System.err.println("统一执行结果--->"+result);
        close();
        return result;
    }

    @Override
    public void close() {
        System.err.println("统一执行close------");
    }

    public abstract T call();
}
