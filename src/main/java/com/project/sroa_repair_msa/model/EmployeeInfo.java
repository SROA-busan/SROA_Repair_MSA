package com.project.sroa_repair_msa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EmployeeInfo {
    @Id
    public Long empNum;
    public String name;

}
