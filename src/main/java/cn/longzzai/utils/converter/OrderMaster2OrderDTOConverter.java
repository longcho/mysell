package cn.longzzai.utils.converter;

import cn.longzzai.dataobject.OrderMaster;
import cn.longzzai.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ordermaster与orderdto转换
 *
 * @author longcho
 * 2017-08-30-22:12
 */
public class OrderMaster2OrderDTOConverter {
    public static OrderDTO toOrderDTO(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster , orderDTO);
        return orderDTO;
    }

    public static OrderMaster toOrderMaster(OrderDTO orderDTO){
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO , orderMaster);
        return orderMaster;
    }

    public static List<OrderDTO> toOrderDTO(List<OrderMaster> orderMasterList){
        return orderMasterList.stream().map(e -> toOrderDTO(e)).collect(Collectors.toList());
    }

    public static List<OrderMaster> toOrderMaster(List<OrderDTO> orderDTOList){
        return orderDTOList.stream().map(e -> toOrderMaster(e)).collect(Collectors.toList());
    }
}
