package com.xlccc.dao;

import com.xlccc.pojo.Stu;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Linker
 * @date 2020/8/17 10:39
 * @description：
 */
@Repository
public interface StuRepository extends ElasticsearchRepository<Stu, String> {
}
