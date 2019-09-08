package com.smartshare.event;

public abstract class CreationEvent<T> {
    private final T object;

    CreationEvent(T object){
        this.object = object;
    }

    public T getObject(){
        return object;
    }
}
