package cn.longzzai.service;

import cn.longzzai.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {
    @Autowired
    private ProductCategoryServiceImpl service;
    @Test
    public void findOne() throws Exception {
        ProductCategory productCategory = service.findOne(5);
        Assert.assertEquals(new Integer(5),productCategory.getCategoryId());
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> all = service.findAll();
        Assert.assertNotEquals(0,all.size());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<ProductCategory> byCategoryTypeIn = service.findByCategoryIdIn(Arrays.asList(1, 2, 3, 4, 5));
        Assert.assertNotEquals(0,byCategoryTypeIn.size());
    }

    @Test
    public void save() throws Exception {
        //ProductCategory p = service.save(new ProductCategory("热门", 10));
        //Assert.assertEquals(new Integer(10),p.getCategoryType());
    }

}