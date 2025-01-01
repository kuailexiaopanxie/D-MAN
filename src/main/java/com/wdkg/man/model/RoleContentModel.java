package com.wdkg.man.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @Author 樊凯
 * @Date 2024/10/15
 **/
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleContentModel {

    String role;

    String content;
}
