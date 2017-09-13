package cn.longzzai.service;

import cn.longzzai.dataobject.SellerInfo;
import cn.longzzai.enums.ResultEnum;
import cn.longzzai.exception.SellException;
import cn.longzzai.repository.SellerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author longcho
 * 2017-09-10-21:41
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        if (openid == null){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid(openid);
        if (sellerInfo == null){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        return sellerInfo;
    }
}
