package com.xoriant.LearningManagementSystem_v3.course.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourseRequestDto {
    private String name;
    private String description;
    private Long instructorId;
  private List<Long> studentIds;
}
