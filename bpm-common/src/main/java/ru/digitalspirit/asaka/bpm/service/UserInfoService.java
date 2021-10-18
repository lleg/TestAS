package ru.digitalspirit.asaka.bpm.service;

import ru.digitalspirit.asaka.bpm.model.UserContextData;
import ru.digitalspirit.asaka.bpm.model.UserInfo;

import java.util.List;

/**
 * Service for working with nfo users
 */
public interface UserInfoService {

    /**
     * Get users managed by chief user
     *
     * @param login Chief user login
     * @return List of managed users (see {@link UserInfo})
     */
    List<UserInfo> getManagedUsersList(String login);

    /**
     * Get roles managed by chief user
     *
     * @param login Chief user login
     * @return List of managed role names
     */
    List<String> getManagedRolesList(String login);

    /**
     * Get user bpm roles corresponding to nfo role
     *
     * @param login   User login
     * @param nfoRole Nfo role code
     * @return List of bpm role names
     */
    List<String> getBpmRolesByNfoRole(String login, String nfoRole);

    /**
     * Add additional info about user to nfo data
     *
     * @param data Nfo data
     */
    void addUserData(UserContextData data);

    /**
     * Check whether the user belongs to nfo role
     *
     * @param login   User login
     * @param nfoRole Nfo role code
     * @return User belongs
     */
    boolean hasRole(String login, String nfoRole);

    boolean hasRoleWithDepartment(String login, String department, String nfoRole);

    boolean hasRoleWithCentralSquare(String login, String department, String nfoRole);

}
