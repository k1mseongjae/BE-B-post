package edu.causwict.restapi.service;

import org.springframework.stereotype.Service;

import edu.causwict.restapi.entity.Post;
import edu.causwict.restapi.repository.InMemoryPostRepository;

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
    }

	public Post create(String title, String content) {
        validateTitle(title);
		Post post = new Post(null, title, content);
		return postRepository.save(post);
	}
}