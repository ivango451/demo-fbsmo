package ru.gladyshev.demo.twitter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tag {

    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tagId;

    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Post> posts;

    public Tag(String name) {
        this.name = name;
    }
}
