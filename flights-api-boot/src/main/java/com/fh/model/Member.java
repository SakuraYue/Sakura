package com.fh.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Member {
private Integer id;// 会员id
private String username;// 用户名
private String usrpassword;// 密码
private String phonenumber;// 手机号
private Integer sex ;//性别 1代表男 2代表女
private String email;// 邮箱
 @DateTimeFormat(pattern = "yyyy-MM-dd")
private Date registertime;// 注册时间
 private String uuid;
 private String carid;
}
