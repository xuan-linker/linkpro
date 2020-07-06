package com.xlccc.service.impl;

import com.xlccc.mapper.User2Mapper;
import com.xlccc.pojo.User2;
import com.xlccc.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class User2ServiceImpl implements User2Service {
    @Autowired
    private User2Mapper user2Mapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequired(User2 user) {
        user2Mapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRequiredException(User2 user) {
        user2Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    public void truncate() {
        user2Mapper.truncated();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void addSupports(User2 user) {
        user2Mapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void addSupportsException(User2 user) {
        user2Mapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNew(User2 user) {
        user2Mapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addRequiresNewException(User2 user) {
        user2Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void addNotSupported(User2 user) {
        user2Mapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)

    public void addNotSupportedException(User2 user) {
        user2Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    public void add(User2 user) {
        user2Mapper.insert(user);
    }

    @Override
    public void addException(User2 user) {
        user2Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addMandatory(User2 user) {
        user2Mapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void addMandatoryException(User2 user) {
        user2Mapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public void addNever(User2 user) {
        user2Mapper.insert(user);

    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public void addNeverException(User2 user) {
        user2Mapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void addNested(User2 user) {
        user2Mapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public void addNestedException(User2 user) {
        user2Mapper.insert(user);
        throw new RuntimeException();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public User2 getRequired(Integer id) {
        return user2Mapper.selectByPrimaryKey(id);
    }

    @Override
    public User2 get(Integer id) {
        return user2Mapper.selectByPrimaryKey(id);
    }
}
