package team12.user.repository;








import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import team12.user.entity.CartEntity;
import team12.user.entity.CartId;

@Repository
public interface CartRepository extends CrudRepository<CartEntity, CartId>{
	

	
}