package day1PreparedStatement.entity;

import com.mysql.cj.jdbc.Blob;

import java.util.Date;

/**
 * @description: 针对Customer表的查询操作，ORM（编程思想 object relational mapping）
 *               一个数据表对应一个Java类，表中的一条记录对应Java类中的一个对象，表中的一个字段对应Java类的一个属性
 * @author: bberzhou@gmail.com
 * @date: 6/27/2021
 * Create By Intellij IDEA
 */
public class Customer {
    private int id;
    private String name;
    private String email;
    private Date birth;
    private Blob blob;
    public Customer(){

    }
    public Customer(int id, String name, String email, Date birth){
        this.id= id;
        this.name = name;
        this.email = email;
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
