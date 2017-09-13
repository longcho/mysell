package cn.longzzai.service;

import cn.longzzai.dataobject.SellerInfo;

/**
 * @author longcho
 * 2017-09-10-21:40
 */
public interface SellerService {
    SellerInfo findSellerInfoByOpenid(String openid);
}
