package com.taskforge.taskforge_api.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BUG")
public class Bug extends Task {

    protected Bug() {
    }

    public Bug(String title) {
        super(title);
    }

    @Override
    public int estimatePoints() {
        return 3;
    }
}
// package com.taskforge.taskforge_api.model;

// public class Bug extends Task {
//     public Bug(int id, String title) {
//         super(id, title);
//     }

//     @Override
//     public int estimatePoints() {
//         return 3;
//     }
// }