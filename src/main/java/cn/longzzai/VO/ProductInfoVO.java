package cn.longzzai.VO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品详情
 *
 * @author longcho
 * 2017-08-26-10:48
 */
public class ProductInfoVO implements Serializable{

    private static final long serialVersionUID = 543982891677553883L;
    //商品id
    @JsonProperty("id")
    private String productId;
    //商品名称
    @JsonProperty("name")
    private String productName;
    //商品价格
    @JsonProperty("price")
    private BigDecimal productPrice;
    //商品描述
    @JsonProperty("description")
    private String productDescription;
    //商品小图
    @JsonProperty("icon")
    private String icon;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
