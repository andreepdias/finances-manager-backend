package com.github.andreepdias.mymoney.service;

import com.github.andreepdias.mymoney.exception.DataConsistencyException;
import com.github.andreepdias.mymoney.exception.ObjectNotFoundException;
import com.github.andreepdias.mymoney.model.entity.Category;
import com.github.andreepdias.mymoney.model.entity.User;
import com.github.andreepdias.mymoney.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    private final TransactionService transactionService;
    private  final UserService userService;

    public List<Category> findAll() {
        Integer userId = userService.findAuthenticatedUserId();
        return repository.findAllByUserIdOrderByCategoryTypeAscNameAsc(userId);
    }

    public Page<Category> findPage(PageRequest pageRequest) {
        Integer userId = userService.findAuthenticatedUserId();
        return repository.findAllByUserIdOrderByCategoryTypeAscNameAsc(userId , pageRequest);
    }

    public Category findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Category with id " + id + " was not found"));
    }

    public boolean existsById(Integer id){
        return repository.existsById(id);
    }

    public Category insert(Category category){
        User user = userService.findAuthenticatedUser();
        category.setId(null);
        category.setUser(user);
        return repository.save(category);
    }

    public Category update(Category newCategory) {
        Category existentCategory = findById(newCategory.getId());
        updateCategoryData(existentCategory, newCategory);
        return repository.save(existentCategory);
    }

    public void delete(Integer id) {
        if(!existsById(id)){
            throw new ObjectNotFoundException("Category with id " + id + " was not found.");
        }

        boolean existsTransactionsAssociated = transactionService.existsByCategoryId(id);
        if(existsTransactionsAssociated){
            throw new DataConsistencyException("You can't delete a category with transactions associated.");
        }

        repository.deleteById(id);
    }

    private void updateCategoryData(Category existentCategory, Category newCategory) {
        existentCategory.setName(newCategory.getName());
        existentCategory.setDescription(newCategory.getDescription());
        existentCategory.setCategoryType(newCategory.getCategoryType());
    }
}
