package com.example.greendaodemo.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class StudentBeanTable {
    @Id
    private String sId;
    private String name;
    private int age;
    private String  gender;
    @Generated(hash = 965995267)
    public StudentBeanTable(String sId, String name, int age, String gender) {
        this.sId = sId;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    @Generated(hash = 898805197)
    public StudentBeanTable() {
    }
    public String getSId() {
        return this.sId;
    }
    public void setSId(String sId) {
        this.sId = sId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

}
