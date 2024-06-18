package com.example.demo.user.controller;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.example.demo.common.domain.exception.CertificationCodeNotMatchedException;
import com.example.demo.common.domain.exception.ResourceNotFoundException;
import com.example.demo.common.service.port.ClockHolder;
import com.example.demo.mock.TestContainer;
import com.example.demo.user.controller.response.MyProfileResponse;
import com.example.demo.user.controller.response.UserResponse;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserStatus;
import com.example.demo.user.domain.UserUpdateDto;

public class UserControllerTest {


    @Test
    void 사용자는_특정_유저의_정보를_개인정보는_소거된채_전달_받을_수_있다() throws Exception {
        // given
    	TestContainer testContainer = TestContainer.builder()
    			.build();
    	testContainer.userRepository.save(User.builder()
    			.id(1L)
				.email("ssehn9327@gmail.com")
				.nickname("ssehn9327")
				.address("Deajeon")
				.certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
				.lastLoginAt(100L)
				.status(UserStatus.ACTIVE)
    			.build());
        // when
    	ResponseEntity<UserResponse> result = testContainer.userController.getUserById(1);
        // then
    	assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
    	assertThat(result.getBody()).isNotNull();
    	assertThat(result.getBody().getId()).isEqualTo(1);
    	assertThat(result.getBody().getEmail()).isEqualTo("ssehn9327@gmail.com");
    	assertThat(result.getBody().getNickname()).isEqualTo("ssehn9327");
    	assertThat(result.getBody().getLastLoginAt()).isEqualTo(100);
    	assertThat(result.getBody().getStatus()).isEqualTo(UserStatus.ACTIVE);
    }

    @Test
    void 사용자는_존재하지_않는_유저의_아이디로_api_호출할_경우_404_응답을_받는다() throws Exception {
    	// given
    	TestContainer testContainer = TestContainer.builder()
    			.build();
        // when
        // then
    	assertThatThrownBy(()-> {
    		testContainer.userController.getUserById(1);
    	}).isInstanceOf(ResourceNotFoundException.class);


    }

    @Test
    void 사용자는_인증_코드로_계정을_활성화_시킬_수_있다() throws Exception {
    	// given
    	TestContainer testContainer = TestContainer.builder()
    			.build();
    	testContainer.userRepository.save(User.builder()
    			.id(1L)
				.email("ssehn9327@gmail.com")
				.nickname("ssehn9327")
				.address("Deajeon")
				.certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
				.lastLoginAt(100L)
				.status(UserStatus.PENDING)
    			.build());
        // when
    	ResponseEntity<Void> result = testContainer.userController.verifyEmail(1, "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");
        // then
    	assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(302));
    	assertThat(testContainer.userRepository.findById(1).get().getStatus()).isEqualTo(UserStatus.ACTIVE);
        
    }

    @Test
    void 사용자는_인증_코드가_일치하지_않을_경우_권한_없음_에러를_내려준다() throws Exception {
    	// given
    	TestContainer testContainer = TestContainer.builder()
    			.build();
    	testContainer.userRepository.save(User.builder()
    			.id(1L)
				.email("ssehn9327@gmail.com")
				.nickname("ssehn9327")
				.address("Deajeon")
				.certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
				.lastLoginAt(100L)
				.status(UserStatus.PENDING)
    			.build());
        // when
        // then
    	assertThatThrownBy(()-> {
        	testContainer.userController.verifyEmail(1, "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaab");
        }).isInstanceOf(CertificationCodeNotMatchedException.class);
    }
//
    @Test
    void 사용자는_내_정보를_불러올_때_개인정보인_주소도_갖고_올_수_있다() throws Exception {
    	// given
    	TestContainer testContainer = TestContainer.builder()
    			.clockHolder(() ->1678530673958L)
    			.build();
    	testContainer.userRepository.save(User.builder()
    			.id(1L)
				.email("ssehn9327@gmail.com")
				.nickname("ssehn9327")
				.address("Deajeon")
				.certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
				.lastLoginAt(100L)
				.status(UserStatus.ACTIVE)
    			.build());
        // when
    	ResponseEntity<MyProfileResponse> result = testContainer.userController.getMyInfo("ssehn9327@gmail.com");
        // then
    	assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
    	assertThat(result.getBody()).isNotNull();
    	assertThat(result.getBody().getId()).isEqualTo(1);
    	assertThat(result.getBody().getEmail()).isEqualTo("ssehn9327@gmail.com");
    	assertThat(result.getBody().getAddress()).isEqualTo("Deajeon");
    	assertThat(result.getBody().getNickname()).isEqualTo("ssehn9327");
//    	assertThat(result.getBody().getLastLoginAt()).isEqualTo(1678530673958L);
    	assertThat(result.getBody().getStatus()).isEqualTo(UserStatus.ACTIVE);
    	
      
    }

    @Test
    void 사용자는_내_정보를_수정할_수_있다() throws Exception {
    	// given
    	TestContainer testContainer = TestContainer.builder()
    			.build();
    	testContainer.userRepository.save(User.builder()
    			.id(1L)
				.email("ssehn9327@gmail.com")
				.nickname("ssehn9327")
				.address("Deajeon")
				.certificationCode("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
				.lastLoginAt(100L)
				.status(UserStatus.ACTIVE)
    			.build());
    	UserUpdateDto userUpdate = UserUpdateDto.builder()
    			.address("Pangyo")
    			.nickname("ssehn93277")
    			.build();
        // when
    	ResponseEntity<MyProfileResponse> result = testContainer.userController.updateMyInfo("ssehn9327@gmail.com", userUpdate);
        // then
    	assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
    	assertThat(result.getBody()).isNotNull();
    	assertThat(result.getBody().getId()).isEqualTo(1);
    	assertThat(result.getBody().getEmail()).isEqualTo("ssehn9327@gmail.com");
    	assertThat(result.getBody().getAddress()).isEqualTo("Pangyo");
    	assertThat(result.getBody().getNickname()).isEqualTo("ssehn93277");
    	assertThat(result.getBody().getLastLoginAt()).isEqualTo(100);
    	assertThat(result.getBody().getStatus()).isEqualTo(UserStatus.ACTIVE);

}
}
