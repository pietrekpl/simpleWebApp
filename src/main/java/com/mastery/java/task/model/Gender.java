package com.mastery.java.task.model;
import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
public enum Gender {

    MALE("male"),
    FEMALE("female");

    private final String text;


}



