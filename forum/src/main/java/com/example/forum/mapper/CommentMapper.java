package com.example.forum.mapper;

import com.example.forum.repository.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<Comment> selectAll();

    Comment selectById(Integer id);

    void insert(Comment saveComment);

    void update(Comment saveComment);

    void delete(Integer id);
}
