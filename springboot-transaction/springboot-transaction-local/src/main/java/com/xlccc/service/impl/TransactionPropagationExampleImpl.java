package com.xlccc.service.impl;

import com.xlccc.pojo.User1;
import com.xlccc.pojo.User2;
import com.xlccc.service.TransactionPropagationExample;
import com.xlccc.service.User1Service;
import com.xlccc.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class TransactionPropagationExampleImpl implements TransactionPropagationExample {

    @Autowired
    private User1Service user1Service;
    @Autowired
    private User2Service user2Service;


    @Override
    public void truncated() {
        user1Service.truncate();
        user2Service.truncate();
    }

    /**
     * 没有事务注解
     */
    @Override
    public void no_transaction_required_required() {
        User1 user1 = new User1();
        user1.setName("user1");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("user2");
        user2Service.addRequired(user2);
    }

    /**
     * 方法本身抛出异常
     */
    @Override
    public void no_transaction_exception_required_required() {
        User1 user1 = new User1();
        user1.setName("user1");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("user2");
        user2Service.addRequired(user2);

        throw new RuntimeException();
    }

    /**
     * 调用方法抛出异常
     */
    @Override
    public void no_transaction_required_required_exception() {
        User1 user1 = new User1();
        user1.setName("user1");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("user2");
        user2Service.addRequiredException(user2);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void transaction_exception_required_required() {
        User1 user1 = new User1();
        user1.setName("user1");
        user1Service.addRequired(user1);

        User2 user2 = new User2();
        user2.setName("user2");
        user2Service.addRequired(user2);

        throw new RuntimeException();
    }
}
