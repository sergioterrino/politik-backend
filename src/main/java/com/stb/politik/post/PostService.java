package com.stb.politik.post;

import java.util.List;

import com.stb.politik.dto.PostDTO;

public interface PostService {
    List<Post> getPosts();
    void savePost(Post post);
    List<Post> getPostsByUserId(Long userId);
    Post getPostById(Long postId);
    void deletePost(Long postId);
    void updatePost(Long postId, PostDTO postDTO);
    
}
