package cn.longzzai.dataobject;

import cn.longzzai.repository.ProductCategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;

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
}