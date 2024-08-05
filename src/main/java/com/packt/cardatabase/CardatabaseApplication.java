package com.packt.cardatabase;

import com.packt.cardatabase.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

	private final CarRepository carRepository;
	private final OwnerRepository ownerRepository;
	private final AppUserRepository appUserRepository;

	public CardatabaseApplication(CarRepository carRepository, OwnerRepository ownerRepository, AppUserRepository appUserRepository) {
		this.carRepository = carRepository;
		this.ownerRepository = ownerRepository;
        this.appUserRepository = appUserRepository;
    }
	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);
		logger.info("Application started");
	}

	@Override
	public void run(String ... args) throws Exception {
		Owner owner1 = new Owner("John", "Johnson");
		Owner owner2 = new Owner("Mary", "Robinson");
		ownerRepository.saveAll(List.of(owner1, owner2));

		carRepository.save(new Car(
				"Ford",
				"Mustang",
				"Red",
				"ADF-1121",
				2023,
				59000,
				owner1
		));
		carRepository.save(new Car(
				"Nissan",
				"Leaf",
				"White",
				"SSJ-3002",
				2020,
				29000,
				owner2
		));
		carRepository.save(new Car(
				"Toyota",
				"Prius",
				"Silver",
				"KKO-0212",
				2022,
				39000,
				owner2
		));

		for (Car car: carRepository.findAll()) {
			logger.info("brand: {}, model: {}", car.getBrand(), car.getModel());
		}

		appUserRepository.save(new AppUser(
				"user",
				"$2a$12$yrXLRRcQtJmTPyFXvfV4WOfz0475GkWVgMR.P/b9wHPsDiToirZaG",
				"USER"));
		appUserRepository.save(new AppUser(
				"admin",
				"$2a$12$/lvqhqVVc04uaG2K.CGhLetKTTLUaErARuyekcq3pXD1NlaAF5gii",
				"ADMIN"));
	}

}
