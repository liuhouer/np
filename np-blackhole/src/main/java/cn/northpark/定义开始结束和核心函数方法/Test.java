package cn.northpark.定义开始结束和核心函数方法;

/**
 * @author bruce
 * @date 2022年06月21日 16:51:09
 */
public class Test {
    public static void main(String[] args) {

        //1.执行1
        ProcessFunction<String> processFunction1 = new ProcessFunction<String>() {
            @Override
            public String call() {
                return "hello jeey";
            }
        };

        String process = processFunction1.process();
        System.err.println("执行结果1--->"+process);

        //1.执行1
        ProcessFunction<String> processFunction2 = new ProcessFunction<String>() {
            @Override
            public void open() {
                System.err.println("我自己执行open===");
            }

            @Override
            public void close() {
                System.err.println("我自己执行close===");
            }

            @Override
            public String call() {
                return "hello moto";
            }
        };

        String process2 = processFunction2.process();
        System.err.println("执行结果2--->"+process2);
    }
}
