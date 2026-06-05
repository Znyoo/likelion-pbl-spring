package com.example.likelion_pbl_spring.dto;

import com.example.likelion_pbl_spring.role.Staff;
import java.lang.reflect.Field;

public class StaffResponse {
    private String name;
    private String major;
    private int generation;
    private String part;
    private String position;

    public StaffResponse(String name, String major, int generation, String part, String position) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.position = position;
    }

    public static StaffResponse from(Staff staff) {
        return new StaffResponse(
                getFieldValue(staff, "name"),
                getFieldValue(staff, "major"),
                Integer.parseInt(getFieldValue(staff, "generation")),
                getFieldValue(staff, "part"),
                getFieldValue(staff, "position")
        );
    }

    private static String getFieldValue(Object obj, String fieldName) {
        Class<?> clazz = obj.getClass();
        while (clazz != null) {
            try {
                Field field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                return String.valueOf(field.get(obj));
            } catch (Exception e) {
                clazz = clazz.getSuperclass();
            }
        }
        return "";
    }

    // Getters
    public String getName() { return name; }
    public String getMajor() { return major; }
    public int getGeneration() { return generation; }
    public String getPart() { return part; }
    public String getPosition() { return position; }
}