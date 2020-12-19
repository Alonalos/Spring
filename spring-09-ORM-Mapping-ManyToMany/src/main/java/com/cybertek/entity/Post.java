package com.cybertek.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name="post_tag_rel",
    joinColumns={@JoinColumn(name="postik_id")},
            inverseJoinColumns = {@JoinColumn(name="tagulia_id")})
    private Set<Tag> tags=new HashSet<>();


    public Post(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
