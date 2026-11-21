package com.taskforge.taskforge_api.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EPIC")
public class Epic extends Task {

    protected Epic() {
    }

    public Epic(String title) {
        super(title);
    }

    @Override
    public int estimatePoints() {
        return 13;
    }
}
// package com.taskforge.taskforge_api.model;

// public class Epic extends Task {
//      public Epic(int id, String title) {
//         super(id, title);
//     }

//     @Override
//     public int estimatePoints() {
//         return 13;
//     }
    
// }
