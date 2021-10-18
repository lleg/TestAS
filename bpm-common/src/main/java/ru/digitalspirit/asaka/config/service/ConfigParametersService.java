package ru.digitalspirit.asaka.config.service;


import ru.digitalspirit.asaka.config.entity.ConfigParam;
import ru.digitalspirit.asaka.config.enums.ParameterCode;

import java.util.List;

/**
 * Service for working with configuration parameters
 */
public interface ConfigParametersService {

    /**
     * Get parameter value by code
     * @param code Parameter code
     * @return Parameter value
     */
    String getParameterValue(String code);

    /**
     * Get parameter value by code
     * @param code Parameter code (see {@link ParameterCode})
     * @return Parameter value
     */
    String getParameterValue(ParameterCode code);

    /**
     * Get parameter by code
     * @param code Parameter code
     * @return Parameter value (see {@link ConfigParam})
     */
    ConfigParam getParameter(String code);

    /**
     * Get parameter by code
     * @param code Parameter code see {@link ParameterCode})
     * @return Parameter value (see {@link ConfigParam})
     */
    ConfigParam getParameter(ParameterCode code);

    /**
     * Get all parameters
     * @return List of parameters (see {@link ConfigParam})
     */
    List<ConfigParam> getAllParameters();

    /**
     * Update parameter value by code
     * @param code Parameter code
     * @param value Parameter value
     * @return Parameter (see {@link ConfigParam})
     */
    ConfigParam updateParameterValue(String code, String value);

    /**
     * Update parameter value by code
     * @param code Parameter code (see {@link ParameterCode})
     * @param value Parameter value
     * @return Parameter (see {@link ConfigParam})
     */
    ConfigParam updateParameterValue(ParameterCode code, String value);

}
