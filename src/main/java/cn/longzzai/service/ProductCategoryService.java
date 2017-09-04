package cn.longzzai.service;

import cn.longzzai.dataobject.ProductCategory;

import java.util.List;

/**
 * 类目服务层
 *
 * @author longcho
 * 2017-08-26-8:43
 */
public interface ProductCategoryService {
    public ProductCategory findOne(Integer productCategoryId);
    public List<ProductCategory> findAll();
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categorytypeList);
    public ProductCategory save(ProductCategory productCategory);
}
