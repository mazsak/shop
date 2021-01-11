package api.shop.shop.controller;


import api.shop.shop.model.Product;
import api.shop.shop.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Product> findProducts(@RequestParam(required = false) String directory,
                                      @RequestParam(required = false) String subdirectory,
                                      @RequestParam int current,
                                      @RequestParam int size) {
        if (directory == null && subdirectory == null) {
            return productService.getAllProducts(current, size);
        } else if (directory != null && subdirectory == null) {
            return productService.getDirectoryProducts(directory, current, size);
        } else {
            return productService.getSubdirectoryProducts(subdirectory, current, size);
        }
    }
}
