package com.spring.mmm.domain.mukgroups.controller.request;

import lombok.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class MukgroupMBTICalcRequest {
    private List<Long> mukbos;
}
