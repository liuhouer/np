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

    /**
     * 注意：这个抽象函数定义域为protected,
     * 可以防止外部包调用该函数，
     * 有执行顺序，又需要不同实现的函数，
     * 不能被外部类直接调用，不然不会执行到前后的顺序(open\close)函数
     * @return
     */
    protected abstract T call();
}
