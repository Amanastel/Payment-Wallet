package com.payment.exceptions;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class ErrorDetails {

	private LocalDateTime timeStamp;
	private String message;
	private String description;
}
