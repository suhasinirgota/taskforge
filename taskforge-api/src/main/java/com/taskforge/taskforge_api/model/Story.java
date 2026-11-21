package com.taskforge.taskforge_api.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("STORY")
public class Story extends Task {

    protected Story() {
    }

    public Story(String title) {
        super(title);
    }

    @Override
    public int estimatePoints() {
        return 5;
    }
}
// package com.taskforge.taskforge_api.model;

// public class Story extends Task {
//     public Story(int id, String title) {
//         super(id, title);
//     }

//     @Override
//     public int estimatePoints() {
//         return 5;
//     }
// }