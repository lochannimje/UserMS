package team12.user.service;

import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team12.user.dto.Seller;
import team12.user.entity.SellerEntity;
import team12.user.exception.AlreadyEmailIdExistException;
import team12.user.exception.AlreadyPhoneNumberExistException;
import team12.user.exception.InvalidEmailIdException;
import team12.user.exception.InvalidNameException;
import team12.user.exception.InvalidPasswordException;
import team12.user.exception.InvalidPhoneNumberException;
import team12.user.exception.UserException;
import team12.user.exception.WrongEmailException;
import team12.user.exception.WrongPasswordException;
import team12.user.repository.SellerRepository;



@Service
public class SellerService {
	
	@Autowired
	SellerRepository sellerRepository;

	public void sellerRegisterion(Seller seller) throws UserException {
		validateSeller(seller);
		SellerEntity sellerEntity = new SellerEntity();
		BeanUtils.copyProperties(seller, sellerEntity);
		sellerRepository.save(sellerEntity);
	}
	
	private void validateSeller(Seller seller) throws UserException {

		
		if(!isValidName(seller.getName()))
			throw new InvalidNameException("BuyerRegistration.INVALID_NAME");
		if(!isValidEmail(seller.getEmail()))
			throw new InvalidEmailIdException("BuyerRegistration.INVALID_EMAIL");
		if(!isValidPhoneNumber(seller.getphoneNumber()))
			throw new InvalidPhoneNumberException("BuyerRegistration.INVALID_PHONENUMBER");
		if(!isvalidPassword(seller.getPassword()))
			throw new InvalidPasswordException("BuyerRegistration.INVALID_PASSWORD");
		if(!isAlreadyPhoneNumberExist(seller.getphoneNumber()))
			throw new AlreadyPhoneNumberExistException("BuyerRegistration.ALREADY_PH_EXISTS");
		if(!isAlreadyEmailIdExist(seller.getEmail()))
			throw new AlreadyEmailIdExistException("BuyerRegistration.ALREADY_EMAIL_EXISTS");
		
	}

	private boolean isAlreadyEmailIdExist(String email) {
		
		SellerEntity sellerEntity=sellerRepository.findByEmail(email);
		if (sellerEntity!=null)
			return false;
		return true;
	}

	private boolean isAlreadyPhoneNumberExist(String phoneNumber) {
		
		SellerEntity sellerEntity=sellerRepository.findByPhoneNumber(phoneNumber);
		if (sellerEntity!=null)
			return false;
		return true;
	}

	private boolean isvalidPassword(String password) {
		
		return Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{7,20}$",password);
	}

	private boolean isValidPhoneNumber(String phoneNumber) {
		
		return Pattern.matches("^\\d{10}$", phoneNumber);
	}

	private boolean isValidEmail(String email) {
		
		return Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$",email);
	}

	private boolean isValidName(String name) {
		
		return Pattern.matches("^[a-zA-Z]+[-a-zA-Z\\s]+([-a-zA-Z]+)$", name);
	}
	
	
	public void sellerLogin(Seller  seller) throws UserException {
		SellerEntity sellerEntity=sellerRepository.findByEmail(seller.getEmail());
		if(sellerEntity!= null) {
			if(sellerEntity.getPassword().equals(seller.getPassword())) {
				
			}
			else {
				throw new WrongPasswordException("BuyerLogin.INVALID_PASSWORD");
			}	
		}
		else {
			throw new WrongEmailException("BuyerLogin.INVALID_EMAILID");
		}
	}
	
	
	public void deactivateSeller(Seller seller) throws UserException {
		SellerEntity sellerEntity=sellerRepository.findByEmail(seller.getEmail());
		if(sellerEntity!= null) {
			sellerEntity.setActive(false);
			sellerRepository.save(sellerEntity);
			
		}
		else {
			throw new WrongEmailException("BuyerLogin.INVALID_EMAILID");
		}		
	}
}
