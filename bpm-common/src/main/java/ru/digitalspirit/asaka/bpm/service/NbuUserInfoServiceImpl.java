package ru.digitalspirit.asaka.bpm.service;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.StringUtils;
import ru.digitalspirit.asaka.applicationlist.entity.BusinessProcessRole;
import ru.digitalspirit.asaka.bpm.model.organisation.UserDataBody;
import ru.digitalspirit.asaka.bpm.model.NbuRole;
import ru.digitalspirit.asaka.bpm.model.UserContextData;
import ru.digitalspirit.asaka.bpm.model.UserInfo;
import ru.digitalspirit.asaka.util.CoreUtil;

import java.util.*;

import static ru.digitalspirit.asaka.util.StringUtil.isEmpty;

public class NbuUserInfoServiceImpl implements UserInfoService {

    @Setter
    private NbuBpmService bpmService;

    private static final String PREFIX_SUFFIX_DELIMITER = "_";

    private Map<String, NbuRole> nbuRoles = null;

    private String getManagedUsersServiceId = "NBUSERV@GetManagedUsersInfo";
    private String getManagedRolesServiceId = "ROSCMN@GetManagedRolesService";

    private static final String NULL_USER_LOGIN_ERROR = "User login can't be null!";
    private static final String EMPTY_USER_LOGIN_ERROR = "User login can't be empty!";
    private static final String EMPTY_NFO_ROLE_ERROR = "NfoRole can't be empty!";
    private static final String EMPTY_BPM_ROLE_ERROR = "BpmRole can't be empty!";
    private static final String UNKNOWN_NFO_ROLE_ERROR = "Unknown nfo role!";

    private static final Logger logger = LoggerFactory.getLogger(NbuUserInfoServiceImpl.class);


