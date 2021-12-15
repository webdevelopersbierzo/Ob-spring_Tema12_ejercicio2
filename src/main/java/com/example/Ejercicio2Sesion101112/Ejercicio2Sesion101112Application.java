package com.example.Ejercicio2Sesion101112;

import com.example.Ejercicio2Sesion101112.entity.Laptop;
import com.example.Ejercicio2Sesion101112.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class Ejercicio2Sesion101112Application {

	public static void main(String[] args) {

		ApplicationContext context =  SpringApplication.run(Ejercicio2Sesion101112Application.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);

		//Creamos un laptop para verificaciones

		Laptop laptop1 = new Laptop(null,"Toshiba", "345hg56mx",8,"windows 11");
		Laptop laptop2 = new Laptop(null,"Sangung", "458sm45",8,"windows 11");

		// Los guardamos

		repository.save(laptop1);
		repository.save(laptop2);

		// listamos

		System.out.println("los laptops almacenados son : " + repository.findAll().size());



	}

}
