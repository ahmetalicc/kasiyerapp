package org.sau.toyota.backend.saleservice.fixture;


import org.sau.toyota.backend.saleservice.dto.request.ProductRequest;
import org.sau.toyota.backend.saleservice.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
/** @author Ahmet Alıç
 * @since 15-06-2024
 * Fixture class for generating Product objects and ProductRequest objects for testing purposes.
 */
public class ProductFixture extends Fixture<Product> {

    CategoryFixture categoryFixture = new CategoryFixture();

    /**
     * Creates a list of Product objects with randomly generated fake data.
     *
     * @return List of Product objects
     */
    public List<Product> createProductList(){
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            Product product = Product.builder()
                    .id(faker.number().randomNumber())
                    .name(faker.commerce().productName())
                    .price(faker.number().randomDouble(2, 10, 100))
                    .image(new byte[]{})
                    .description(faker.lorem().sentence())
                    .brand(faker.company().name())
                    .expirationDate(faker.date().future(365, TimeUnit.DAYS))
                    .stock(faker.number().numberBetween(100, 1000))
                    .barcode(new byte[]{})
                    .category(categoryFixture.createCategory())
                    .build();
            products.add(product);
        }

        return products;
    }
    /**
     * Creates a single Product object with randomly generated fake data.
     *
     * @return Product object
     */
    public Product createProduct(){
        Product product = new Product();
        product.setId(faker.number().randomNumber());
        product.setName(faker.commerce().productName());
        product.setPrice(faker.number().randomDouble(2, 0, 25));
        product.setImage(new byte[]{});
        product.setDescription(faker.lorem().sentence());
        product.setBrand(faker.company().name());
        product.setExpirationDate(faker.date().future(365, TimeUnit.DAYS));
        product.setStock(faker.number().numberBetween(100, 1000));
        product.setBarcode(new byte[]{});
        product.setCategory(categoryFixture.createCategory());

        return product;
    }
    /**
     * Creates a ProductRequest object with randomly generated fake data.
     *
     * @return ProductRequest object
     */
    public ProductRequest createProductRequest(){
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName(faker.commerce().productName());
        productRequest.setPrice(faker.number().randomDouble(2, 10, 100));
        productRequest.setDescription(faker.lorem().sentence());
        productRequest.setBrand(faker.company().name());
        productRequest.setExpirationDate(faker.date().future(365, TimeUnit.DAYS));
        productRequest.setStock(faker.number().numberBetween(100, 1000));
        productRequest.setBarcode(new byte[]{});
        productRequest.setCategoryId(categoryFixture.createCategory().getId());

        return productRequest;
    }

}
