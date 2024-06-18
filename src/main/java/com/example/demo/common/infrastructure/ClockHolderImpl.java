package com.example.demo.common.infrastructure;

import java.time.Clock;

import org.springframework.stereotype.Component;

import com.example.demo.common.service.port.ClockHolder;

@Component
public class ClockHolderImpl implements ClockHolder{

	@Override
	public long milis() {
		// TODO Auto-generated method stub
		return Clock.systemUTC().millis();
	}

}
