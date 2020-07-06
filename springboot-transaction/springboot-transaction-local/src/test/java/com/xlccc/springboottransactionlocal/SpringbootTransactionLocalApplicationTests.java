package com.xlccc.springboottransactionlocal;

import com.xlccc.service.TransactionPropagationExample;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootTransactionLocalApplicationTests {

    @Autowired
    private TransactionPropagationExample transactionPropagationExample;

//    @BeforeEach
//    public void beforeForClean(){
//        transactionPropagationExample.truncated();
//    }

    @Test
    public void test_no_transaction_required_required(){
        transactionPropagationExample.no_transaction_required_required();
    }
    @Test
    void contextLoads() {
    }

}
