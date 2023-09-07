package com.example.demo.mapper;

import com.example.demo.dto.StudentDto;
import com.example.demo.model.Student;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface StudentMapper extends EntityMapper<StudentDto, Student> {


    @Override
    default Student toEntity(StudentDto dto) {
        return Student.builder()
                .id(dto.getId())
                .name(dto.getName())
                .age(dto.getAge())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .build();
    }

    @Override
    default StudentDto toDto(Student entity) {
        return StudentDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .age(entity.getAge())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .address(entity.getAddress())
                .build();
    }

    @Override
    default List<Student> toEntity(List<StudentDto> dtoList) {
        if(dtoList == null) {
            return List.of();
        }
        List<Student> studentList = new java.util.ArrayList<>(dtoList.size());
        for (StudentDto studentDto : dtoList) {
            studentList.add(toEntity(studentDto));
        }
        return studentList;
    }

    @Override
    default List<StudentDto> toDto(List<Student> entityList) {
        if(entityList == null) {
            return List.of();
        }
        List<StudentDto> studentDtoList = new java.util.ArrayList<>(entityList.size());
        for (Student student : entityList) {
            studentDtoList.add(toDto(student));
        }
        return studentDtoList;
    }

    @Override
    default Set<StudentDto> toDto(Set<Student> entityList) {
        if(entityList == null) {
            return Set.of();
        }
        Set<StudentDto> studentDtoList = new java.util.HashSet<>(entityList.size());
        for (Student student : entityList) {
            studentDtoList.add(toDto(student));
        }
        return studentDtoList;
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)Student partialUpdate(StudentDto studentDto, @MappingTarget Student student);
}