package cn.longzzai.service;

import cn.longzzai.dataobject.ProductInfo;
import cn.longzzai.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品信息管理
 *
 * @author longcho
 * 2017-08-26-10:01
 */
public interface ProductInfoService {
    ProductInfo findOne(String productId);

    /**
     * 查询所有在架商品列表
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increase(List<CartDTO> cartDTOList);
    //减库存
    void decrease(List<CartDTO> cartDTOList);

    //上架
    void onSale(String productId);
    //下架
    void offSale(String productId);
}
