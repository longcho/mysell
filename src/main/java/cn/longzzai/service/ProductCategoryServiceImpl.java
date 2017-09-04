package cn.longzzai.service;

import cn.longzzai.dataobject.ProductCategory;
import cn.longzzai.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类目服务层
 *
 * @author longcho
 * 2017-08-26-8:43
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository repository;
    @Override
    public ProductCategory findOne(Integer productCategoryId) {
        return repository.findOne(productCategoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categorytypeList) {
       return  repository.findByCategoryTypeIn(categorytypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
       return  repository.save(productCategory);
    }
}
