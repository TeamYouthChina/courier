package com.youthchina.courier.dto;

/**
 * Created by zhongyangwu on 5/6/19.
 */
public class VerifyEmailDTO {
    private String mailto;
    private String firstName;
    private String lastName;
    private String code;

    public VerifyEmailDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

