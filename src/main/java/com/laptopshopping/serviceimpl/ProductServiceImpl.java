package com.laptopshopping.serviceimpl;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptopshopping.exception.ResourceNotFoundException;
import com.laptopshopping.model.Category;
//import com.laptopshopping.model.Category;
import com.laptopshopping.model.Product;
import com.laptopshopping.repository.ProductRepository;
import com.laptopshopping.service.CategoryService;
//import com.laptopshopping.service.CategoryService;
import com.laptopshopping.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryService categoryService;

	@Override
	public Product addProduct(Product product, int categoryId) {
		// TODO Auto-generated method stub
		Category category = categoryService.getCategoryById(categoryId);
		product.setCategoryId(categoryId);
		return productRepository.save(product);
	}

	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public Product updateProduct(Product product, int productId) {
		// TODO Auto-generated method stub
		Product existingProduct = productRepository.findById(productId).orElseThrow(
				() -> new ResourceNotFoundException("Product", "ProductId", "product with this id is not found"));
		existingProduct.setProductCompany(product.getProductCompany());
		existingProduct.setProductFeatures(product.getProductFeatures());
		existingProduct.setProductModel(product.getProductModel());
		existingProduct.setProductPrice(product.getProductPrice());
		productRepository.save(existingProduct);
		return existingProduct;
	}

	@Override
	public Product getByProductId(int productId) {
		// TODO Auto-generated method stub
		return productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product",
				"ProductId", "no product is present with this id" + productId));
	}

	@Override
	public void deleteProductById(int productId) {
		// TODO Auto-generated method stub
		productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product", "ProductId", productId));
		productRepository.deleteById(productId);
	}

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<Product> getProductByCategoryId(int categoryId) { //
	 * TODO Auto-generated method stub return (List<Product>)
	 * productRepository.findByCategoryId(categoryId).orElseThrow(() -> new
	 * ResourceNotFoundException("Product", "ProductId", categoryId)); }
	 */
}
