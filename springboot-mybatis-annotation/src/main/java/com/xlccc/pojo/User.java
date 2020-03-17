package com.xlccc.pojo;

/**
 * @ClassName User
 * @Description TODO
 * @Author Linker
 * @Date 2020/1/15 3:56 PM
 **/
public class User {

    private int id ;
    private String name ;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
