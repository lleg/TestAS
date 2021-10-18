package ru.digitalspirit.asaka.applicationlist.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApplicationInfoList {

    private List<ApplicationInfo> applications;

    private Long total;

}
