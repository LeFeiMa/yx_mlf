package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Excel(name="id")
    private String id;
    @Excel(name="姓名")
    private String username;
    @Excel(name="手机号")
    private String phone;
    @Excel(name="头像")
    private String headimg;
    @Excel(name="签名")
    private String brief;
    @Excel(name="微信")
    private String wechat;
    @Excel(name="生日",format = "yyyy年MM月dd日",width = 20)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date create_date;
    @Excel(name="状态")
    private Integer status;


}
