package api.shop.shop.controller;


import api.shop.shop.model.Product;
import api.shop.shop.service.ProductService;
import api.shop.shop.utils.ItemsResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ItemsResponse> findProducts(@RequestParam(required = false) String directory,
                                                      @RequestParam(required = false) String subdirectory,
                                                      @RequestParam int current,
                                                      @RequestParam int size) {
        ItemsResponse itemsResponse = new ItemsResponse();
        if (directory == null && subdirectory == null) {
            Page<Product> allProducts = productService.getAllProducts(current - 1, size);
            itemsResponse.setCurrentPage(current);
            itemsResponse.setPageSize(size);
            itemsResponse.setProducts(allProducts.getContent());
            itemsResponse.setTotalItems(allProducts.getTotalElements());
            itemsResponse.setTotalPages(allProducts.getTotalPages());
            return ResponseEntity.ok(itemsResponse);
        } else if (directory != null && subdirectory == null) {
            Page<Product> directoryProducts = productService.getDirectoryProducts(directory, current - 1, size);
            itemsResponse.setCurrentPage(current);
            itemsResponse.setPageSize(size);
            itemsResponse.setProducts(directoryProducts.getContent());
            itemsResponse.setTotalItems(directoryProducts.getTotalElements());
            itemsResponse.setTotalPages(directoryProducts.getTotalPages());
            return ResponseEntity.ok(itemsResponse);
        } else {
            Page<Product> subdirectoryProducts = productService.getSubdirectoryProducts(subdirectory, current - 1, size);
            itemsResponse.setCurrentPage(current);
            itemsResponse.setPageSize(size);
            itemsResponse.setProducts(subdirectoryProducts.getContent());
            itemsResponse.setTotalItems(subdirectoryProducts.getTotalElements());
            itemsResponse.setTotalPages(subdirectoryProducts.getTotalPages());
            return ResponseEntity.ok(itemsResponse);
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
