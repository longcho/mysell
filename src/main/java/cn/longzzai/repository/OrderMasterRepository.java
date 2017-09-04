package cn.longzzai.repository;

import cn.longzzai.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 商品主表dao层
 *
 * @author longcho
 * 2017-08-26-21:04
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster , String> {
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
