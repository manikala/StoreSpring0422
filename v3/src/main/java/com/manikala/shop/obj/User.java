package com.manikala.shop.obj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data // Геттеры сетеры и иколс
@NoArgsConstructor
@AllArgsConstructor
@Builder //DTO
@Entity // Указывает, что данный бин (класс) является сущностью.
@Table(name = "users")
public class User {

    private static final String SEQ_NAME = "user_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;
    private String name;
    private String lastname;
    private String firstname;
    private String password;
    private boolean archive; // Есть ли пользователь в архиве

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Bucket bucket;
}
