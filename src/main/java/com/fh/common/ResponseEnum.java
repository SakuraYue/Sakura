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
    PRODUCT_ID_IS_NOT_EXIST(1007,"商品不存在"),
    PRODUCT_IS_DOWN(1008,"商品已下架"),
    PHONE_IS_NULL(1009,"手机号为空"),
    PHONE_ERROR(1017,"手机号 错误"),
    MEMBERNAME_IS_EXIST(1010,"会员名已注册"),

    PHONE_IS_EXIST(1011,"手机号已注册"),
    CODE_IS_NOT_EXIST(1012,"验证码不存在"),
    CART_IS_NULL(1013,"购物车为空"),
    CART_ALL_STOCK_SHORT(1014,"购物车所有商品都库存不足"),

    INTERFACE_CONFIRM_REPEAT(1015,"不能重复提交"),
    PAY_ORDER_NO_EXIST(1016,"支付订单不存在"),


    ;
    private int code;
    private String msg;

    ResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
