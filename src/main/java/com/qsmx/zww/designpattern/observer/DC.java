package com.qsmx.zww.designpattern.observer;

/**
 * Created by zww on 2019-04-19.貂蝉
 */
public class DC {
    private String name = "貂蝉";
    private String message = "貂蝉";
    private Observer observer;

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
        observer.obser(message);
    }

    public Observer getObserver() {
        return observer;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }
}
