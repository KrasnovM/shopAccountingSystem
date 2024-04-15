package ru.krasnovm.shopAccountingSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.krasnovm.shopAccountingSystem.entity.ShopUser;
import ru.krasnovm.shopAccountingSystem.repository.ShopUserRepository;

import java.util.Optional;

@Configuration
public class ShopUserDetailsService implements UserDetailsService {

    @Autowired
    private ShopUserRepository shopUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        if (username.equals(env.getProperty("spring.security.admin.name"))) {
//
//        } else if (username.equals(env.getProperty("spring.security.user.name"))) {
//
//        } else {
//            throw new UsernameNotFoundException("User doesn't exist");
//        }
        Optional<ShopUser> user = shopUserRepository.findByName(username);
        return user.map(ShopUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User doesn't exist"));
    }
}
