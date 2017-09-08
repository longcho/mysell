package cn.longzzai.enums;

/**
 * 返回的最外层的enum
 *
 * @author longcho
 * 2017-08-26-16:55
 */
public enum ResultEnum {
    SUCCESS(0,"ok"),
    ERROR(1,"error"),
    PRODUCT_NOT_EXIST(2,"商品不存在"),
    PRODUCT_STOCK_ERROR(3,"库存不正确"),
    ORDER_STATUS_ERROR(4 , "订单状态错误"),
    ORDERDETAIL_NOT_EXIST(5 , "订单详情不存在"),
    ORDER_UPDATE_FAIL(6,"订单更新失败"),
    ORDER_PAY_STATUS_ERROR(7, "订单支付状态不正确"),
    PARAM_ERROR(8,"参数不正确"),
    CART_EMPTY(9 ,"购物车为空"),
    BUYER_OWNER_ERROR(10 , "该订单不属于当前用户"),
    ORDER_NOT_EXIST(11 , "订单不存在"),
    WECHAT_TOKEN_ERROR(12 ,"获取微信授权失败"),
    ORDER_CANCEL_SUCCESS(13,"订单取消成功"),
    ;
    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResultEnum(int code, String msg) {

        this.code = code;
        this.msg = msg;
    }
}
