package ru.fbsmo.news.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Profile {

    @Id
    @Column(name = "profile_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long profileId;

    private String username;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "photo_path")
    private String photoPath;

    private String about;

    @OneToOne
    @JoinColumn( name = "user_id")
    private User user;
}
