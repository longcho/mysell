package cn.longzzai.repository;

import cn.longzzai.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品表dao
 *
 * @author longcho
 * 2017-08-26-9:30
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo ,String>{
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
