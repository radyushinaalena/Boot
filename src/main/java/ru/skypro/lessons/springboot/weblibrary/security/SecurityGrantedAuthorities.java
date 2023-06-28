package ru.skypro.lessons.springboot.weblibrary.security;

import org.springframework.security.core.GrantedAuthority;
import ru.skypro.lessons.springboot.weblibrary.pojo.Authority;

public class SecurityGrantedAuthorities implements GrantedAuthority {
    private String role;

    public SecurityGrantedAuthorities(Authority authority) {
        this.role = authority.getRole();
    }
    @Override
    public String getAuthority() {
        return this.role;
    }
}