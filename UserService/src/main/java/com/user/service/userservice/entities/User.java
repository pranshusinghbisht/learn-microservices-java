package com.user.service.userservice.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="micro_users")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String userId;

    @Column(length = 20)
    private String name;
    private String email;
    private String about;

    @Transient // transient means when we dont want to save data in db JPA will ignore
    private List<Rating> ratings = new ArrayList<>();
}
