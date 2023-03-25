package com.example.testsecurity.security;

import com.example.testsecurity.model.People;
import com.example.testsecurity.repo.PeopleRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDetailsServicePeople implements UserDetailsService {
    PeopleRepo peopleRepo;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        People people = peopleRepo.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("Нет такого пользователя"));
        return new UserDetailsPeople(people);
    }
}