    public NbuUserInfoServiceImpl(JpaRepository<BusinessProcessRole, Long> businessProcessRoleRepository) {
        nbuRoles = getNbuRoles(businessProcessRoleRepository);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserInfo> getManagedUsersList(String login) {
        if (isEmpty(login)) {
            throw new IllegalArgumentException(NULL_USER_LOGIN_ERROR);
        }

        List<UserInfo> users = null;

        try {
            logger.info("Start receiving managed users for chief {}", login);
            users = bpmService.getManagedUsersListService(login, getManagedUsersServiceId);
            logger.info("List of managed users for chief {} received", login);
        }catch (Exception ex) {
            logger.error("Exception on receiving list of managed users for chief {}", login, ex);
            throw new BpmServiceException("Exception on receiving list of managed users for chief " + login);
        }

        return users;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getManagedRolesList(String login) {
        if (isEmpty(login)) {
            throw new IllegalArgumentException(NULL_USER_LOGIN_ERROR);
        }

        List<String> roles = null;

        try {
            logger.info("Start receiving managed roles for chief {}", login);
            roles = bpmService.getManagedRolesListService(login, getManagedRolesServiceId);
            logger.info("List of managed roles for chief {} received", login);
        }catch (Exception ex) {
            logger.error("Exception on receiving list of managed roles for chief {}", login, ex);
            throw new BpmServiceException("Exception on receiving list of managed roles for chief {}", login);
        }

        return roles;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getBpmRolesByNfoRole(String login, String nfoRole) {
        List<String> bpmRolesForNfoRole = new ArrayList<>();
        List<String> bpmRoles = getAllBpmRoles(login);
        for (String bpmRole : bpmRoles) {
            if (matchingRoles(bpmRole, nfoRole)) {
                bpmRolesForNfoRole.add(bpmRole);
            }
        }
        return bpmRolesForNfoRole;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addUserData(UserContextData data) {
        String login = data.getUserLogin();
        if (!isEmpty(login)) {
            UserDataBody user = bpmService.getUser(login, false);
            data.setUserLogin(user.getUserName());
            data.setUserName(user.getFullName());
            data.setUserRole(StringUtils.collectionToCommaDelimitedString(getNbuRoles(user.getMemberships())));
            data.setBpmRoles(getBpmRoles(user.getMemberships()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasRole(String login, String nfoRole) {
        CoreUtil.checkNotEmpty(login, EMPTY_USER_LOGIN_ERROR);
        CoreUtil.checkNotEmpty(nfoRole, EMPTY_NFO_ROLE_ERROR);
        if (!nbuRoles.containsKey(nfoRole)) {
            throw new IllegalArgumentException(UNKNOWN_NFO_ROLE_ERROR);
        }

        List<String> bpmRoles = getAllBpmRoles(login);
        NbuRole role = nbuRoles.get(nfoRole);
        return hasRole(bpmRoles, role);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasRoleWithDepartment(String login, String department, String nfoRole) {
        CoreUtil.checkNotEmpty(login, EMPTY_USER_LOGIN_ERROR);
        CoreUtil.checkNotEmpty(department, "Department can't be empty!");
        CoreUtil.checkNotEmpty(nfoRole, EMPTY_NFO_ROLE_ERROR);
        if (!nbuRoles.containsKey(nfoRole)) {
            throw new IllegalArgumentException(UNKNOWN_NFO_ROLE_ERROR);
        }

        NbuRole role = nbuRoles.get(nfoRole);
        if (!role.getWithDepartment()) {
            throw new IllegalArgumentException("Role without department!");
        }
        List<String> bpmRoles = getAllBpmRoles(login);
        return hasRoleWithDepartment(bpmRoles, department, role);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasRoleWithCentralSquare(String login, String centralSquare, String nfoRole) {
        CoreUtil.checkNotEmpty(login, EMPTY_USER_LOGIN_ERROR);
        CoreUtil.checkNotEmpty(centralSquare, "Central square can't be empty!");
        CoreUtil.checkNotEmpty(nfoRole, EMPTY_NFO_ROLE_ERROR);
        if (!nbuRoles.containsKey(nfoRole)) {
            throw new IllegalArgumentException(UNKNOWN_NFO_ROLE_ERROR);
        }

        NbuRole role = nbuRoles.get(nfoRole);
        if (role.getWithCentralSquare()) {
            throw new IllegalArgumentException("Role without central square!");
        }
        List<String> bpmRoles = getAllBpmRoles(login);
        return hasRoleWithCentralSquare(bpmRoles, centralSquare, role);
    }

    private boolean hasRoleWithDepartment(List<String> bpmRoles, String department, NbuRole role) {
        String roleName = (role.getPrefix() != null ? role.getPrefix() : "") + department + (role.getSuffix() != null ? role.getSuffix() : "");
        return bpmRoles.contains(roleName);
    }

    private boolean hasRoleWithCentralSquare(List<String> bpmRoles, String centralSquare, NbuRole role) {
        String roleName = (role.getPrefix() != null ? role.getPrefix() : "") + centralSquare + (role.getSuffix() != null ? role.getSuffix() : "");
        return bpmRoles.contains(roleName);
    }

    private boolean hasRole(List<String> bpmRoles, NbuRole role) {
        for (String bpmRole : bpmRoles) {
            if (matchingRoles(bpmRole, role)) {
                return true;
            }
        }
        return false;
    }

    private boolean matchingRoles(String bpmRole, NbuRole nbuRole) {
        String tempRole = bpmRole;
        if (!StringUtils.isEmpty(nbuRole.getPrefix())) {
            if (bpmRole.startsWith(nbuRole.getPrefix())) {
                tempRole = tempRole.substring(nbuRole.getPrefix().length());
            } else {
                return false;
            }
        }
        if (!StringUtils.isEmpty(nbuRole.getSuffix())) {
            if (bpmRole.endsWith(nbuRole.getSuffix())) {
                tempRole = tempRole.substring(0, tempRole.length() -  nbuRole.getSuffix().length());
            } else {
                return false;
            }
        }
        return !tempRole.contains(PREFIX_SUFFIX_DELIMITER);
    }

    private boolean matchingRoles(String bpmRole, String nfoRole) {
        return matchingRoles(bpmRole, nbuRoles.get(nfoRole));
    }

    private Set<String> getNbuRoles(List<String> roles) {
        Set<String> nfoRoles = new HashSet<>();
        for (String role : roles) {
            for (String roleCode : this.nbuRoles.keySet()) {
                NbuRole nbuRole = this.nbuRoles.get(roleCode);
                if (matchingRoles(role, nbuRole)) {
                    nfoRoles.add(nbuRole.getCode());
                }
            }
        }
        return nfoRoles;
    }

    private Set<String> getBpmRoles(List<String> roles) {
        Set<String> nbuRoles = new HashSet<>();
        for (String role : roles) {
            for (String roleCode : this.nbuRoles.keySet()) {
                NbuRole nbuRole = this.nbuRoles.get(roleCode);
                if (matchingRoles(role, nbuRole)) {
                    nbuRoles.add(role);
                    break;
                }
            }
        }
        return nbuRoles;
    }




    private List<String> getAllBpmRoles(String login) {
        UserDataBody user = bpmService.getUser(login, false);
        return user.getMemberships();
    }

    private Map<String, NbuRole> getNbuRoles(JpaRepository<BusinessProcessRole, Long> businessProcessRoleRepository) {
        Map<String, NbuRole> roles = new HashMap<>();
        for (BusinessProcessRole role : businessProcessRoleRepository.findAll()) {
            roles.put(role.getCode(), NbuRole.createRole(role));
        }
        return roles;
    }
}
