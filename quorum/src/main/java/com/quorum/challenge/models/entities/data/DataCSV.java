package com.quorum.challenge.models.entities.data;

import lombok.Getter;

@Getter
public abstract class DataCSV {
    public abstract Object buildModelFromCSV(String[] metadata);
}
