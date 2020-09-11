package ru.romasini.spring.beginner.project;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> productList = new ArrayList<>();

    public ProductRepository() {
    }

    @PostConstruct
    public void initialize(){
        productList.addAll(Arrays.asList(
                new Product(1l, "Product 1", 100),
                new Product(2l, "Product 2", 200),
                new Product(3l, "Product 3", 300),
                new Product(4l, "Product 4", 400),
                new Product(5l, "Product 5", 500)
        ));
    }

    public Product getProduct(long id){

        for (Product p:productList) {
            if (p.getId() == id){
                return p;
            }
        }

        throw new RuntimeException("Продукт не найден");
    }

    public List<Product> getProductList() {
        return Collections.unmodifiableList(productList);
    }

    @Override
    public String toString() {

        if(productList.isEmpty()){
            return "Список товаров пуст";
        }

        StringBuilder str = new StringBuilder();
        for (Product p:productList) {
            str.append(p).append("\n");
        }
        return str.toString();
    }
}
