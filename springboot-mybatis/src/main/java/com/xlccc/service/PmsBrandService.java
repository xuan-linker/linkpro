package com.xlccc.service;

import com.xlccc.mbg.model.PmsBrand;

import java.util.List;

/**
 * @author Linker
 * @date 2021/1/27 23:25
 * @description：
 */
public interface PmsBrandService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
