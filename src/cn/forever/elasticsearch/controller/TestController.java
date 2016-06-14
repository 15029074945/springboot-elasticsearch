package cn.forever.elasticsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.forever.elasticsearch.domain.Post;
import cn.forever.elasticsearch.service.PostService;

@RestController
public class TestController {

	@Autowired
	private PostService postService;

	@RequestMapping("init")
	public String index() {

		postService.init();

		return "asdsadsadadsad";
	}

	@RequestMapping(value = "search", method = RequestMethod.POST)
	public String search() {
		for (Post p : postService.query("meme")) {
			System.out.println(p);
		}
		return "zxcxzckxjzjasd";
	}

}
