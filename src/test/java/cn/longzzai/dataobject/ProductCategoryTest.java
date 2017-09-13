package cn.longzzai.dataobject;

import cn.longzzai.repository.ProductCategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryTest {
    @Autowired
    private ProductCategoryRepository repository;
    @Test
    public void findone(){
        ProductCategory productCategory = repository.findOne(1);
        System.out.println( productCategory.toString());
    }
    @Test
    public void saveOne(){
        //ProductCategory productCategory = new ProductCategory("女生最爱",2);
        //repository.save(productCategory);
        //Assert.assertNotNull(productCategory);
    }
}