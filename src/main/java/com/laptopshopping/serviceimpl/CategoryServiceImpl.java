package com.laptopshopping.serviceimpl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptopshopping.exception.ResourceNotFoundException;
import com.laptopshopping.model.Category;
import com.laptopshopping.repository.CategoryRepository;
import com.laptopshopping.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category addCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	@Override
	public Category updateCategory(Category category, int categoryId) {
		// TODO Auto-generated method stub
		Category existingCategory = categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId","category not found with this id "+categoryId));
		existingCategory.setCategoryName(category.getCategoryName());
		existingCategory.setCategoryDiscription(category.getCategoryDiscription());
		return existingCategory;
	}

	@Override
	public Category getCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		//return categoryRepository.findById(categoryId).orElseThrow(()->new NoSuchElementException());
		return categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId","category not found with this id "+categoryId));
	}

	@Override
	public void deleteCategoryById(int categoryId) {
		// TODO Auto-generated method stub
		categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","CategoryId",categoryId));
		categoryRepository.deleteById(categoryId);
	}

}
