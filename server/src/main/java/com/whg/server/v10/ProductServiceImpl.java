package com.whg.server.v10;

import com.whg.api.Product;
import com.whg.api.ProductService;

import java.util.HashMap;
import java.util.Map;

public class ProductServiceImpl implements ProductService {

    private static final Map<String, Product> productMap = new HashMap<String, Product>(){
        {
            put("1", new Product(1, "1", "苹果"));
            put("2", new Product(2, "2", "华为"));
            put("3", new Product(3, "3", "小米"));
        }
    };

    @Override
    public Product findProduct(String sn) {
        // TODO: 查询缓存/数据库
        return productMap.get(sn);
    }
}
