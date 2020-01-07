package com.example.demo.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.example.demo.security.ApplicationUserPersmission.COURSE_READ;
import static com.example.demo.security.ApplicationUserPersmission.COURSE_WRITE;
import static com.example.demo.security.ApplicationUserPersmission.STUDENT_READ;
import static com.example.demo.security.ApplicationUserPersmission.STUDENT_WRITE;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)),
    ADMINTRANIE(Sets.newHashSet(COURSE_READ, STUDENT_READ));

    private final Set<ApplicationUserPersmission> persmissions;

    ApplicationUserRole(Set<ApplicationUserPersmission> persmissions) {
        this.persmissions = persmissions;
    }

    public Set<ApplicationUserPersmission> getPersmissions() {
        return persmissions;
    }
}
