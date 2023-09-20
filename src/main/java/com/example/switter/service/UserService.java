package com.example.switter.service;

import com.example.switter.domain.Role;
import com.example.switter.repos.UserRepo;
import com.example.switter.domain.User;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailSender sender;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser (User user) {
      User userFormOb = userRepo.findByUsername(user.getName());
      if (userFormOb != null) {
          return false;
      }
      user.setActive(true);
      user.setRole(Collections.singleton(Role.USER));
      user.setActivationCode(UUID.randomUUID().toString());
      user.setPassword(encoder.encode(user.getPassword()));
      userRepo.save(user);


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

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public void userSaver(String userName, User user, Map<String, String> form) {
        user.setName(userName);

        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors
                        .toSet());

        user.getRole().clear();

        for (String role : form.keySet()) {
            if (roles.contains(role)) {
                user.getRole().add(Role.valueOf(role));
            }
        }
        userRepo.save(user);
    }
}
