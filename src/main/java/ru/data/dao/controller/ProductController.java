package ru.data.dao.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.data.dao.model.Product;
import ru.data.dao.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping(value = "/all")
    public List<Product> findAllProduct() {
        return service.getAllProduct();
    }

    @GetMapping(value = "/product/{id}")
    public Optional<Product> findProductById(@PathVariable Long id) {
        return service.getByIdProduct(id);
    }

    @GetMapping(value = "/del/{id}")
    public List<Product> delete(@PathVariable Long id) {
        service.delete(id);
        return service.getAllProduct();
    }

    @GetMapping(value = "/product/min/{min}")
    public List<Product> findMin(@PathVariable int min) {
        return service.getMin(min);
    }

    @GetMapping(value = "/product/max/{max}")
    public List<Product> findMax(@PathVariable int max) {
        return service.getMax(max);
    }

    // http://localhost:22333/app/product/btw?min=66&max=77
    @GetMapping(value = "/product/btw")
    public List<Product> findBtw(@RequestParam(name = "min") int min, @RequestParam(name = "max") int max) {
        return service.getBetweenMaxAndMin(min, max);
    }

    @PostMapping(value = "/save")
    public Product save(@RequestBody Product product) {
        if (!service.isExist(product.getTitle())) {
            service.save(product);
        }
        return product;
    }

}
