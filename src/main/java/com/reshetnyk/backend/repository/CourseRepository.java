package com.reshetnyk.backend.repository;

import com.reshetnyk.backend.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query(value = "CALL find_max_course_price();", nativeQuery = true)
    Integer findMaxCoursePrice();
}