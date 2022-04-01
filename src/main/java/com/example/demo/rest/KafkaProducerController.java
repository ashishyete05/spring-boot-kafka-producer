package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;

@RestController
@RequestMapping("kafka")
public class KafkaProducerController {

	@Autowired
	KafkaTemplate<String, User> kafkaTemplate;

	public static final String TOPIC = "test_topicWXYZ";

	@GetMapping("/publish/bulk")
	public String publishMessage() {

		User u = null;
		for(int i=0;i<1000;i++) {
			u= new User(1+i,"Ashish-"+i, "Description-"+i);
		kafkaTemplate.send(TOPIC,u);
		}
		return "Success";
	}

	@PostMapping("/publish")
	public String publishObject(@RequestBody User user) {

		kafkaTemplate.send(TOPIC, user);
		return "Message \n " + user + " \n Successfully Published";
	}
}
