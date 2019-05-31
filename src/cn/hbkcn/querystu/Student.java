package cn.hbkcn.querystu;

import cn.hbkcn.bean2excel.Bean;
import cn.hbkcn.bean2excel.Field;

import java.io.Serializable;

/**
 * 2019/2/26 21:26
 */
public class Student implements Serializable, Bean {
    /**
     * 入学年份
     */
    @Field("入学年份")
    private String year;

    /**
     * 姓名
     */
    @Field("姓名")
    private String name;

    /**
     * 性别
     */
    @Field("性别")
    private String sex;

    /**
     * 班级
     */
    @Field("班级")
    private String clazz;

    /**
     * 学号
     */
    @Field("学号")
    private String schoolId;

    /**
     * 身份证号码
     */
    @Field("身份证号码")
    private String id;

    public Student(String year, String name, String schoolId, String sex, String clazz, String id) {
        this.year = year;
        this.name = name;
        this.schoolId = schoolId;
        this.sex = sex;
        this.clazz = clazz;
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return year + " " + name + " " + sex + " " +
                schoolId + " "  + clazz + " "  + id;
    }
}
