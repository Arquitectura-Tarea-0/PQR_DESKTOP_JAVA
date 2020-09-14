package com.visitas;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.visitas.controller.UsuarioControl;


@SpringBootApplication
public class PqrApplication implements CommandLineRunner {

	private final static Logger logger = LoggerFactory.getLogger(PqrApplication.class);
	
	
	public static void main(String[] args) {
		UsuarioControl u= new UsuarioControl();
		logger.info("Mensaje A");
		SpringApplication.run(PqrApplication.class, args);
		u.login("","");
		u.getAllPQR();
		logger.info("Mensaje B");
		//System.setProperty("java.awt.headless", "false");
		//JOptionPane.showMessageDialog(null, "Javadesde0.com");
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		logger.info("Mensaje C");
	}
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}
