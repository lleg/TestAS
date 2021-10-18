package ru.digitalspirit.asaka.role;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.jpa.JPAExpressions;
import lombok.Getter;
import org.springframework.stereotype.Component;
import ru.digitalspirit.asaka.applicationlist.entity.ApplicationStatusUserEntity;
import ru.digitalspirit.asaka.applicationlist.entity.QApplicationStatusEntity;
import ru.digitalspirit.asaka.applicationlist.entity.QApplicationStatusUserEntity;
import ru.digitalspirit.asaka.bpm.enums.Role;
import ru.digitalspirit.asaka.bpm.model.ColumnCondition;
import ru.digitalspirit.asaka.util.QuerydslUtil;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Default implementation of service for completing of expression with specific roles expressions
 */
@Component
public class DefaultRoleHelper implements RoleExpressionHelper {

    private static EntityPathBase<ApplicationStatusUserEntity> userBase = QApplicationStatusUserEntity.applicationStatusUserEntity;

    @Getter
    private final List<Role> roles = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public BooleanExpression addExpressionForUser(BooleanExpression expression, String user, String role) throws ParseException {
        List<ColumnCondition> userConditions = Arrays.asList(ColumnCondition.getFilterCondition("nfoRole", "eq", role),
                ColumnCondition.getFilterCondition("userLogin", "eq", user));
        BooleanExpression userExpression = JPAExpressions.selectOne().from(QApplicationStatusUserEntity.applicationStatusUserEntity)
                .where(QApplicationStatusUserEntity.applicationStatusUserEntity.appInfo.eq(QApplicationStatusEntity.applicationStatusEntity)
                        .and(QuerydslUtil.createExpressionOr(userConditions, userBase))).exists();
        return expression == null ? userExpression : expression.and(userExpression);
    }

}
