package ru.romasini.spring.beginner.project;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> productList = new ArrayList<>();

    private void initialize(){
        productList.addAll(Arrays.asList(
                new Product()
        ));
    }

}
