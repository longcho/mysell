package cn.longzzai.repository;

import cn.longzzai.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品详情dao层
 *
 * @author longcho
 * 2017-08-26-21:02
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail , String> {
    List<OrderDetail> findByOrderId(String orderId);
}
