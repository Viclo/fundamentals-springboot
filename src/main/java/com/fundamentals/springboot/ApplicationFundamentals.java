package com.fundamentals.springboot;

import com.fundamentals.springboot.bean.MyBean;
import com.fundamentals.springboot.bean.MyBeanWithDependency;
import com.fundamentals.springboot.bean.MyBeanWithProperties;
import com.fundamentals.springboot.component.ComponentDependency;
import com.fundamentals.springboot.entity.User;
import com.fundamentals.springboot.pojo.UserPojo;
import com.fundamentals.springboot.repository.UserRepository;
import com.fundamentals.springboot.service.UserService;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;

@SpringBootApplication
@ComponentScan("com.fundamentals.springboot.*")
public class ApplicationFundamentals implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(ApplicationFundamentals.class);
	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserPojo userPojo;
	private UserRepository userRepository;
	private UserService userService;

	public ApplicationFundamentals(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository, UserService userService) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userPojo = userPojo;
		this.userRepository = userRepository;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApplicationFundamentals.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		DITests();
		saveUsersInDB();
		getInformationJPQLFromUser();
		saveWithTransactionalError();
	}

	private void saveWithTransactionalError() {
		User user11 = new User("TransactionalTest11", "test11@gmail.com", LocalDate.now());
		User user12 = new User("TransactionalTest12", "test12@gmail.com", LocalDate.now());
		User user13 = new User("TransactionalTest13", "test13@gmail.com", LocalDate.now());
		User user14 = new User("TransactionalTest14", "test14@gmail.com", LocalDate.now());
		User user15 = new User("TransactionalTest15", "test15@gmail.com", LocalDate.now());

		List<User> listUsers = Arrays.asList(user11, user12, user13, user14, user15);

		try {
			userService.saveTransactional(listUsers);
		}catch (Exception e) {
			LOGGER.info("There is a error saving users." + e);
		}

		userService.getAllUsers()
				.forEach(user -> LOGGER.info("User with Transactional method: " + user));
	}

	private void getInformationJPQLFromUser() {
		LOGGER.info("Find user with findByUserEmail method: " +
				userRepository.findByUserEmail("fabiana@gmail.com")
						.orElseThrow(()-> new RuntimeException("Alert: Doesn't exist user.")));

		userRepository.findAndSort("E", Sort.by("id").ascending())
				.forEach(user -> LOGGER.info("User found with findAndSort method." + user));

		userRepository.findByName("Calvex")
				.forEach(user -> LOGGER.info("User found with Query method. " + user));

		LOGGER.info("User found with Query method and manage of nulls with Optional. " + userRepository.findByEmailAndName("calvex@gmail.com", "Calvex"));

		userRepository.findByNameLike("%E%")
				.forEach(user -> LOGGER.info("User found findByNameLike method. " + user));

		userRepository.findByNameOrEmail(null, "justino@gmail.com")
				.forEach(user -> LOGGER.info("User found with OR in findByNameOrEmail method. " + user));

		userRepository.findByBirthDateBetween(LocalDate.of(1970, 1, 1), LocalDate.of(2023, 1, 1))
				.forEach(user -> LOGGER.info("Users found between dates. " + user));

		userRepository.findByNameContainingOrderByIdDesc("Elvis")
				.forEach(user -> LOGGER.info("Users found and sorted with findByNameLikeOrderByDesc. " + user));

		LOGGER.info("User found since Named parameter method. " + userRepository.getAllByBirthDateAndEmail(LocalDate.of(2000, 2, 20), "fabiana@gmail.com")
				.orElseThrow(() -> new RuntimeException("Doesn't exist user using Named parameter method. ")));
	}

	private void saveUsersInDB() {
		User user1 = new User("Elvis", "elvis@gmail.com", LocalDate.of(1999, 1, 10));
		User user2 = new User("Fabiana", "fabiana@gmail.com", LocalDate.of(2000, 2, 20));
		User user3 = new User("Calvex", "calvex@gmail.com", LocalDate.of(1980, 3, 15));
		User user4 = new User("Justino", "justino@gmail.com", LocalDate.of(1950, 4, 23));
		User user5 = new User("Elviscocho", "elviscocho@gmail.com", LocalDate.of(1888, 8, 18));
		User user6 = new User("Calvex", "calvex1@gmail.com", LocalDate.of(1981, 4, 16));
		List<User> userList = Arrays.asList(user1, user2, user3, user4, user5, user6);
		userRepository.saveAll(userList);
	}

	private void DITests() {
		componentDependency.toGreet();
		myBean.print();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.useValueProperties());
		System.out.println(userPojo.getEmail());
		LOGGER.error("This is a Log error");
	}
}
