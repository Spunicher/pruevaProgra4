package com.alquiler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.alquiler.entidades.Rol;
import com.alquiler.entidades.Usuario;
import com.alquiler.proyecto.interfaces.RolRepo;
import com.alquiler.proyecto.interfaces.UsuarioRepo;

@SpringBootApplication
public class Alquilerprogramacion4Application implements CommandLineRunner{
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(Alquilerprogramacion4Application.class, args);
		
	}

//	@Override
	//public void run(String... args) throws Exception {
//String password="12345";
		
	//	for (int i = 1; i < 3; i++) {
	//		String bcrypt= passwordEncoder.encode(password);
	//		System.out.println(bcrypt);
		
	//	}
		
	//}
	
	@Autowired
	UsuarioRepo repoU;
	@Autowired
	RolRepo repoR;

	@Override
	public void run(String... args) throws Exception {
		
		
	Rol r2 = new Rol();
	    r2.setRol("ROLE_USER");
		repoR.save(r2);
		
	Usuario l = new Usuario();
	     l.setPriNombre("NOMBRE1");
	     l.setSeguNombre("NOMBRE2");
	     l.setPrinApellido("APELLIDO1");
	     l.setSegunApellido("APELLIDO2");
	     l.setNick("admin@gmail.com");
	     l.setDirecion("Mi casa");
	     l.setDui("12345678");
	     l.setLicencia("876543");
	     String bcrypt = passwordEncoder.encode("12345");
	     l.setClave(bcrypt);	
	     
	     Rol r = new Rol();
		 r.setRol("ROLE_ADMIN");
		 
	     List<Rol> rol = new ArrayList<Rol>();
	     rol.add(r);
         l.setRoles(rol);
			
	     repoU.save(l);
		
	}
	

}
