package team12.user.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import team12.user.entity.WishListId;
import team12.user.entity.WishlistEntity;

public interface WishListRepository extends CrudRepository<WishlistEntity, WishListId>{
	public List<WishlistEntity> findByIdBuyerId(int buyerId);
	
	public WishlistEntity findByIdBuyerIdAndIdProdId(int buyerId,int ProdId);
}
