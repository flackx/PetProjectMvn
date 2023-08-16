package com.example.multimodule.service.controller;

import com.example.multimodule.service.model.Comment;
import com.example.multimodule.service.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @Operation(summary = "Create a new comment", description = "Create a new comment with the given title, due date and status", tags = {"Comments", "Create"})
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Comment created"), @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found"), @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @PostMapping("/add")
    public Comment addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }

    @Operation(summary = "Get all comments", description = "Get all comments", tags = {"Comments", "Get"})
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Comments found"), @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found"), @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @GetMapping("/get/all")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }
    @Operation(summary = "Get comment by id", description = "Get comment by id", tags = {"Comments", "Get"})
    @ApiResponses({@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Comment found"), @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Not found"), @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal Server Error")})
    @GetMapping("/get/{id}")
    public Comment getCommentById(@PathVariable Long id) {
        return commentService.getCommentById(id);
    }

    @PostMapping("/add/bulk")
    public List<Comment> addComments(@RequestBody List<Comment> comments) {
        return commentService.addComments(comments);
    }


}
