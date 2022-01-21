package cn.northpark.观察者_通知回调模式;

/**
 * @author zhangyang
 * @date 2021年05月06日 20:03:03
 */
public class Hero extends Subject{
    void move(){
        System.out.println("主角向前移动");
        notifyObservers();
    }


    public static void main(String[] args) {
        //初始化对象
        Hero hero = new Hero();
        Monster monster = new Monster();
        Trap trap = new Trap();
        Treasure treasure = new Treasure();
        //注册观察者
        hero.attachObserver(monster);
        hero.attachObserver(trap);
        hero.attachObserver(treasure);
        //移动事件
        hero.move();
    }

}
