package com.taskforge.taskforge_api.model;

public class Bug extends Task {
    public Bug(int id, String title) {
        super(id, title);
    }

    @Override
    public int estimatePoints() {
        return 3;
    }
}