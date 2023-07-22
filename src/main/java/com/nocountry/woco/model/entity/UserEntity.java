package com.nocountry.woco.model.entity;

import com.nocountry.woco.model.entity.Reservation;
import com.nocountry.woco.model.entity.RoleEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE users SET soft_delete = true WHERE id=?")
@Where(clause = "soft_delete=false")
@Table(name = "app_users", indexes = @Index(name = "unique_email", columnList = "email", unique = true))
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private Boolean reservation;

    @OneToMany
    private List<Reservation> reservations;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<RoleEntity> roles;

    @CreationTimestamp
    @Column(name = "create_timestamp")
    private Timestamp createTimestamp;

    @Column(name = "soft_delete")
    private Boolean softDelete = Boolean.FALSE;


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
//    }

}
//
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return !this.softDelete;
//    }
//}
