package api.shop.shop.controller;


import api.shop.shop.model.Product;
import api.shop.shop.service.ProductService;
import api.shop.shop.utils.ItemsResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Product>> findProducts(@RequestParam(required = false) String directory,
                                                      @RequestParam(required = false) String subdirectory,
                                                      @RequestParam int current,
                                                      @RequestParam int size) {
        ItemsResponse itemsResponse = new ItemsResponse();
        if (directory == null && subdirectory == null) {
            List<Product> allProducts = productService.getAllProducts(current, size);

            return ResponseEntity.ok(allProducts);
        } else if (directory != null && subdirectory == null) {
            List<Product> directoryProducts = productService.getDirectoryProducts(directory, current, size);
            return ResponseEntity.ok(directoryProducts);
        } else {
            List<Product> subdirectoryProducts = productService.getSubdirectoryProducts(subdirectory, current, size);
            return ResponseEntity.ok(subdirectoryProducts);
        }
    }

    @PostMapping(value = "/", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try {
            productService.save(product);
            return ResponseEntity.created(new URI("/product/" + product.getId())).body(product);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping(value = "/product/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> getProduct(@PathVariable final Long id) {
        try {
            Product product = productService.findById(id);
            return ResponseEntity.ok(product);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping(value = "/product/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable final Long id) {
        try {
            product.setId(id);
            productService.save(product);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping(value = "/product/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> removeProduct(@PathVariable final Long id) {
        try {
            boolean removed = productService.deleteById(id);
            if (removed) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
