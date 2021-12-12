package spd.trello.service;

import spd.trello.domain.Board;

public abstract class ServiceLayer <T> {
    
    public abstract T create();
    public abstract void update(int index, T object);

    public void print(T object){
        System.out.println(object);
    }
}
