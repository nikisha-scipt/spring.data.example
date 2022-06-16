package ru.data.dao.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.data.dao.model.Product;
import ru.data.dao.repositories.ProductRepo;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService implements Store {


    private final ProductRepo productRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    @Override
    @Transactional
    public Optional<Product> getByIdProduct(Long id) {
        return productRepo.findById(id);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        boolean res = false;
        List<Product> temp = productRepo.findAll();
        Product delete = temp.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
        if (delete != null) {
            productRepo.delete(delete);
            res = true;
        }
        return res;
    }

    @Override
    @Transactional
    public void save(Product product) {
        productRepo.save(product);
    }

    @Override
    @Transactional
    public boolean isExist(String title) {
        boolean res = false;
        List<Product> temp = productRepo.findAll();
        Optional<Product> exists = temp.stream().filter(e -> e.getTitle().equals(title)).findFirst();
        if (exists.isPresent()) {
            res = true;
        }
        return res;
    }

    @Override
    @Transactional
    public Product getById(Long id) {
        Product res = new Product();
        List<Product> temp = productRepo.findAll();
        Optional<Product> exists = temp.stream().filter(e -> e.getId().equals(id)).findFirst();
        if (exists.isPresent()) {
            res.setId(id);
            res.setTitle(exists.get().getTitle());
            res.setPrice(exists.get().getPrice());
        }
        return res;
    }

    @Override
    @Transactional
    public List<Product> getMin(int min) {
        return productRepo.findMin(min);
    }

    @Override
    @Transactional
    public List<Product> getMax(int max) {
        return productRepo.findMax(max);
    }

    @Override
    @Transactional
    public List<Product> getBetweenMaxAndMin(int min, int max) {
        return productRepo.findMinAndMax(min, max);
    }

    @Override
    @Transactional
    public List<Product> findAll(int pageIndex, int pageSize) {
        return productRepo.findAll(PageRequest.of(pageIndex, pageSize)).stream().toList();
    }
}
