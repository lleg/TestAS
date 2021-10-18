package ru.digitalspirit.asaka.applicationlist.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApplicationAddInfoList {

    private List<ApplicationAddInfo> applications;

    private Long total;

}
