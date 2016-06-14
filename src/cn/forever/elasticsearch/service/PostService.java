package cn.forever.elasticsearch.service;

import java.util.LinkedList;
import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.forever.elasticsearch.dao.PostRepository;
import cn.forever.elasticsearch.domain.Post;

@Service
public class PostService {
	@Autowired
	PostRepository postRepository;

	public void init() {
		
		List<Post> post = new LinkedList<>(); 
		for (int i = 0; i < 10000000; i++) {
			Post p = new Post();
			p.setId(String.valueOf(i + 1));
			
			if(i % 3100 == 0){
				p.setTitle("meme!"+i+"asdsad");
			}else{
				p.setTitle(String.valueOf(i));
			}
			post.add(p);
			
			
			if(i % 10000 == 0 || i == 10000000 - 1){
				postRepository.save(post);
				
				post = new LinkedList<>();
			}
		}
	}

	public Iterable<Post> query(String name) {
		QueryBuilder builder = QueryBuilders.fuzzyLikeThisQuery("title").likeText("mem");
		return postRepository.search(builder);
	}

	public Iterable<Post> findAll() {
		return postRepository.findAll();
	}
}
