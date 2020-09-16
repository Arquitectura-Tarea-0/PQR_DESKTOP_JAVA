package com.PQR;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.PQR.GUI.ArquitecturaUI;
import com.PQR.controller.UsuarioControl;


@SpringBootApplication
public class PqrApplication implements CommandLineRunner {

	private final static Logger logger = LoggerFactory.getLogger(PqrApplication.class);
	
	
	public static void main(String[] args) {
		UsuarioControl u= new UsuarioControl();		
		SpringApplication.run(PqrApplication.class, args);
		
		System.setProperty("java.awt.headless", "false");
		
		ArquitecturaUI a = new ArquitecturaUI();
                a.setVisible(true);
		
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
