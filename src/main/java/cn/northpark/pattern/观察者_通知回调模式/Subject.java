package cn.northpark.pattern.观察者_通知回调模式;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyang
 * @date 2021年05月06日 20:00:02
 */
//被观察者
public abstract class Subject {


    private List<Observer> observerList = new ArrayList<Observer>();

    public void attachObserver(Observer observer) {
        observerList.add(observer);
    }

    public void detachObserver(Observer observer){
        observerList.remove(observer);
    }

    public void notifyObservers(){
        for (Observer observer: observerList){
            observer.update();
        }
    }
}
