package com.example.forum.mapper;

import com.example.forum.controller.form.ReportForm;
import com.example.forum.repository.entity.Report;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReportMapper {
    @Select("SELECT * FROM report ORDER BY updated_date DESC")
    List<Report> selectAll();

    @Select("SELECT * FROM report WHERE updated_date BETWEEN #{startDate} AND #{endDate} ORDER BY updated_date DESC")
    List<Report> selectByUpdatedDate();

    @Select("SELECT * FROM report WHERE id = #{id}")
    List<Report> selectById(Integer id);

    @Insert("INSERT INTO report (id, content, created_date, updated_date) VALUES(#{id}, #{content}, #{created_date}, #{updated_date})")
    int insert(ReportForm reqReport);

    
}
