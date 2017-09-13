package cn.longzzai.repository;

import cn.longzzai.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author longcho
 * 2017-09-10-21:36
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo ,String>{
    SellerInfo findByOpenid(String openid);
}
