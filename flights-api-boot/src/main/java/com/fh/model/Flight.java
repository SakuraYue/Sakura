package com.fh.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Flight{
 private Integer id;// 航班id
 private String name;//  航班名称
 private Integer typeId;//  机型id
 @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
 private Date startIime;//  起飞时间
 @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
 private Date endTime;//  到大时间
 private Integer startCityId;//  出发城市id
 private Integer startAirportId;//  出发机场id
 private Integer startTerminalId;//  出发航站楼id
 private Integer endCityId;//  到达城市id
 private Integer endAirportId;//  到达机场id
 private Integer endTerminalId;//  到达航站楼id
}
