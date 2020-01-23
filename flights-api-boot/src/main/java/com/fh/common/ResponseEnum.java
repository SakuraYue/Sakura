package com.fh.common;

public enum ResponseEnum {
  SUCCESS(200,"操作成功"),
  ERROR(1000,"操作失败"),
  CODE_ERROR(1001,"验证码错误"),
  MEMBER_IS_NULL(1002,"会员信息为空"),
  MEMBERNAME_IS_NULL(1003,"会员名称为空"),
  MEMBERPASSWORD_IS_NULL(1004,"会员密码为空"),
  MEMBERNAME_IS_ERROR(1005,"会员名称错误"),
  MEMBERPASSWORD_IS_ERROR(1006,"会员密码错误"),
  PRODUCT_IS_NOT_EXIST(1007,"商品不存在"),
  PRODUCT_IS_DOWN(1008,"商品已下架"),
  PHONE_IS_NULL(1009,"手机号为空"),
  MEMBERNAME_IS_EXIST(1010,"会员名已注册"),

  TOKEN_IS_NULL(519,"请求头中token信息为空"),
  TOKEN_IS_SHORT_OF(520,"请求头中token信息不完整"),
  TOKEN_IS_CHANGED(521,"请求头中token信息被篡改"),
  TOKEN_IS_EXPIRED(522,"登录信息已过期"),
  TOKEN_VERIFY_ERROR(523,"验证token失败"),

  PHONE_IS_EXIST(1011,"手机号已注册"),
  CODE_IS_NOT_EXIST(1012,"验证码不存在"),
  CART_IS_NULL(1013,"购物车为空"),
  CART_ALL_IS_SHORT(1014,"全部商品库存不足"),
  PAY_ORDER_NO_EXIST(1015,"支付订单不存在"),
  PJONE_IS_EXIST(1016,"手机号不合法"),
  CODE_IS_NULL(1017,"手机号为空"),
  MEMBERPHONE_IS_ERROR(1018,"手机号错误"),
  PRODUCT_ID_IS_NULL(524,"商品id为空"),
  PRODUCT_IS_SOLD_OUT(525,"商品已下架"),
  CART_IS_NOT_EXISTED(526,"购物车不存在"),
  CART_PRODUCT_IS_NOT_EXISTED(527,"商品不存在"),
  CART_ALL_CHECKED_PRODUCT_UNDER_STOCK(528,"所选商品库存都不足"),
  REPETITIVE_OPERATION(529,"重复操作"),
  PAY_LOG_IS_NULL(530,"订单日志为空"),
  OUT_TRADE_NO_INVALID(531,"无效订单"),
  TICKET_IS_NULL(532,"机票信息为空"),

  ;
  private int status;
  private String msg;

  ResponseEnum(int code, String msg) {
    this.status = code;
    this.msg = msg;
  }

  public int getCode() {
    return status;
  }

  public String getMsg() {
    return msg;
  }
}
