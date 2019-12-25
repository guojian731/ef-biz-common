package com.eastfair.platform.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author guojian
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnumVO {

    private String code;

    private String value;

    private String default1;

    private String default2;

}
