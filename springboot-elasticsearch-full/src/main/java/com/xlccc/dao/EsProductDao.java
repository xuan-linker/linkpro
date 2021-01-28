package com.xlccc.dao;

import com.xlccc.nosql.elasticsearch.document.EsProduct;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Linker
 * @date 2021/1/28 16:23
 * @description：
 */
public interface EsProductDao {
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
