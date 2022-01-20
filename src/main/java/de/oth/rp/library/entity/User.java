package de.oth.rp.library.entity;


import de.oth.rp.library.entity.util.SingleIdEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class User extends SingleIdEntity<String> implements UserDetails {

    @Id
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private int bookCount;
    private int downloadCount;
    @OneToMany
    private List<Book> ownedBooks;

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.accountType = AccountType.STANDARD;
    }

    public User(String name, String password, AccountType accountType) {
        this.name = name;
        this.password = password;
        this.accountType = accountType;
    }

    @Override
    public String getUsername() {
        return this.name;
    }



    @Override
    public String getID() {
        return this.name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return User.this.accountType.name();
            }
        });
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPassword() {
        return password;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public List<Book> getOwnedBooks() {
        return ownedBooks;
    }

    public void setOwnedBooks(List<Book> ownedBooks) {
        this.ownedBooks = ownedBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return bookCount == user.bookCount && downloadCount == user.downloadCount && name.equals(user.name) && password.equals(user.password) && accountType == user.accountType && Objects.equals(ownedBooks, user.ownedBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, password, accountType, bookCount, downloadCount, ownedBooks);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("name='").append(name).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", accountType=").append(accountType);
        sb.append(", bookCount=").append(bookCount);
        sb.append(", downloadCount=").append(downloadCount);
        sb.append(", ownedBooks=").append(ownedBooks);
        sb.append('}');
        return sb.toString();
    }
}
