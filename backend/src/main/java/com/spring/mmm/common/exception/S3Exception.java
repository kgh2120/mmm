package com.spring.mmm.common.exception;

public class S3Exception extends CustomException{
    public S3Exception(ErrorCode errorCode) {
        super(errorCode);
    }
}
