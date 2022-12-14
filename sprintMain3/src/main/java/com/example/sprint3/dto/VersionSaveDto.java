package com.example.sprint3.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class VersionSaveDto implements Serializable {
    private String newText;
    private String username;
    //private String ip;
    private Integer sectionId;
    private String status;
}