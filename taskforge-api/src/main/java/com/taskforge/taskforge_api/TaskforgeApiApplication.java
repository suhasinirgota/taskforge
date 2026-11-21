
package com.taskforge.taskforge_api;

import java.util.TimeZone;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskforgeApiApplication {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
        SpringApplication.run(TaskforgeApiApplication.class, args);
    }
}
// package com.taskforge.taskforge_api;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
// public class TaskforgeApiApplication {

// 	public static void main(String[] args) {
// 		SpringApplication.run(TaskforgeApiApplication.class, args);
// 	}

// }
