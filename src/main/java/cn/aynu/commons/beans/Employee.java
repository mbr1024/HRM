package cn.aynu.commons.beans;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/3/22
 */
public class Employee {
    private Integer id;
    private String name;
    private String card_Id;
    private String address;
    private String post_Code;
    private String tel;
    private String phone;
    private String QQ_Num;
    private String EMail;
    private Integer sex;
    private String party;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String race;
    private String education;
    private String speciality;
    private String hobby;
    private String remark;
    private Date create_Date;
    private Dept dept;
    private Job job;

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCard_Id() {
        return card_Id;
    }

    public void setCard_Id(String card_Id) {
        this.card_Id = card_Id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPost_Code() {
        return post_Code;
    }

    public void setPost_Code(String post_Code) {
        this.post_Code = post_Code;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQQ_Num() {
        return QQ_Num;
    }

    public void setQQ_Num(String QQ_Num) {
        this.QQ_Num = QQ_Num;
    }

    public String getEMail() {
        return EMail;
    }

    public void setEMail(String EMail) {
        this.EMail = EMail;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreate_Date() {
        return create_Date;
    }

    public void setCreate_Date(Date create_Date) {
        this.create_Date = create_Date;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", card_Id='" + card_Id + '\'' +
                ", address='" + address + '\'' +
                ", post_Code='" + post_Code + '\'' +
                ", tel='" + tel + '\'' +
                ", phone='" + phone + '\'' +
                ", QQ_Num='" + QQ_Num + '\'' +
                ", EMail='" + EMail + '\'' +
                ", sex=" + sex +
                ", party='" + party + '\'' +
                ", birthday='" + birthday + '\'' +
                ", race='" + race + '\'' +
                ", education='" + education + '\'' +
                ", speciality='" + speciality + '\'' +
                ", hobby='" + hobby + '\'' +
                ", remark='" + remark + '\'' +
                ", create_Date=" + create_Date +
                ", dept=" + dept +
                ", job=" + job +
                '}';
    }
}
