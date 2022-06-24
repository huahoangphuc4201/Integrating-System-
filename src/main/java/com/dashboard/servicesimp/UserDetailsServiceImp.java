package com.dashboard.servicesimp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dashboard.config.JwtTokenUtil;
import com.dashboard.entities.DashboardPersonal;
import com.dashboard.entities.MyUserDetails;
import com.dashboard.entities.Role;
import com.dashboard.entities.User;
import com.dashboard.repositories.DashboardPersonalRepository;
import com.dashboard.repositories.DashboardRoleRepository;
import com.dashboard.repositories.DashboardUserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
	
	@Autowired
	private DashboardUserRepository userRepo;

	@Autowired
	private DashboardRoleRepository roleRepo;
	
	@Autowired
	private DashboardPersonalRepository personalRepo;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	public Boolean create(long id,String username, String password, String roleName) {
		if (!isExist(username)) {
			User user = new User();
			user.setId(id);
			user.setUsername(username);
			user.setPassword(bcryptEncoder.encode(password));
			Role role= roleRepo.findByRoleName(roleName);
			user.setRole(role);
			userRepo.save(user);
			return true;
		}
		return false;
	}

	public Map<String, String> login(String username, String password) {
		UserDetails userDetails = loadUserByUsername(username);
		String jwt = "";
		Map<String, String> response = new HashMap<String, String>();
		if (userDetails != null) {
			if (bcryptEncoder.matches(password, userDetails.getPassword()))
				jwt = jwtTokenUtil.generateToken(userDetails);
		}
		response.put("token", jwt);
		return response;
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	public List<User> findAll() {
		return userRepo.findAll();
	}

	private Boolean isExist(String username) {
		if (userRepo.findByUsername(username) != null)
			return true;
		return false;
	}

	public void changePassword(String username, String password) {
		User user=userRepo.findByUsername(username);
		user.setPassword(bcryptEncoder.encode(password));
		userRepo.save(user);
	}
	
	public boolean deleteAccount(long id) {
		userRepo.deleteById(id);
		return true;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if (user == null) {
			return null;
		}
		return new MyUserDetails(user);
	}

}
