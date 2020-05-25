package com.training.vos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = false)
public class UserInfo {
    private Integer usrId;
    private String usrName;
    private String usrEmail;
}
