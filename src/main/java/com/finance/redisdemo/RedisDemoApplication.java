package com.finance.redisdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.Serializable;
import java.util.List;

@SpringBootApplication
public class RedisDemoApplication implements CommandLineRunner {

	/*@Autowired
	private RedisTemplate<String,Serializable> redisTemplate;*/

	@Autowired
	private RedisTemplate<String,Object> redisTemplate;

	public static void main(String[] args) {
		SpringApplication.run(RedisDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ValueOperations<String, Object> ops = redisTemplate.opsForValue();
		String key = "spring.boot.redis.test";
		if (!redisTemplate.hasKey(key)) {
			ops.set(key, "foo");
		}
		System.out.println("Found key " + key + ", value=" + ops.get(key));
		redisTemplate.opsForList().leftPush("listkey2","listvalue21");
		redisTemplate.opsForList().leftPush("listkey2","listvalue22");
		redisTemplate.opsForList().leftPush("listkey2","listvalue23");

		List<String> stringList = (List<String>) redisTemplate.opsForValue().get("listkey1");
		for(String tempString : stringList){
			System.out.println(tempString);
		}

		com.fasterxml.jackson.databind.ObjectMapper objectMapper = new ObjectMapper();
	}
}
