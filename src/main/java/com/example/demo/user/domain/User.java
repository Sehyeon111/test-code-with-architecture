package com.example.demo.user.domain;

import java.time.Clock;

import com.example.demo.common.domain.exception.CertificationCodeNotMatchedException;
import com.example.demo.common.service.port.ClockHolder;
import com.example.demo.common.service.port.UuidHolder;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {

    private final Long id;

    private final String email;

    private final String nickname;

    private final String address;

    private final String certificationCode;

    private final UserStatus status;

    private final Long lastLoginAt;

    @Builder
	public User(Long id, String email, String nickname, String address, String certificationCode, UserStatus status,
			Long lastLoginAt) {
		super();
		this.id = id;
		this.email = email;
		this.nickname = nickname;
		this.address = address;
		this.certificationCode = certificationCode;
		this.status = status;
		this.lastLoginAt = lastLoginAt;
	}

	public static User from(UserCreateDto userCreate, UuidHolder uuidHolder) {
		User user = User.builder()
        		.email(userCreate.getEmail())
        		.nickname(userCreate.getNickname())
        		.address(userCreate.getAddress())
        		.status(UserStatus.PENDING)
        		.certificationCode(uuidHolder.random())
        		.build();
		return user;
	}

	public User update(UserUpdateDto userUpdateDto) {
		return User.builder()
				.id(id)
				.email(email)
				.nickname(userUpdateDto.getNickname())
				.address(userUpdateDto.getAddress())
				.certificationCode(certificationCode)
				.status(status)
				.lastLoginAt(lastLoginAt)
				.build();
		
		
	}
	
	public User login(ClockHolder clockHolder) {
		return User.builder()
				.id(id)
				.email(email)
				.nickname(nickname)
				.address(address)
				.certificationCode(certificationCode)
				.status(status)
				.lastLoginAt(clockHolder.milis())
				.build();
		
	}
	
	public User certificate(String certificationCode) {
		if (!this.certificationCode.equals(certificationCode)) {
            throw new CertificationCodeNotMatchedException();
        }
		return User.builder()
				.id(id)
				.email(email)
				.nickname(nickname)
				.address(address)
				.certificationCode(certificationCode)
				.status(UserStatus.ACTIVE)
				.lastLoginAt(Clock.systemUTC().millis())
				.build();
	}


    
    
}
