package cn.longzzai.controller;

import cn.longzzai.VO.ProductInfoVO;
import cn.longzzai.VO.ProductVO;
import cn.longzzai.VO.ResultVO;
import cn.longzzai.dataobject.ProductCategory;
import cn.longzzai.dataobject.ProductInfo;
import cn.longzzai.service.ProductCategoryService;
import cn.longzzai.service.ProductInfoService;
import cn.longzzai.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品管理控制层
 *
 * @author longcho
 * 2017-08-26-15:06
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyProductController {
    @Autowired
    private ProductCategoryService categoryService;
    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping("/list")
    public ResultVO list(){
        //查询所有上架商品
        List<ProductInfo> upProductList = productInfoService.findUpAll();
        //根据上架商品查询所有商品目录的标签collection
        List<Integer> collect = upProductList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        //上架商品的目录collection
        List<ProductCategory> categoryTypeList = categoryService.findByCategoryTypeIn(collect);
        List<ProductVO> productVOList =new ArrayList<>();
        for (ProductCategory productCategory : categoryTypeList) {
            ProductVO productVO =new ProductVO();
            productVO.setProductCategoryName(productCategory.getCategoryName());
            productVO.setProductCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList =new ArrayList<>();
            for (ProductInfo productInfo : upProductList) {
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    productInfoVO.setProductId(productInfo.getProductId());
                    productInfoVO.setProductName(productInfo.getProductName());
                    productInfoVO.setProductPrice(productInfo.getProductPrice());
                    productInfoVO.setProductDescription(productInfo.getProductDescription());
                    productInfoVO.setIcon(productInfo.getProductIcon());
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
        productVOList.add(productVO);
        }

        return ResultVOUtil.success(productVOList);
    }
}
