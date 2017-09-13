package cn.longzzai.utils;

import cn.longzzai.dataobject.ProductCategory;
import cn.longzzai.service.ProductCategoryService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取商品目录的名称
 *
 * @author longcho
 * 2017-09-09-20:42
 */
@Component
public class CagegoryNameUtil {
    @Autowired
    private  ProductCategoryService categoryService;
    private static CagegoryNameUtil cagegoryNameUtil;
    @PostConstruct
    public void init(){
        cagegoryNameUtil=this;
        cagegoryNameUtil.categoryService = this.categoryService ;
    }

    public static String findCategoryName(Integer categoryId){
        List list = new ArrayList<Integer>();
        list.add(categoryId);
        List<ProductCategory> byCategoryIdIn = cagegoryNameUtil.categoryService.findByCategoryIdIn(list);
        if (byCategoryIdIn.size() > 0){
            return byCategoryIdIn.get(0).getCategoryName();
        }
        return "";
    }
}
