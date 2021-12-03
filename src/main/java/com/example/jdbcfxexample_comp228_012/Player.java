package com.example.jdbcfxexample_comp228_012;

public class Player {
    Integer p_id;
    String p_fName;
    String p_lName;
    String p_address;
    String p_postalCode;
    String p_province;
    Long p_phoneNum;

    public Player(Integer p_id, String p_fName, String p_lName, String p_address, String p_postalCode, String p_province, Long p_phoneNum) {
        this.p_id = p_id;
        this.p_fName = p_fName;
        this.p_lName = p_lName;
        this.p_address = p_address;
        this.p_postalCode = p_postalCode;
        this.p_province = p_province;
        this.p_phoneNum = p_phoneNum;
    }

    public Integer getP_id() {
        return p_id;
    }

    public String getP_fName() {
        return p_fName;
    }

    public String getP_lName() {
        return p_lName;
    }

    public String getP_address() {
        return p_address;
    }

    public String getP_postalCode() {
        return p_postalCode;
    }

    public String getP_province() {
        return p_province;
    }

    public Long getP_phoneNum() {
        return p_phoneNum;
    }

    public void setP_id(Integer p_id) {
        this.p_id = p_id;
    }

    public void setP_fName(String p_fName) {
        this.p_fName = p_fName;
    }

    public void setP_lName(String p_lName) {
        this.p_lName = p_lName;
    }

    public void setP_address(String p_address) {
        this.p_address = p_address;
    }

    public void setP_postalCode(String p_postalCode) {
        this.p_postalCode = p_postalCode;
    }

    public void setP_province(String p_province) {
        this.p_province = p_province;
    }

    public void setP_phoneNum(Long p_phoneNum) {
        this.p_phoneNum = p_phoneNum;
    }
}
