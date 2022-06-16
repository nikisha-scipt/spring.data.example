package ru.data.dao.service;

import ru.data.dao.model.Product;

import java.util.List;
import java.util.Optional;

public interface Store {

    List<Product> getAllProduct();

    Optional<Product> getByIdProduct(Long id);

    boolean delete(Long id);

    void save(Product product);

    boolean isExist(String newsTitle);

    Product getById(Long id);

    List<Product> getMin(int min);

    List<Product> getMax(int max);

    List<Product> getBetweenMaxAndMin(int min, int max);

    List<Product> findAll(int pageIndex, int pageSize);

}
