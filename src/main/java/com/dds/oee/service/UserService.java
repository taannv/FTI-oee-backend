package com.dds.oee.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dds.oee.common.CommonService;
import com.dds.oee.entity.User;
import com.dds.oee.exception.NotFoundEntityException;
import com.dds.oee.repository.UserRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author : duybv
 * Aug 28, 2019
 */
@Log4j2
@Service
public class UserService extends CommonService<User, Long, UserRepository> {

	public UserService(UserRepository repo) {
		super(repo);
	}

	public Boolean existsByUsername(String name) {
		return repo.existsByUsername(name);
	}

	public Boolean existsByEmail(String email) {
		return repo.existsByEmail(email);
	}

	public User findByUsernameOrElseThrow(String userName) throws NotFoundEntityException {
//		User user = new User();
		log.debug(userName);
		try{
			User user = repo.findByUsername(userName)
					.orElseThrow(NotFoundEntityException.ofSupplier("Không tìm thấy Tài khoản theo username " + userName));
			return user;
		}catch (Exception exception){
			log.error(exception.getMessage());
		}
		return  null;
//		return repo.findByUsername(userName)
//				.orElseThrow(NotFoundEntityException.ofSupplier("Không tìm thấy Tài khoản theo username " + userName));
	}
	
	public User findByUsernameOrEmailOrElseThrow(String usernameOrEmail) throws NotFoundEntityException {

		return repo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(NotFoundEntityException.ofSupplier("Không tìm thấy Tài khoản theo username hoặc email: " + usernameOrEmail));

	}
	public List<User> findAllUser(){
		return repo.findAll();
	}
	public User findById(Long id) throws NotFoundEntityException {
		return repo.findById(id).orElseThrow(NotFoundEntityException.ofSupplier("Không tìm thấy tài khoản theo id:"+id));
	}
	@Override
	protected String notFoundMessage(Long id) {
		return "Không tìm thấy Tài khoản có id: " + id;
	}

}
