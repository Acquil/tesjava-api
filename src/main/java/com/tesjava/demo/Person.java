package com.tesjava.demo;
import org.bson.codecs.pojo.annotations.*;
import org.bson.types.ObjectId;

@BsonDiscriminator
public final class Person {
    private ObjectId id;
    private String name;
    private Integer salary;
    private String address = null;

    public Person() { }


    public ObjectId getId() {
        return id;
    }

    public void setId(final ObjectId id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }
}
