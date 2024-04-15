package ru.krasnovm.shopAccountingSystem.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.krasnovm.shopAccountingSystem.entity.ShopUser;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ShopUserDetails implements UserDetails {
    private String name;
    private  String password;
    private List<GrantedAuthority> roles;

//    @Autowired
//    private Environment env;

    public ShopUserDetails(ShopUser shopUser) {
//        if (name.equals(env.getProperty("spring.security.admin.name")) &&
//                password.equals(env.getProperty("spring.security.admin.password"))) {
//            this.name = name;
//            this.password = password;
//            this.roles = List.of(
//                    new SimpleGrantedAuthority("ROLE_ADMIN"),
//                    new SimpleGrantedAuthority("ROLE_USER")
//            );
//        } else if (name.equals(env.getProperty("spring.security.user.name")) &&
//                password.equals(env.getProperty("spring.security.user.password"))) {
//            this.name = name;
//            this.password = password;
//            this.roles = List.of(
//                    new SimpleGrantedAuthority("ROLE_USER")
//            );
//        } else {
//            throw new UsernameNotFoundException("User doesn't exist");
//        }
        this.name = shopUser.getName();
        this.password = new BCryptPasswordEncoder().encode(shopUser.getPassword());
        this.roles = Arrays.stream(shopUser.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
