package com.ad340.micromomentums;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class TrackedStock {

    @Id(assignable = true)
    public Long id;

    public TrackedStock(){};

    public TrackedStock(Long id){
        this.id = id;
    }
}

