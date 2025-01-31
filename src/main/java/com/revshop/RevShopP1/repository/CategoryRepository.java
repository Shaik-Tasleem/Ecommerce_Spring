package com.revshop.RevShopP1.repository;

import com.revshop.RevShopP1.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Custom query to find a category by its name
    Category findByCategoryName(String categoryName);

}
