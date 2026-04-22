package com.pluralsight;

import com.pluralsight.model.Product;
import ui.Console;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class Main {
    public static void main(String[] arg) {
        HashMap<Integer, Product> product = new HashMap<>();

        try {
            String fileName = "src/main/java/com/pluralsight/data/product.txt";
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String productInventory;

            while ((productInventory = bufferedReader.readLine()) != null) {
                try {
                    String[] productData = productInventory.split("\\|");
                    int productId = Integer.parseInt(productData[0]);
                    String productItem = productData[1];
                    double productPrice = Double.parseDouble(productData[2]);
                    Product p = new Product(productId, productItem, productPrice);
                    product.put(productId, p);
                } catch (Exception e) {
                    System.out.println("An error occured: " + e.getMessage());
                }
            }

        } catch (IOException e ){
            System.out.println("Something went wrong with trying read the file.");
        }




        String productSystemMenuMsg = """
            What do you want to do?
            1 - List all product
            2 - Lookup product by Id
            3 - Find all product within a price range
            4 - Add a new product
            5 - Quit application
            """;

        while (true){
            System.out.println(productSystemMenuMsg);
            int userInput = Console.askForInt("Enter your command:", 1, 5);
            switch (userInput){
                case 1:
                    System.out.println(product.get(2048).toString());
                    break;
            }


        }



    }
}


