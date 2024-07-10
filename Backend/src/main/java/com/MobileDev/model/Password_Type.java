package com.MobileDev.model;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Password_Type {

        PLAIN_TEXT(0),
        HASHED(10);

        private final int value;

    public static Password_Type fromValue(int value) {
            for (Password_Type type : Password_Type.values()) {
                if (type.value == value) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Invalid password type: " + value);
        }
}
