package com.example.demo.user.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import com.example.demo.user.service.port.UserRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{
	
	 private final UserJpaRepository userJpaRepository;

	@Override
	public Optional<User> findByIdAndStatus(long id, UserStatus userStatus) {
		// TODO Auto-generated method stub
		return userJpaRepository.findByIdAndStatus(id, userStatus).map(userEntity -> userEntity.toModel());
	}

	@Override
	public Optional<User> findByEmailAndStatus(String email, UserStatus userStatus) {
		// TODO Auto-generated method stub
		return userJpaRepository.findByEmailAndStatus(email, userStatus).map(userEntity -> userEntity.toModel());
	}

	@Override
	public Optional<User> findById(long id) {
		// TODO Auto-generated method stub
		return userJpaRepository.findById(id).map(userEntity -> userEntity.toModel());
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return userJpaRepository.save(UserEntity.fromModel(user)).toModel();
	}

}
