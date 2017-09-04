package cn.longzzai.dto;

/**
 * 提供的订单的详情
 *
 * @author longcho
 * 2017-08-30-18:47
 */
public class CartDTO {
    //商品id
    private String productId;
    //商品数量
    private int productQuantity;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public CartDTO(String productId, int productQuantity) {

        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
