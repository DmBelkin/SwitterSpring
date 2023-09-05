package com.example.switter.service;

import com.example.switter.domain.Role;
import com.example.switter.repos.UserRepo;
import com.example.switter.domain.User;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    MailSender sender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser (User user) {
      User userFormOb = userRepo.findByUsername(user.getUsername());
      if (userFormOb != null) {
          return false;
      }
      user.setActive(true);
      user.setRole(Collections.singleton(Role.USER));
      userRepo.save(user);
      user.setActivationCode(UUID.randomUUID().toString());

      if (!StringUtils.isNullOrEmpty(user.getEmail())) {
          String message = String.format(
                  "Hello, %s! \n" +
          "welcome to Sweatter, please, visit to: http//:localhost8080/activate/%s", user.getUsername(),
                  user.getActivationCode());

          sender.send(user.getEmail(), "Activation code", message);
      }
      return true;
    }

    public boolean activated(String code) {
        User user = userRepo.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActivationCode(null);
        userRepo.save(user);
        return true;
    }
}
