package ru.digitalspirit.asaka.applicationlist.service;

import ru.digitalspirit.asaka.applicationlist.model.ApplicationInfoList;
import ru.digitalspirit.asaka.bpm.enums.Role;
import ru.digitalspirit.asaka.bpm.model.ColumnCondition;

import java.util.List;

/**
 * Service for working with application list (specialized for specific roles)
 */
public interface ApplicationListHelper {

    /**
     * Get list of roles supported by service
     *
     * @return List of nfo roles
     */
    List<Role> getSupportedRoles();

    /**
     * Get application list for user
     *
     * @param conditions List of additional filters
     * @param user       User login
     * @param role       Nfo role code
     * @param page       Page number
     * @param size       Rows count
     * @return List of applications
     */
    ApplicationInfoList getApplicationInfoList(List<ColumnCondition> conditions, String user, String role, int page, int size);

}
