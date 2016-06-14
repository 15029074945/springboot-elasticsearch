package cn.forever.elasticsearch.dao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import cn.forever.elasticsearch.domain.Post;

@Repository
public interface PostRepository extends ElasticsearchRepository<Post, String>{
	
}
