package com.example.forum.service;

import com.example.forum.controller.form.CommentForm;
import com.example.forum.mapper.CommentMapper;
import com.example.forum.repository.CommentRepository;
import com.example.forum.repository.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentMapper commentMapper;

    /*
     * レコード全件取得処理
     */
    public List<CommentForm> findAllComment() {
//        List<Comment> results = commentRepository.findAll();
        List<Comment> results = commentMapper.selectAll();
        List<CommentForm> comments = setCommentForm(results);
        return comments;
    }

    /*
     * DBから取得したデータをFormに設定
     */
    private List<CommentForm> setCommentForm(List<Comment> results) {
        List<CommentForm> comments = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            CommentForm Comment = new CommentForm();
            Comment result = results.get(i);
            Comment.setId(result.getId());
            Comment.setComment(result.getComment());
            Comment.setContent_id(result.getContent_id());
            comments.add(Comment);
        }
        return comments;
    }

    /*
     * レコード追加
     */
    public void saveComment(CommentForm reqComment) {
        Comment saveComment = setCommentEntity(reqComment);
//        commentRepository.save(saveComment);

        if (saveComment.getId() == 0){
            commentMapper.insert(saveComment);
        }else {
            commentMapper.update(saveComment);
        }
    }

    /*
     * リクエストから取得した情報をEntityに設定
     */
    private Comment setCommentEntity(CommentForm reqComment) {
        Comment comment = new Comment();
        comment.setId(reqComment.getId());
        comment.setComment(reqComment.getComment());
        comment.setContent_id(reqComment.getContent_id());
        comment.setUpdated_date(reqComment.getUpdated_date());
        return comment;
    }

    /*
     * レコード取得処理
     */
    public CommentForm findComment(Integer id) {
        List<Comment> results = new ArrayList<>();
//        results.add((Comment) commentRepository.findById(id).orElse(null));
        results.add((Comment) commentMapper.selectById(id));
        List<CommentForm> comments = setCommentForm(results);
        return comments.get(0);
    }

    /*
     * レコード削除
     */
    public void deleteComment(Integer id) {
//        commentRepository.deleteById(id);
        commentMapper.delete(id);
    }
}
