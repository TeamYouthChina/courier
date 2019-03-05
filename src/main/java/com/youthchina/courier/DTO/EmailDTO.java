package com.youthchina.courier.DTO;

/**
 * @program: courier
 * @description: 邮箱DTO
 * @author: Qinghong Wang
 * @create: 2019-03-05 14:33
 **/
public class EmailDTO {
    private Integer user_id;
    private String company_email;
    private byte[] bytes;

    public EmailDTO() {

    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getCompany_email() {
        return company_email;
    }

    public void setCompany_email(String company_email) {
        this.company_email = company_email;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
