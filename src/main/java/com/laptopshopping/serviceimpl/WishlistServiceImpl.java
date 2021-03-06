package com.laptopshopping.serviceimpl;

import java.util.List;
import java.util.NoSuchElementException;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptopshopping.exception.ResourceNotFoundException;
import com.laptopshopping.model.Product;
import com.laptopshopping.model.Wishlist;
//import com.laptopshopping.repository.ProductRepository;
import com.laptopshopping.repository.WishlistRepository;
import com.laptopshopping.service.ProductService;
import com.laptopshopping.service.WishlistService;

@Service
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	private WishlistRepository wishlistRepository;

	@Autowired
	private ProductService productService;

	/*
	 * @Autowired private ProductRepository productRepository;
	 */
	@Override
	public Wishlist addProductToWishlist(int productId, Wishlist wishlist) {
		// TODO Auto-generated method stub
		Product product = productService.getByProductId(productId);
		wishlist.setProduct(product);
		//System.out.println(product);
		return wishlistRepository.save(wishlist);
	}

	@Override public void deleteProductById(int wishlistId) { 
		// TODOAuto-generated method stub
	  wishlistRepository.findByWishlistId(wishlistId).orElseThrow(()->new
	  NoSuchElementException());
	  wishlistRepository.deleteById(wishlistId);
	  }

	@Override
	public List<Wishlist> viewWishlistProducts() {
		// TODO Auto-generated method stub
		return wishlistRepository.findAll();
	}

	@Override
	public Product viewProduct(int wishlistId) {
		return wishlistRepository.findById(wishlistId).get().getProduct();
	}

	@Override
	public Wishlist getByWishlistId(int wishlistId) {
		// TODO Auto-generated method stub
		 return wishlistRepository.findById(wishlistId).orElseThrow(() -> new ResourceNotFoundException("Wishlist",
				"WishlistId", "no wishlist is present with this id" + wishlistId));
	}

}
