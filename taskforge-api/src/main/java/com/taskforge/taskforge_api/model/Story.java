package com.taskforge.taskforge_api.model;

public class Story extends Task {
    public Story(int id, String title) {
        super(id, title);
    }

    @Override
    public int estimatePoints() {
        return 5;
    }
}