package de.oth.rp.library.entity;


import de.oth.rp.library.entity.util.SingleIdEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class User extends SingleIdEntity<String> implements UserDetails {

//    @Id @GeneratedValue
//    private long userId;
    @Id
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
//    @ElementCollection
//    private List<String> ranks;
    private final String role = "ADMIN";
    private int bookCount;
    private int downloadCount;
    @OneToMany
    private List<Book> ownedBooks;

    @Override
    public String getID() {
        return this.username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return User.this.role;
            }
        });
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
