package edu.causwict.restapi.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import edu.causwict.restapi.entity.Post;

@Repository
public class InMemoryPostRepository {

	private final Map<Long, Post> store = new ConcurrentHashMap<>();
	private final AtomicLong sequence = new AtomicLong(0);

	public Post save(Post post) {
		if (post.getId() == null) {
			post.setId(sequence.incrementAndGet());
		}
		store.put(post.getId(), post);
		return post;
	}

    // ID로 Post를 찾는 메서드 추가
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    // Post를 업데이트하는 메서드 추가
    public Post update(Long id, Post post) {
        store.put(id, post);
        return post;
    }

	public List<Post> findAll() {
		return new ArrayList<>(store.values());
	}

}
