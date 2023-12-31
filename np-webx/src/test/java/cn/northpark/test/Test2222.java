package cn.northpark.test;

public class Test2222 {
    void m(){
        if(this instanceof Test2222){
            System.out.println("I M A");
        }
        if(this instanceof B){
            System.out.println("I M B");
        }
    }
}

class B extends Test2222{
    void m(){
        super.m();
    }

    public static void main(String[] args) {
        Test2222 a = new B();
        a.m();
    }
}
