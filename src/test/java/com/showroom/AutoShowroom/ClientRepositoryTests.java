package com.showroom.AutoShowroom;

import com.showroom.AutoShowroom.models.Car;
import com.showroom.AutoShowroom.models.Client;
import com.showroom.AutoShowroom.repo.ClientRepository;
import com.showroom.AutoShowroom.repo.CarRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ClientRepositoryTests {
	@Autowired private ClientRepository repo;
	@Autowired private CarRepository carRepository;

	@Test
	public void testAddNew() {
		Client client = new Client();
		client.setEmail("RomanFomic@mail.com");
		client.setPassword("qwerty123");
		client.setFirstName("Roman");
		client.setLastName("Fomichev");
		client.setDelivery(true);
		client.setAuto_model("mini-cooper");

		Client savedClient = repo.save(client);

		Assertions.assertThat(savedClient).isNotNull();
		Assertions.assertThat(savedClient.getId()).isGreaterThan(0);
	}

	@Test
	public void testListsAll() {
		Iterable<Client> clients = repo.findAll();
		Assertions.assertThat(clients).hasSizeGreaterThan(0);

		for (Client client : clients) {
			System.out.println(client);
		}
	}

	@Test
	public void testUpdate() {
		Integer clientId = 17;
		Optional<Client> optionalClient = repo.findById(clientId);
		Client client = optionalClient.get();
		client.setPassword("123hi321");
		repo.save(client);

		Client updatedClient = repo.findById(clientId).get();
		Assertions.assertThat(updatedClient.getPassword()).isEqualTo("123hi321");
	}

	@Test
	public void testGet() {
		Integer clientId = 1;
		Optional<Client> optionalClient = repo.findById(clientId);
		Assertions.assertThat(optionalClient).isPresent();
		System.out.println(optionalClient.get());
	}

	@Test
	public void testDelete() {
		Integer clientId = 2;
		repo.deleteById(clientId);

		Optional<Client> optionalClient = repo.findById(clientId);
		Assertions.assertThat(optionalClient).isNotPresent();
	}

	@Test
	public void testAddNews() {
		Car car = new Car();
		car.setTitle("myTitle");
		car.setAnons("myAnons");
		car.setFull_text("myFullText");

		Car savedCar = carRepository.save(car);

		Assertions.assertThat(savedCar).isNotNull();
		Assertions.assertThat(savedCar.getId()).isGreaterThan(0);
	}

	@Test
	public void testDeleteNews() {
		int newsId = 352;
		carRepository.deleteById((long) newsId);

		Optional<Car> optionalCar = carRepository.findById((long) newsId);
		Assertions.assertThat(optionalCar).isNotPresent();
	}

	@Test
	public void testNewsUpdate() {
		int newsId = 252;
		Optional<Car> optionalCar = carRepository.findById((long) newsId);
		Car car = optionalCar.get();
		car.setTitle("Changed Title");
		carRepository.save(car);

		Car updatedCar = carRepository.findById((long) newsId).get();
		Assertions.assertThat(updatedCar.getTitle()).isEqualTo("Changed Title");
	}
}
