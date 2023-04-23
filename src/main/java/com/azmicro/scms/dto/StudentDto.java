package com.azmicro.scms.dto;

import com.azmicro.scms.enums.Gender;
import com.azmicro.scms.model.Student;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Builder
@Data
public class StudentDto {

    private Long id;
    private String  code;
    private String lastNameFr;
    private String lastNameAr;
    private String firstNameFr;
    private String firstNameAr;
    private String phone;
    private String address;
    private Gender gender;
    private Date dateOfBirth;
    private String  photo;
    private GradeDto gradeDto;

    public static StudentDto fromEntity(Student student){
        if (student == null) return null;
        return StudentDto.builder()
                .id(student.getId())
                .code(student.getCode())
                .lastNameFr(student.getLastNameFr())
                .lastNameAr(student.getLastNameAr())
                .firstNameFr(student.getFirstNameFr())
                .firstNameAr(student.getFirstNameAr())
                .phone(student.getPhone())
                .address(student.getAddress())
                .gender(student.getGender())
                .dateOfBirth(student.getDateOfBirth())
                .photo(student.getPhoto())
                .gradeDto(GradeDto.fromEntity(student.getGrade()))
                .build();
    }
    public static Student toEntity(StudentDto studentDto){
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setCode(studentDto.getCode());
        student.setLastNameFr(studentDto.getLastNameFr());
        student.setLastNameAr(studentDto.getLastNameAr());
        student.setFirstNameFr(studentDto.getFirstNameFr());
        student.setFirstNameAr(studentDto.getFirstNameAr());
        student.setPhone(studentDto.getPhone());
        student.setAddress(studentDto.getAddress());
        student.setGender(studentDto.getGender());
        student.setDateOfBirth(studentDto.getDateOfBirth());
        student.setPhoto(studentDto.getPhoto());
        student.setGrade(GradeDto.toEntity(studentDto.getGradeDto()));
        return student;
    }
}
