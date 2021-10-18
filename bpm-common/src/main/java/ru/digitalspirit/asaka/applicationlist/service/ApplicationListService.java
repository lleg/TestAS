package ru.digitalspirit.asaka.applicationlist.service;

import ru.digitalspirit.asaka.applicationlist.model.ApplicationInfoList;
import ru.digitalspirit.asaka.bpm.model.ColumnCondition;

import java.util.List;

/**
 * Service for working with application list
 */
public interface ApplicationListService {

    /**
     * Get application list for user
     *
     * @param login      User login
     * @param nfoRole    Nfo role code
     * @param conditions List of additional filters
     * @param page       Page number
     * @param rows       Rows count
     * @return List of applications
     */
    ApplicationInfoList getByLogin(String login, String nfoRole, List<ColumnCondition> conditions, int page, int rows);

}
