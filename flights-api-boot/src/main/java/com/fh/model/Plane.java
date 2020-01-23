package com.fh.model;

import lombok.Data;

@Data
public class Plane {
private Integer  id;// 机型id
private String  name;// 机型名称
private Integer  type;// 机型类型(比如1代表大型，2代表中型，3代表小型)
}
