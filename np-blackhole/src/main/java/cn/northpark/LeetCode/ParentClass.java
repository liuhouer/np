package cn.northpark.LeetCode;

/**
 * @author liuhouer
 * @date 2021年05月07日 09:11:10
 */

class ChildClass extends ParentClass {
    public static String C_S_S = "子类-静态变量";
    private String c_s = "子类-变量";

    //2
    static {
        System.out.println(C_S_S);
        System.out.println("子类-静态初始化快");
    }

    //5
    {
        System.out.println(c_s);
        System.out.println("子类-初始化块");
    }

    //6
    public ChildClass() {
        System.out.println("子类-构造器");
    }
}

public class ParentClass {
    public static String F_S_S = "父类-静态变量";
    private String f_s = "父类-变量";

    //1
    static {
        System.out.println(F_S_S);
        System.out.println("父类-静态初始化快");
    }

    //3
    {
        System.out.println(f_s);
        System.out.println("父类-初始化块");
    }
    //4
    public ParentClass() {
        System.out.println("父类-构造器");
    }

    public static void main(String[] args) {
        new ChildClass();
        //父类-静态变量
        //父类-静态初始化快
        //子类-静态变量
        //子类-静态初始化快
        //父类-变量
        //父类-初始化块
        //父类-构造器
        //子类-变量
        //子类-初始化块
        //子类-构造器
    }


}

