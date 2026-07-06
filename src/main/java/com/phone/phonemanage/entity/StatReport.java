package com.phone.phonemanage.entity;

import lombok.Data;

@Data
public class StatReport {
    private Long id;
    private String name;
    private Integer count;
    private Integer total;
    private Double percentage;
}
