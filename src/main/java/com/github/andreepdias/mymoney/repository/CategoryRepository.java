package com.github.andreepdias.mymoney.repository;

import com.github.andreepdias.mymoney.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Page<Category> findAllByOrderByCategoryTypeAscNameAsc(Pageable pageable);

    List<Category> findAllByOrderByCategoryTypeAscNameAsc();
}
