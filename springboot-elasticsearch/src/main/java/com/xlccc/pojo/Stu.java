package com.xlccc.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * @author Linker
 * @date 2020/8/14 11:46
 * @description：
 */
@Document(indexName = "stu")
//@Document(indexName = "stu",type="_doc")
public class Stu implements Serializable {
    @Id
    private String stuId;

    @Field(store = true)
    private String name;

    @Field(store = true)
    private Integer age;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Stu{" +
                "stuId='" + stuId + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
