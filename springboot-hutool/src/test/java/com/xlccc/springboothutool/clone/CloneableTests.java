package com.xlccc.springboothutool.clone;

import cn.hutool.core.util.ObjectUtil;
import com.xlccc.clone.Address;
import com.xlccc.clone.Person;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author Linker
 * @Date 2020/4/29 3:09 下午
 * @Version 1.0
 * TODO:Clone
 */
@SpringBootTest
public class CloneableTests {
    /**
     * 浅拷贝
     * 浅拷贝存在的问题
     *
     * @throws CloneNotSupportedException
     */
    @Test
    public void shallowCopyTest() throws CloneNotSupportedException {
        Address address = new Address("中国大陆");
        Person person = new Person("Linker", 99, address);

        //浅拷贝
        Person newPerson = (Person) person.clone();
        //person与newPerson不是同一对象
        System.out.println(person == newPerson);
        //address为引用类型，指向原来对象
        System.out.println(person.getAddress() == newPerson.getAddress());

        //浅拷贝存在问题
        address.setAddress("person's address");
        person.setAddress(address);

        address.setAddress("newPerson's address");
        newPerson.setAddress(address);

        System.out.println(person.getAddress());
        System.out.println(newPerson.getAddress());
    }

    /**
     * 深拷贝
     * commons-lang3
     */
    @Test
    public void deepCopyTest1() {
        Address address = new Address("中国大陆");
        Person person = new Person("Linker", 99, address);

        //序列化
        byte[] serialize = SerializationUtils.serialize(person);
        //反序列化
        Person newPerson = SerializationUtils.deserialize(serialize);

        System.out.println(person == newPerson);
        System.out.println(person.getAddress() == newPerson.getAddress());
    }

    /**
     * 深克隆
     * hu-tool
     */
    @Test
    public void deepCopyTest2() {
        Address address = new Address("中国大陆");
        Person person = new Person("Linker", 99, address);

        Person newPerson = ObjectUtil.cloneByStream(person);

        System.out.println(person == newPerson);
        System.out.println(person.getAddress() == newPerson.getAddress());
    }
}
