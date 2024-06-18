package com.example.demo.user.controller.port;

import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserCreateDto;
import com.example.demo.user.domain.UserUpdateDto;

public interface UserService {

	User getByEmail(String email);
	User create(UserCreateDto userCreateDto);
	User getById(long id);
	User update(long id, UserUpdateDto userUpdateDto);
	void login(long id);
	void verifyEmail(long id, String certificationCode);

}
