package com.example.likelion_pbl_spring.dto;

import com.example.likelion_pbl_spring.role.Lion;
import java.lang.reflect.Field;

public class LionResponse {
    private String name;
    private String major;
    private int generation;
    private String part;
    private String studentId;

    public LionResponse(String name, String major, int generation, String part, String studentId) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.studentId = studentId;
    }

    public static LionResponse from(Lion lion) {
        return new LionResponse(
                getFieldValue(lion, "name"),
                getFieldValue(lion, "major"),
                Integer.parseInt(getFieldValue(lion, "generation")),
                getFieldValue(lion, "part"),
                getFieldValue(lion, "studentId")
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
    public String getStudentId() { return studentId; }
}