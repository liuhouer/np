package cn.northpark.pattern.观察者_通知回调模式;

/**
 * @author zhangyang
 * @date 2021年05月06日 20:00:43
 */
//怪物
public class Monster implements Observer{
    @Override
    public void update() {
        if(inRange()){
            System.out.println("怪物 对主角攻击！");
        }
    }

    private boolean inRange(){
        //判断主角是否在自己的影响范围内，这里忽略细节，直接返回true
        return true;
    }
}
