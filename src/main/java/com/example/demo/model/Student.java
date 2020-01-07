package com.example.demo.model;

public class Student {
    private final Integer id;
    private final String name;


    public Student(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private Student(Builder builder) {
        id = builder.id;
        name = builder.name;
    }


    public static final class Builder {
        private final Integer id;
        private final String name;

        public Builder(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Student build() {
            return new Student(this);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
