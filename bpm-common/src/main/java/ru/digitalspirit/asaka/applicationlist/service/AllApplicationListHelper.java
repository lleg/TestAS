package ru.digitalspirit.asaka.applicationlist.service;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.JPAExpressions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Component;
import ru.digitalspirit.asaka.applicationlist.entity.AdditionalApplicationInfoEntity;
import ru.digitalspirit.asaka.applicationlist.entity.ApplicationStatusEntity;
import ru.digitalspirit.asaka.applicationlist.entity.QAdditionalApplicationInfoEntity;
import ru.digitalspirit.asaka.applicationlist.entity.QApplicationStatusEntity;
import ru.digitalspirit.asaka.applicationlist.repository.ApplicationStatusRepository;
import ru.digitalspirit.asaka.applicationlist.entity.*;
import ru.digitalspirit.asaka.applicationlist.model.ApplicationInfo;
import ru.digitalspirit.asaka.applicationlist.model.ApplicationInfoList;
import ru.digitalspirit.asaka.bpm.enums.Role;
import ru.digitalspirit.asaka.bpm.model.ColumnCondition;
import ru.digitalspirit.asaka.util.QuerydslUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Default implementation of service for working with application list
 */
@Component
public class AllApplicationListHelper implements ApplicationListHelper {
    private static final String ADDINFO_CONDITION_PREFIX = "additionalInfo.";
    private static final List<Role> SUPPORTED_ROLES = Arrays.asList(Role.PM);

    @Autowired
    private ApplicationStatusRepository applicationStatusRepository;

    private static EntityPathBase<ApplicationStatusEntity> base = QApplicationStatusEntity.applicationStatusEntity;
    private static EntityPathBase<AdditionalApplicationInfoEntity> addInfoBase = QAdditionalApplicationInfoEntity.additionalApplicationInfoEntity;

    private static Logger logger = LoggerFactory.getLogger(AllApplicationListHelper.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Role> getSupportedRoles() {
        return SUPPORTED_ROLES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ApplicationInfoList getApplicationInfoList(List<ColumnCondition> conditions, String user, String role, int page, int size) {
        try {
            ApplicationInfoList appList = new ApplicationInfoList();
            List<ColumnCondition> addInfoConditions = filterConditions(conditions, ADDINFO_CONDITION_PREFIX);
            QPageRequest pageRequest = new QPageRequest(page, size, QuerydslUtil.getSort(conditions, base));
            BooleanExpression expression = QuerydslUtil.createExpression(conditions, base);
            if (!addInfoConditions.isEmpty()) {
                expression = addAdditionalInfoFilterExpression(addInfoConditions, expression);
            }
            Page<ApplicationStatusEntity> resultPage = applicationStatusRepository.findAll(expression, pageRequest);
            appList.setApplications(makeList(resultPage));
            appList.setTotal(resultPage.getTotalElements());
            return appList;
        } catch (ParseException e) {
            logger.error("Can't receive application info list", e);
            return null;
        }
    }


    /**
     * Make list from iterable
     *
     * @param iter Iterable for ApplicationStatusEntity
     * @return List of ApplicationInfo
     */
    private static List<ApplicationInfo> makeList(Iterable<ApplicationStatusEntity> iter) {
        ArrayList<ApplicationInfo> list = new ArrayList<>();
        for (ApplicationStatusEntity item : iter) {
            list.add(item);
        }
        return list;
    }

    private List<ColumnCondition> filterConditions(List<ColumnCondition> conditions, String prefix) {
        List<ColumnCondition> filtered = new ArrayList<>();
        for (Iterator<ColumnCondition> iterator = conditions.iterator(); iterator.hasNext(); ) {
            ColumnCondition condition = iterator.next();
            if (condition.getColumn().startsWith(prefix)) {
                condition.setColumn(condition.getColumn().substring(prefix.length()));
                filtered.add(condition);
                iterator.remove();
            }
        }
        return filtered;
    }

    private BooleanExpression addAdditionalInfoFilterExpression(List<ColumnCondition> conditions, BooleanExpression expression) throws ParseException {
        BooleanExpression additionalExpression = JPAExpressions.selectOne().from(QAdditionalApplicationInfoEntity.additionalApplicationInfoEntity)
                .where(QAdditionalApplicationInfoEntity.additionalApplicationInfoEntity.application.eq(QApplicationStatusEntity.applicationStatusEntity)
                        .and(QuerydslUtil.createExpression(conditions, addInfoBase))).exists();
        return expression == null ? additionalExpression : expression.and(additionalExpression);
    }
}
