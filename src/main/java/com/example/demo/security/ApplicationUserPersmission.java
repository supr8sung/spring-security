package com.example.demo.security;

public enum ApplicationUserPersmission {

    STUDENT_READ("student:read"),
    STUDENT_WRITE("student-write"),
    COURSE_READ("course-read"),
    COURSE_WRITE("course-write");

    private final String permsission;

    ApplicationUserPersmission(String permsission) {
        this.permsission = permsission;
    }

    public String getPermsission() {
        return permsission;
    }
}
