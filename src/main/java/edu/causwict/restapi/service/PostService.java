package edu.causwict.restapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.causwict.restapi.entity.Post;
import edu.causwict.restapi.repository.InMemoryPostRepository;

import java.util.List;

@Service
public class PostService {

	private final InMemoryPostRepository postRepository;

	public PostService(InMemoryPostRepository postRepository) { // 생성자 주입
		this.postRepository = postRepository;
	}

    // 제목 유효성 검사
    private void validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty.");
        }
        // 제목 길이에 대한 조건 : 30자 이하
        if (title.length() > 30) {
            throw new IllegalArgumentException("Title cannot exceed 30 characters.");
        }
    }
    // 제목 중복 검사
    private void validateDuplicateTitle(String title) {
        postRepository.findByTitle(title).ifPresent(p -> {
            throw new IllegalArgumentException("A post with this title already exists.");
        });
    }

    // 제목의 키워드로 게시글을 검색
    public List<Post> searchByTitle(String keyword) {
        return postRepository.findByTitleContaining(keyword);
    }

	public Post create(String title, String content) {
        validateTitle(title);
        validateDuplicateTitle(title);
		Post post = new Post(null, title, content);
		return postRepository.save(post);
	}
}