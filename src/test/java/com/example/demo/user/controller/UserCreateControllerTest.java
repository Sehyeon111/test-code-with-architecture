package com.example.demo.user.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.mock.TestContainer;
import com.example.demo.user.controller.response.UserResponse;
import com.example.demo.user.domain.User;
import com.example.demo.user.domain.UserCreateDto;
import com.example.demo.user.domain.UserStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserCreateControllerTest {

    @Test
    void 사용자는_회원_가입을_할_수있고_회원가입된_사용자는_PENDING_상태이다() throws Exception {
    	// given
    	TestContainer testContainer = TestContainer.builder()
    			.uuidHolder(()->"aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa")
    			.build();
    	UserCreateDto userCreate = UserCreateDto.builder()
    			.email("ssehn9327@gmail.com")
    			.nickname("ssehn9327")
    			.address("Deajeon")
    			.build();
    
        // when
    	ResponseEntity<UserResponse> result = testContainer.userCreateController.createUser(userCreate);
        // then
    	assertThat(result.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
    	assertThat(result.getBody()).isNotNull();
    	assertThat(result.getBody().getId()).isEqualTo(1);
    	assertThat(result.getBody().getEmail()).isEqualTo("ssehn9327@gmail.com");
    	assertThat(result.getBody().getNickname()).isEqualTo("ssehn9327");
    	assertThat(result.getBody().getLastLoginAt()).isNull();
    	assertThat(result.getBody().getStatus()).isEqualTo(UserStatus.PENDING);
    	assertThat(testContainer.userRepository.findById(1).get().getCertificationCode()).isEqualTo("aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa");
  
    }

}
