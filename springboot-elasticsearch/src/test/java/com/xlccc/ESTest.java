package com.xlccc;

import com.xlccc.pojo.Stu;
import com.xlccc.service.StuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Linker
 * @date 2020/8/14 11:44
 * @description：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ESTest {

    @Autowired
    private StuService stuService;

    @Test
    public void contextLoads() {
        System.out.println(stuService.count());
    }

    @Test
    public void testInsert() {
        Stu stu = new Stu();
        stu.setAge(11);
        stu.setName("Lily");
        stu.setStuId("10001");
        stuService.save(stu);

        stu = new Stu();
        stu.setAge(12);
        stu.setName("Linker");
        stu.setStuId("10002");
        stuService.save(stu);

        stu = new Stu();
        stu.setAge(13);
        stu.setName("Macro");
        stu.setStuId("10003");
        stuService.save(stu);

        stu = new Stu();
        stu.setAge(14);
        stu.setName("Kim");
        stu.setStuId("10004");
        stuService.save(stu);
    }

    @Test
    public void testGetAll() {
        Iterable<Stu> all = stuService.getAll();
        System.out.println(all.toString());

        all.forEach(e -> System.out.println(e.toString()));
    }

    @Test
    public void testDelete() {
        Stu stu = new Stu();
        stu.setStuId("10004");
        stuService.delete(stu);
    }

    @Test
    public void testGetByName() {
        List<Stu> s = stuService.getByName("Linker");
        System.out.println(s);
    }

    @Test
    public void testPage() {
        Page<Stu> page = stuService.pageQuery(0, 10, "Linker");
        System.out.println(page.getTotalPages());
        System.out.println(page.getNumber());
        System.out.println(page.getContent());
    }
}
