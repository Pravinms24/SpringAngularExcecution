package com.project.angularjava.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.angularjava.model.Address;
import com.project.angularjava.model.Role;
import com.project.angularjava.model.User;
import com.project.angularjava.repository.RoleRepository;
import com.project.angularjava.repository.UserRepository;

import lombok.extern.log4j.Log4j2;


@Service
@Log4j2
public class RegistrationService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
//	@Autowired
//	private PasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;

	public void populateDefaultData() {
		log.info("Registration Service Called..");
		long count = roleRepository.count();
		log.info(count);
		if (count == 0) {
			populateRole();
		}
		
		long userCount = userRepository.count();
		
		if (userCount == 0) {
		
			Role role = roleRepository.findByRole("ADMIN");
			System.out.println(role);
			if (role == null) {
				role = new Role();
				role.setRole("ADMIN");
				role = roleRepository.save(role);
			}
			System.out.println(role);
			User user = userRepository.findByEmail("admin@gmail.com");
			log.info("is user role avilable?"+role);
			if (user == null) {
				user = new User();
				user.setFullname("ADMINUSER");
				user.setEmail("admin@gmail.com");
				user.setPassword("password");
				user.setPhoneNumber("9487911594");
				Address address = new Address();
				address.setCity("Chennai");
				address.setState("Tamil Nadu");
				address.setCountry("India");
				address.setPostelCode("629001");
				user.setAddress(address);

				user.setEnabled(true);
//				user.setPassword(bCryptPasswordEncoder.encode("password"));
				//user.setActive(true);

				Set<Role> adminRole = new HashSet<>();
				//List<Role> adminRole =new ArrayList<>();
				adminRole.add(role);

				user.setRoles(adminRole);
				customUserDetailsService.saveUser(user);
				//userRepository.save(user);

			}
		}
	}
	
	public void populateRole() {
		
		long count = roleRepository.count();
		log.info(count);
		if (count == 0) {
			Role roleAdmin = roleRepository.findByRole("ADMIN");
			//System.out.println(role);
			if (roleAdmin == null) {
				roleAdmin = new Role();
				roleAdmin.setRole("ADMIN");
				roleAdmin = roleRepository.save(roleAdmin);
			}
			
			Role roleCustomer = roleRepository.findByRole("CUSTOMER");
			//System.out.println(role);
			if (roleCustomer == null) {
				roleCustomer = new Role();
				roleCustomer.setRole("CUSTOMER");
				roleCustomer = roleRepository.save(roleCustomer);
			}
			
			Role roleView = roleRepository.findByRole("VIEW");
			//System.out.println(role);
			if (roleView == null) {
				roleView = new Role();
				roleView.setRole("VIEW");
				roleView = roleRepository.save(roleView);
			}
			
			
		}
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
