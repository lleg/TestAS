package ru.digitalspirit.asaka.applicationlist.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalspirit.asaka.applicationlist.model.ApplicationInfoList;
import ru.digitalspirit.asaka.bpm.enums.Role;
import ru.digitalspirit.asaka.bpm.model.ColumnCondition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service for working with application list with selecting of specific application service
 */
@Service
public class ApplicationListServiceImpl implements ApplicationListService {

    private static Logger logger = LoggerFactory.getLogger(ApplicationListServiceImpl.class);

    private Map<String, ApplicationListHelper> applicationListHelperMap = new HashMap<>();

    @Autowired
    private DefaultApplicationListHelper defaultApplicationListHelper;

    /**
     * Constructs service with list of application specific helpers
     *
     * @param applicationListHelpers List of application specific helpers (see {@link ApplicationListHelper})
     */
    @Autowired
    public ApplicationListServiceImpl(List<ApplicationListHelper> applicationListHelpers) {
        for (ApplicationListHelper applicationListHelper : applicationListHelpers) {
            for (Role role : applicationListHelper.getSupportedRoles()) {
                applicationListHelperMap.put(role.getCode(), applicationListHelper);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ApplicationInfoList getByLogin(String login, String role, List<ColumnCondition> conditions, int page, int rows) {
        if (conditions == null) {
            conditions = new ArrayList<>();
        }
        return getApplicationInfoListWithUser(conditions, login, role, page, rows);
    }

    /**
     * Get application list for user with selecting of specific service
     *
     * @param conditions List of additional filters
     * @param user       User login
     * @param role       Nfo role code
     * @param page       Page number
     * @param size       Rows count
     * @return List of applications
     */
    private ApplicationInfoList getApplicationInfoListWithUser(List<ColumnCondition> conditions, String user, String role, int page, int size) {
        if (applicationListHelperMap.containsKey(role)) {
            return applicationListHelperMap.get(role).getApplicationInfoList(conditions, user, role, page, size);
        }
        return defaultApplicationListHelper.getApplicationInfoList(conditions, user, role, page, size);
    }

}
