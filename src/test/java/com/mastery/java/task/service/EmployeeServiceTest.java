package com.mastery.java.task.service;

import com.mastery.java.task.jms.JmsEmployeeSender;
import com.mastery.java.task.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    JmsEmployeeSender jmsEmployeeSender;
    @Mock
    EmployeeRepository employeeRepository;
    @InjectMocks
    EmployeeService employeeService;


    @Test
    public void shouldReturnEmptyList() {
        when(employeeService.filterEmployeesByFirstNameOrLastName("","")).thenReturn(Collections.emptyList());
        Assertions.assertEquals(0, employeeService.filterEmployeesByFirstNameOrLastName("","").size());
    }
}



