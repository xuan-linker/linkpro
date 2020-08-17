package com.xlccc.service.impl;

import com.xlccc.dao.StuRepository;
import com.xlccc.pojo.Stu;
import com.xlccc.service.StuService;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Linker
 * @date 2020/8/17 10:42
 * @description：
 */
@Service
public class StuServiceImpl implements StuService {
    @Autowired
    private StuRepository stuRepository;

    @Override
    public long count() {
        return stuRepository.count();
    }

    @Override
    public Stu save(Stu stu) {
        return stuRepository.save(stu);
    }

    @Override
    public void delete(Stu stu) {
        stuRepository.delete(stu);
    }

    @Override
    public Iterable<Stu> getAll() {
        return stuRepository.findAll();
    }

    @Override
    public List<Stu> getByName(String name) {
        List<Stu> list = new ArrayList<>();
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("name", name);
        Iterable<Stu> iterable = stuRepository.search(matchQueryBuilder);
        iterable.forEach(e -> list.add(e));
        return list;

    }

    @Override
    public Page<Stu> pageQuery(Integer pageNo, Integer pageSize, String kw) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchPhraseQuery("name", kw))
                .withPageable(PageRequest.of(pageNo, pageSize)).build();
        return stuRepository.search(searchQuery);
    }
}
