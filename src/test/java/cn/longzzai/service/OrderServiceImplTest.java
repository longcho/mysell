package cn.longzzai.service;

import cn.longzzai.dataobject.OrderDetail;
import cn.longzzai.dto.OrderDTO;
import cn.longzzai.enums.OrderStatusEnum;
import cn.longzzai.enums.PayStatusEnum;
import cn.longzzai.enums.ResultEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    @Autowired
    private OrderServiceImpl orderService;
    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("张三");
        orderDTO.setBuyerPhone("17683112898");
        orderDTO.setBuyerAddress("广安");
        orderDTO.setBuyerOpenid("abcd");
        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123458");
        o1.setProductQuantity(1);
        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123457");
        o2.setProductQuantity(2);
        orderDetailList.add(o1);
        orderDetailList.add(o2);
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO orderDTO1 = orderService.create(orderDTO);
        Assert.assertNotNull(orderDTO1);
    }

    @Test
    public void findOne() throws Exception {
        OrderDTO one = orderService.findOne("15041002451168673268");
        Assert.assertEquals("15041002451168673268" ,one.getOrderId());
    }

    @Test
    public void findList() throws Exception {
        PageRequest request = new PageRequest(0 , 6);
        Page<OrderDTO> orderDTOPage = orderService.findList("abcd" ,request);
        Assert.assertNotEquals(0 , orderDTOPage.getTotalElements());

    }

    @Test
    public void cancel() throws Exception {
        OrderDTO orderDTO = orderService.findOne("15041002451168673268");
        OrderDTO orderDTO1 = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode() , orderDTO1.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
        OrderDTO orderDTO = orderService.findOne("15041002451168673268");
        OrderDTO orderDTO1 = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode() , orderDTO1.getOrderStatus());
    }

    @Test
    public void paid() throws Exception {
        OrderDTO orderDTO = orderService.findOne("15041002451168673268");
        OrderDTO orderDTO1 = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode() , orderDTO1.getPayStatus());
    }

}