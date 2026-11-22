package com.taskforge.taskforge_api.config;

import com.taskforge.taskforge_api.model.Bug;
import com.taskforge.taskforge_api.model.Epic;
import com.taskforge.taskforge_api.model.Story;
import com.taskforge.taskforge_api.model.User;
import com.taskforge.taskforge_api.repository.TaskRepository;
import com.taskforge.taskforge_api.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seed(TaskRepository tasks, UserRepository users) {
        return args -> {
            if (users.count() == 0) {
                users.save(new User("Suha", "suha@example.com"));
                users.save(new User("Ravi", "ravi@example.com"));
            }
            if (tasks.count() == 0) {
                tasks.save(new Story("Set up project"));
                tasks.save(new Bug("Fix login crash"));
                tasks.save(new Epic("User login system"));
            }
        };
    }
}
// package com.taskforge.taskforge_api.config;

// import com.taskforge.taskforge_api.model.Bug;
// import com.taskforge.taskforge_api.model.Epic;
// import com.taskforge.taskforge_api.model.Story;
// import com.taskforge.taskforge_api.repository.TaskRepository;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class DataSeeder {

//     @Bean
//     CommandLineRunner seed(TaskRepository repository) {
//         return args -> {
//             if (repository.count() == 0) {
//                 repository.save(new Story("Set up project"));
//                 repository.save(new Bug("Fix login crash"));
//                 repository.save(new Epic("User login system"));
//             }
//         };
//     }
// }