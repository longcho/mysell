package cn.longzzai.dto;

import cn.longzzai.dataobject.OrderDetail;
import cn.longzzai.enums.OrderStatusEnum;
import cn.longzzai.enums.PayStatusEnum;
import cn.longzzai.utils.EnumUtil;
import cn.longzzai.utils.serializer.Date2LongSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单的数据传输对象
 *
 * @author longcho
 * 2017-08-30-17:36
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO implements Serializable{

    private static final long serialVersionUID = 1958316279662547877L;
    /** 订单id. */

    private String orderId;

    /** 买家名字. */
    private String buyerName;

    /** 买家手机号. */
    private String buyerPhone;

    /** 买家地址. */
    private String buyerAddress;

    /** 买家微信Openid. */
    private String buyerOpenid;

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单. */
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /** 支付状态, 默认为0未支付. */
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /**创建时间*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /**更新时间**/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;;

    /**订单详情. */
    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(this.orderStatus ,OrderStatusEnum.class);
    }

    @JsonIgnore
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(payStatus, PayStatusEnum.class);
    }
}
