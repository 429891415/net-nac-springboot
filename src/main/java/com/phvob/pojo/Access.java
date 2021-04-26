package com.phvob.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author phvob
 * @email hbuphvob@163.com
 * @create 2021-04-25 19:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Access {
    String name;
    String subject;
    String object;
    AccessControl accessControl;
}
