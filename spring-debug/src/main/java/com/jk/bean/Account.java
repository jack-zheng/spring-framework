package com.jk.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Account {
	@Value("Jack")
	private String name;
}
