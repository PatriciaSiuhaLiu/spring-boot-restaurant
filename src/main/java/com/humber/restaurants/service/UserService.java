package com.humber.restaurants.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.humber.restaurants.document.User;
import com.humber.restaurants.repository.UserRepository;


@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		
//		Optional<User> user1 = userRepository.findByUsername(username);
//		user1.get().getAuthorities();
//		// TODO Auto-generated method stub
		return userRepository.findByUsername(username)
				.orElseThrow(()->new UsernameNotFoundException("username not found"));
		

//    	Optional<User> user = userRepository.findByUsername(username);
//        System.out.println("username "+user.get().getUsername());
//        if(user != null) {
//            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), true, true, true, true,
//                    AuthorityUtils.createAuthorityList("ROLE_USER"));
//        } else {
//            throw new UsernameNotFoundException("could not find the user '"
//                    + username + "'");
//        }
    
	}
	
	public User findById(String id) {
		return userRepository.findById(id)
				.orElseThrow(()->new UsernameNotFoundException("user id not found"));
	}
	

}

//
//@Service
//public class CustomUserDetailsService implements UserDetailsService{
//
//	  @Autowired
//	    private UserRepository userRepository;
//
//	    @Override
//	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//	    	Optional<User> user = userRepository.findByUsername(username);
//	        System.out.println("username "+user.get().getUsername());
//	        if(user != null) {
//	            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), true, true, true, true,
//	                    AuthorityUtils.createAuthorityList("ROLE_USER"));
//	        } else {
//	            throw new UsernameNotFoundException("could not find the user '"
//	                    + username + "'");
//	        }
//	    }
//}
