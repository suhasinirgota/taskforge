package com.taskforge.taskforge_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    protected User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User other)) return false;
        return Objects.equals(email, other.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return name + " <" + email + ">";
    }
}
// package com.taskforge.taskforge_api.model;

// import java.util.Objects;

// public class User {
//     private String name;
//     private String email;

//     public User(String name, String email) {
//         this.name = name;
//         this.email = email;
//     }

//     public String getName() { return name; }
//     public String getEmail() { return email; }

//     @Override
//     public boolean equals(Object o) {
//         if (this == o) return true;
//         if (!(o instanceof User)) return false;
//         User other = (User) o;
//         return email.equals(other.email);
//     }

//     @Override
//     public int hashCode() {
//         return Objects.hash(email);
//     }

//     @Override
//     public String toString() {
//         return name + " <" + email + ">";
//     }
// }