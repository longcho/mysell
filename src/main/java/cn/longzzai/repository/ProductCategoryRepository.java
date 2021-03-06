package cn.longzzai.repository;

import cn.longzzai.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory , Integer> {
    List<ProductCategory> findByCategoryIdIn(List<Integer> categorytypeList);
}
