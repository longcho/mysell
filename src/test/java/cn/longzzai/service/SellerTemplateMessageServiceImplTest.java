package cn.longzzai.service;

import cn.longzzai.dto.OrderDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class SellerTemplateMessageServiceImplTest {
    @Autowired
    private SellerTemplateMessageServiceImpl templateMessageService;
    @Autowired
    private OrderService orderService;
    @Test
    public void pushOrderFinishMessage() throws Exception {
        OrderDTO one = orderService.findOne("150423944289524423527");
        templateMessageService.pushOrderFinishMessage(one);
    }

}