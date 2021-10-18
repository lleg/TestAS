package ru.digitalspirit.asaka.role;


import com.querydsl.core.types.dsl.BooleanExpression;
import ru.digitalspirit.asaka.bpm.enums.Role;

import java.text.ParseException;
import java.util.List;

/**
 * Service for completing of expression with specific roles expressions
 */
public interface RoleExpressionHelper {

    /**
     * Get list of roles supported by service
     *
     * @return List of nfo roles (see {@link Role})
     */
    List<Role> getRoles();

    /**
     * Add expression for user based on user login and role
     *
     * @param expression Expression (see {@link BooleanExpression})
     * @param user       User login
     * @param nfoRole    Nfo role code
     * @return Expression (see {@link BooleanExpression})
     */
    BooleanExpression addExpressionForUser(BooleanExpression expression, String user, String nfoRole) throws ParseException;

}
