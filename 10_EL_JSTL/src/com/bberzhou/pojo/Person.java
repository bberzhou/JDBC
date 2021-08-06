package com.bberzhou.pojo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: bberzhou@gmail.com
 * @date: 8/5/2021
 * Create By Intellij IDEA
 */
public class Person {
    //  需求-在JSP中使用EL表达式输出Person类中的普通属性，数组属性，List集合属性和map集合属性.
    private String  name;
    private String[] phones;
    private List<String > cities;
    private Map<String ,Object> map;
    private int age;

    public Person() {
    }

    public Person(String name, String[] phones, List<String> cities, Map<String, Object> map) {
        this.name = name;
        this.phones = phones;
        this.cities = cities;
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", phones=" + Arrays.toString(phones) +
                ", cities=" + cities +
                ", map=" + map +
                '}';
    }
}
