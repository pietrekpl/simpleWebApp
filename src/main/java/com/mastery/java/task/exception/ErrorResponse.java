package com.mastery.java.task.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;

}
