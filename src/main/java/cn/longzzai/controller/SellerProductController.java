package cn.longzzai.controller;

import cn.longzzai.dataobject.ProductCategory;
import cn.longzzai.dataobject.ProductInfo;
import cn.longzzai.dto.OrderDTO;
import cn.longzzai.exception.SellException;
import cn.longzzai.form.ProductForm;
import cn.longzzai.service.ProductCategoryService;
import cn.longzzai.service.ProductInfoService;
import cn.longzzai.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家端商品管理
 *
 * @author longcho
 * 2017-09-08-21:23
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductInfoService productService;
    @Autowired
    private ProductCategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") int page,
                             @RequestParam(value = "size", defaultValue = "10") int size, Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        map.put("page", productInfoPage.getTotalPages());
        return new ModelAndView("product/list", map);
    }


    /**
     * 商品上架
     *
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        try {
            productService.onSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("reurl", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", "修改成功");
        map.put("reurl", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 商品下架
     *
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("reurl", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", "修改成功");
        map.put("reurl", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }

    /**
     * 保存/更新
     *
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("reurl", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        ProductInfo productInfo = new ProductInfo();
        try {
            //如果productId为空, 说明是新增
            if (!StringUtils.isEmpty(form.getProductId())) {
                productInfo = productService.findOne(form.getProductId());
            } else {
                form.setProductId(KeyUtil.getEasyKey());
            }
            BeanUtils.copyProperties(form, productInfo);
            productService.save(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("reurl", "/sell/seller/product/index");
            return new ModelAndView("common/error", map);
        }

        map.put("reurl", "/sell/seller/product/list");
        return new ModelAndView("common/success", map);
    }


    /**
     * 商品编辑页面
     *
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId" ,required = false) String productId,
                              Map<String, Object> map) {
        try {
            if (!(productId == null || productId == "")) {
                ProductInfo productInfo = productService.findOne(productId);
                map.put("productInfo", productInfo);
            }
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("reurl", "/sell/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("product/index", map);
    }
}
