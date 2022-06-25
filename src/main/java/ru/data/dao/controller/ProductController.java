package ru.data.dao.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.data.dao.model.Product;
import ru.data.dao.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final ProductService service;
    private List<Product> list = new ArrayList<>();

    @GetMapping()
    public String index() {
        return "index";
    }

    @GetMapping(value = "/allPage")
    public List<Product> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex, @RequestParam(name = "s", defaultValue = "1") int pageSize) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return service.findAll(pageIndex - 1, pageSize);
    }

    @GetMapping(value = "/all")
    public List<Product> findAllProduct() {
        return service.getAllProduct();
    }

    @GetMapping(value = "/{id}")
    public Optional<Product> findProductById(@PathVariable Long id) {
        return service.getByIdProduct(id);
    }

    @GetMapping(value = "/del")
    public List<Product> delete(@RequestParam(name = "id") Long id) {
        service.delete(id);
        return service.getAllProduct();
    }

    @GetMapping(value = "/min/{min}")
    public List<Product> findMin(@PathVariable int min) {
        return service.getMin(min);
    }

    @GetMapping(value = "/max/{max}")
    public List<Product> findMax(@PathVariable int max) {
        return service.getMax(max);
    }

    // http://localhost:22333/app/product/btw?min=66&max=77
    @GetMapping(value = "/btw")
    public List<Product> findBtw(@RequestParam(name = "min") int min, @RequestParam(name = "max") int max) {
        return service.getBetweenMaxAndMin(min, max);
    }

    @PostMapping(value = "/save")
    public Product save(@RequestParam(name = "title") String title, @RequestParam(name = "price") String price) {
        Product product = new Product(title, Integer.parseInt(price));
        if (!service.isExist(product.getTitle())) {
            service.save(product);
        }
        return product;
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity putProduct(@RequestBody Product product, @PathVariable Long id) {
        try {
            Product prd = service.getById(id);
            prd.setTitle(product.getTitle());
            prd.setPrice(product.getPrice());
            service.save(prd);
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

    @GetMapping(value = "/addToCart/{title}")
    public List<Product> getProduct(@PathVariable String title) {
        Product product = service.findByTitle(title);
        list.add(product);
        return list;
    }

    @GetMapping(value = "/Cart")
    public List<Product> getProductBasket() {
        return list;
    }

}
