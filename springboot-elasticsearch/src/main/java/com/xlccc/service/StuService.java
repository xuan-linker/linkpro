package com.xlccc.service;

import com.xlccc.pojo.Stu;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Linker
 * @date 2020/8/17 10:40
 * @description：
 */
public interface StuService {
    long count();

    Stu save(Stu stu);

    void delete(Stu stu);

    Iterable<Stu> getAll();

    List<Stu> getByName(String name);

    Page<Stu> pageQuery(Integer pageNo, Integer pageSize, String kw);
}
