package com.fh.util;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IdUtil {

    public static String createId() {
        DateTimeFormatter yyyyMMddHHmm = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        return LocalDateTime.now().format(yyyyMMddHHmm)+ IdWorker.getId();
    }

    public static Long createIdWithoutTimestamp() {
        return IdWorker.getId();
    }
}
