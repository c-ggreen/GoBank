package com.chadwick.GoBankDB;

import com.chadwick.GoBankDB.Entity.Users;
import com.chadwick.GoBankDB.Repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.StreamSupport;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestInstance(Lifecycle.PER_CLASS)
class GoBankDbApplicationTests {
	@Autowired
	private UserRepository userRepository;
	@BeforeAll
	void insertDataToDatabase(){
		try{
			String stringTooLong = Files.readString(Path.of("src/test/java/com/chadwick/GoBankDB/data.txt"));
			JsonArray array = JsonParser.parseString(stringTooLong).getAsJsonArray();
			for (int i = 0; i < array.size(); i++) {
				String obj = array.get(i).toString();
				ObjectMapper mapper = new ObjectMapper();
				Users user = mapper.readValue(obj, Users.class);
				userRepository.save(user);
			}
		}catch (Exception e){
			throw new Error(e);
		}
	}
	@Test
	void itShouldCheckThatAll10UsersWereSaved(){
		try {
			Iterable<Users> usersIterable = userRepository.findAll();
			ArrayList<Users> usersArrayList = new ArrayList<>((Collection) usersIterable);
			assertThat(usersArrayList.size()).isEqualTo(10);
			assertThat(usersArrayList.get(0).getEmail()).isNotNull();
		}catch (Exception e){
			throw new Error(e);
		}
	}

}
