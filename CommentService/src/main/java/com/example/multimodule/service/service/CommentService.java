package com.example.multimodule.service.service;

import com.example.multimodule.service.model.Comment;

import java.util.List;

public interface CommentService {


    Comment addComment(Comment comment);

    List<Comment> getAllComments();

    Comment getCommentById(Long id);

    List<Comment> addComments(List<Comment> comments);
}
