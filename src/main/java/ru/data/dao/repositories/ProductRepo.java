package ru.data.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.data.dao.model.Product;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.price > :min")
    List<Product> findMin(int min);

    @Query("select p from Product p where p.price < :max")
    List<Product> findMax(int max);

    @Query("select p from Product p where p.price <= :max and p.price >= :min")
    List<Product> findMinAndMax(int min, int max);

}
