package com.humber.restaurants.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig    {
    
    @Autowired
    private AccessTokenEntryPoint accessTokenEntryPoint;
    
	private UserDetailsService userDetailsService;

    @Bean
    public AccessTokenFilter accessTokenFilter() {
        return new AccessTokenFilter();
    }
    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////		http
////			.securityMatcher(antMatcher("/api/**"))                              
////			.authorizeHttpRequests(authorize -> authorize
////				.requestMatchers(antMatcher("/user/**")).hasRole("USER")         
////				.requestMatchers(regexMatcher("/admin/.*")).hasRole("ADMIN")     
////				.requestMatchers(new MyCustomRequestMatcher()).hasRole("SUPERVISOR")     
////				.anyRequest().authenticated()
////			)
////			.formLogin(withDefaults());
////		return http.build();
//		
//		 http.cors().and().csrf().disable()
//       .exceptionHandling().authenticationEntryPoint(accessTokenEntryPoint).and()
//       .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//       .authorizeRequests().requestMatchers("/api/auth/**").permitAll()
//       .anyRequest().authenticated();
//http.addFilterBefore(accessTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//
//return http.build();
//	}

	 @Bean
	    public AuthenticationManager customAuthenticationManager(HttpSecurity http) throws Exception {
	        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject
	          (AuthenticationManagerBuilder.class);
	        authenticationManagerBuilder.userDetailsService(userDetailsService)
	            .passwordEncoder(bCryptPasswordEncoder());
	        return authenticationManagerBuilder.build();
	    }

	 
	 
	    @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	    
	    

	    @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    	
	    	
	    	 http.cors().and().csrf().disable()
	         .exceptionHandling().authenticationEntryPoint(accessTokenEntryPoint).and()
	         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
	         .authorizeRequests().requestMatchers("/api/auth/**").permitAll()
//	         .requestMatchers("/api/users/getAllRestaurant").
//	         .requestMatchers("/api/restaurant/**").hasRole(null)
//	         .requestMatchers("/api/getAllRestaurant/**").hasRole("USER")
	         .anyRequest().authenticated(); //rest services u need to authenticated
	  http.addFilterBefore(accessTokenFilter(), UsernamePasswordAuthenticationFilter.class);   	
	    	
//	    	
//	        http.cors().and().csrf()
//	            .disable()
//	            .authorizeRequests()
//	            .and()
//	            .httpBasic()
//	            .and()
//	            .authorizeRequests()
//	            .anyRequest()
//	            .permitAll()
//	            .and()
//	            .sessionManagement()
//	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	        return http.build();
	    }
//    @Bean
//    @Override
//    protected AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService)
//                .passwordEncoder(passwordEncoder());
//    }
//   
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable()
//                .exceptionHandling().authenticationEntryPoint(accessTokenEntryPoint).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeRequests().antMatchers("/api/auth/**").permitAll()
//                .anyRequest().authenticated();
//        http.addFilterBefore(accessTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
}



