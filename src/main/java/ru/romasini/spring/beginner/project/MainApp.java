package ru.romasini.spring.beginner.project;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MainApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductService productService = context.getBean("productService", ProductService.class);

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Список команд:");
            System.out.println("prod - список товаров");
            System.out.println("cart - добавить корзину");
            System.out.println("put - добавить товар в корзину");
            System.out.println("del - удалить товар из корзины");
            System.out.println("list - просмотреть список товаров в корзине");
            System.out.println("exit - выход из программы");

            String command = null;
            Cart cart = null;
            long temp = 0l;
            while (true){
                command = bufferedReader.readLine();

                if(command.equals("exit")){
                    break;
                }

                if(command.equals("prod")){
                    List<Product> productList = productService.getProductList();
                    if(productList.isEmpty()){
                        System.out.println("Список товаров пуст");
                    }

                    StringBuilder str = new StringBuilder();
                    for (Product p:productList) {
                        str.append(p).append("\n");
                    }
                    System.out.println(str.toString());
                }

                if(command.equals("cart")){
                    cart = context.getBean("cart", Cart.class);
                    System.out.println("Корзина создана");
                }

                if(cart != null) {
                    if (command.equals("list")) {
                        System.out.println(cart);
                    }

                    if(command.equals("put")){
                        System.out.println("Введите индекс товара");
                        temp = Long.parseLong(bufferedReader.readLine());
                        try {
                            cart.putProduct(temp);
                        }catch (RuntimeException e){
                            System.out.println(e.getMessage());
                        }

                    }

                    if(command.equals("del")){
                        System.out.println("Введите индекс товара");
                        temp = Long.parseLong(bufferedReader.readLine());
                        try {
                            cart.deleteProduct(temp);
                        }catch (RuntimeException e){
                            System.out.println(e.getMessage());
                        }

                    }
                }

            }

            System.out.println("Программа завершена");

        }catch (IOException e){
            e.printStackTrace();
        }

        context.close();
    }

}
