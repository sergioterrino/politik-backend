package com.stb.politik.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.stb.politik.dto.PostDTO;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getPosts() {
        return this.postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Override
    public void savePost(Post post) {
        this.postRepository.save(post);
    }

    @Override
    public List<Post> getPostsByUserId(Long userId) {
        return this.postRepository.findByUser_Id(userId);
    }

    @Override
    public Post getPostById(Long postId) {
        return this.postRepository.findById(postId).orElse(null);
    }

    @Override
    public void deletePost(Long postId) {
        this.postRepository.deleteById(postId);
    }

    @Override
    public void updatePost(Long postId, PostDTO postDTO){
        Post post = this.postRepository.findById(postId).orElse(null);
        if(post != null){
            post.setText(postDTO.getText()); // de momento solo se actualiza el texto
            this.postRepository.save(post);
        }
    }
    
}
