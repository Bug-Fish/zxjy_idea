package com.zjy.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZjyException extends RuntimeException {
    private Integer code;
    private String msg;
}
