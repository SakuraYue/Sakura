package com.fh.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Ticket {
private Integer id;// 机票id
private Integer flightId ;// 航班id
private Integer type ;// 机票类型 1代表经济舱，2代表头等舱
private Long totalCount;//  票数
private BigDecimal price ;// 票价
}
