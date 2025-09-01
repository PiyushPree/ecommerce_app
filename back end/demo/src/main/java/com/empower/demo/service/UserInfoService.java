package com.empower.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.empower.demo.entity.UserInfo;
import com.empower.demo.entity.UserInfoDetails;
import com.empower.demo.helper.UserAlreadyExistAuthenticationException;
import com.empower.demo.repository.UserInfoRepository;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userDetail = repository.findById(username);

        // Converting userDetail to UserDetails
        return userDetail.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

    public String addUser(UserInfo userInfo) {
    	//CHECK if user already exists
    	UserInfo temp = findUserById(userInfo.getUsername());
    	if(temp==null) {
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User Added Successfully";
    	}else
    	{
    		throw new UserAlreadyExistAuthenticationException("User "+userInfo.getUsername()+" already exists. cannot signup");
    	}
    }

    public UserInfo findUserById(String username) {
    	Optional<UserInfo> temp = repository.findById(username);
    	UserInfo user=null;
    	if(temp.isPresent())
    	{
    		user=temp.get();
    	}
    	return user;
    }

}
