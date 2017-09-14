package cn.longzzai.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

/**
 * ResultVO第二层——商品对象
 *
 * @author longcho
 * 2017-08-26-10:42
 */
public class ProductVO implements Serializable{

    private static final long serialVersionUID = -2104023587664975549L;
    //商品类目名称
    @JsonProperty("name")
    private String productCategoryName;
    //商品类目编号(Type)
    @JsonProperty("type")
    private int productCategoryType;
    //包含的全部商品
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public int getProductCategoryType() {
        return productCategoryType;
    }

    public void setProductCategoryType(int productCategoryType) {
        this.productCategoryType = productCategoryType;
    }

    public List<ProductInfoVO> getProductInfoVOList() {
        return productInfoVOList;
    }

    public void setProductInfoVOList(List<ProductInfoVO> productInfoVOList) {
        this.productInfoVOList = productInfoVOList;
    }
}
