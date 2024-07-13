package com.example.simpleboard.reply.service;

import com.example.simpleboard.post.db.PostEntity;
import com.example.simpleboard.post.repository.PostRepository;
import com.example.simpleboard.reply.db.ReplyEntity;
import com.example.simpleboard.reply.model.ReplyRequest;
import com.example.simpleboard.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;

    public ReplyEntity create(@RequestBody ReplyRequest replyRequest) {
        PostEntity postEntity = postRepository.findById(replyRequest.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID: " + replyRequest.getPostId()));

        ReplyEntity entity = ReplyEntity.builder()
                .post(postEntity)
                .userName(replyRequest.getUserName())
                .password(replyRequest.getPassword())
                .status("REGISTERED")
                .title(replyRequest.getTitle())
                .content(replyRequest.getContent())
                .repliedAt(LocalDateTime.now())
                .build();

        return replyRepository.save(entity);

    }

    public List<ReplyEntity> findAllByPostId(Long postId) {
        return replyRepository.findAllByPostIdAndStatusOrderByIdDesc(postId, "REGISTERED");
    }
}
