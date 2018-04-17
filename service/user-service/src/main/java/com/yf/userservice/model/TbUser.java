package com.yf.userservice.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TbUser implements Serializable{

    private  Long id;

    private  String userName;

    private  String password;

    private  String headImage;

    private  Integer sex;

    private String phone;

    private Date gmtModified;

    private Date gmtCreate;

}
