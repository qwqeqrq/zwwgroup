package com.qsmx.zww.designpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zww on 2019-04-19.//西施up主 被观察对象
 */
public class XS {
    private String name = "西施";
    private List<Observer> observers = new ArrayList<>();
    private String message = "西施";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        for (Observer observer : observers) {
            observer.obser(message);
        }
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public void setObservers(List<Observer> observers) {
        this.observers = observers;
    }
}
