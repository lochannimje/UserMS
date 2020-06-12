package team12.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import team12.user.controller.BuyerController;
import team12.user.controller.CartController;
import team12.user.dto.Wishlist;
import team12.user.entity.WishListId;
import team12.user.entity.WishlistEntity;

import team12.user.exception.UserException;
import team12.user.exception.WishlistAlreadyExist;
import team12.user.exception.WishlistNotAvailableException;
import team12.user.repository.CartRepository;
import team12.user.repository.WishListRepository;

@Service
public class WishListService {

	@Value("${productAPIURL}")
	public String productAPIURI;

	@Autowired
	WishListRepository wishListRepository;

	@Autowired
	CartController cartController;

	@Autowired
	BuyerController buyerController;

	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	CartRepository cartRepository;

	public void addToWishList(Wishlist wishlist) throws UserException {
		WishlistEntity wishlistEntity = wishListRepository.findByIdBuyerIdAndIdProdId(wishlist.getBuyerId(),
				wishlist.getProdId());
		if (wishlistEntity == null) {
			WishlistEntity newwishlistEntity = new WishlistEntity(
					new WishListId(wishlist.getBuyerId(), wishlist.getProdId()));
			wishListRepository.save(newwishlistEntity);
		} else {
			throw new WishlistAlreadyExist("wishlist.ALREADY_EXISTS");

		}

	}

	public void removeFromWishList(Wishlist wishlist) throws UserException {
		WishlistEntity wishlistEntity = wishListRepository.findByIdBuyerIdAndIdProdId(wishlist.getBuyerId(),
				wishlist.getProdId());
		if (wishlistEntity != null) {
			wishListRepository.deleteById(new WishListId(wishlist.getBuyerId(), wishlist.getProdId()));
		} else {
			throw new WishlistNotAvailableException("wishlist.NOT_AVAILABLE");
		}
	}

}
