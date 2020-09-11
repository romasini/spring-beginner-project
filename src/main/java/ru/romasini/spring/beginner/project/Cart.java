package ru.romasini.spring.beginner.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Cart {

    private ProductService productService;
    private List<Product> productList = new ArrayList<>();

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void putProduct(long id){
        productList.add(productService.getProduct(id));
    }

    public void deleteProduct(long id){
        productList.remove(productService.getProduct(id));
    }

    @Override
    public String toString() {

        if(productList.isEmpty()){
            return "Корзина пуста";
        }

        StringBuilder str = new StringBuilder();
        for (Product p:productList) {
            str.append(p).append("\n");
        }
        return str.toString();

    }
}
