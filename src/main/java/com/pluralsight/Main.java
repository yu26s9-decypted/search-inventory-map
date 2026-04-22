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
                    processListAllProducts(product);
                    break;
                case 2:
                    processLookupProductById(product);
                    break;
                case 3:
                    processFindProductWithinPriceRange(product);
                    break;
                case 5:
                    System.out.println("Thank you have a nice day!");
                    return;
            }


        }



    }
    public static void processListAllProducts(HashMap<Integer, Product> products){
        for( Product p : products.values()) {
            System.out.println(p);
        }

    }

    public static void processLookupProductById(HashMap<Integer, Product> products) {
        int getProductId = Console.askForInt("What is the product ID?", 2000, 5000);

        for (Product p : products.values())
        {
            if (p.getProductId() == getProductId ){
                System.out.printf("Found: [ID: %d] [PRODUCT: %s] [RETAIL PRICE: $%.2f]\n",
                        p.getProductId(),
                        p.getProductName(),
                        p.getProductPrice()
                );
                return;
            }
        }
        System.out.printf("Cannot find a product with this id. \n");

    }

    public static HashMap<Integer, Product> processFindProductWithinPriceRange(HashMap<Integer, Product> products) {
        double askMinPrice = Console.askForDouble("What's the minimum price");
        double askMaxPrice = Console.askForDouble("What's the maximum price");

        HashMap<Integer, Product> matchingProduct = new HashMap<>();

        for(Product p: products.values())
        {
            if (askMaxPrice >= (p.getProductPrice()) && askMinPrice <= (p.getProductPrice())){
                matchingProduct.put(p.getProductId(), p);
                System.out.printf("Found: [ID: %d] [PRODUCT: %s] [RETAIL PRICE: $%.2f]\n",
                        p.getProductId(),
                        p.getProductName(),
                        p.getProductPrice()
                );

            }

        }
        if(matchingProduct.isEmpty()) {
            System.out.printf("No item found within your price range");
        }
        System.out.println("Found " + matchingProduct.size() + (matchingProduct.size() == 1 ? " result." : " results." ));
        return matchingProduct;
    }




}


