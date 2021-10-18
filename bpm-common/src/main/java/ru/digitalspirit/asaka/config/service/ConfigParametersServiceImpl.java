package ru.digitalspirit.asaka.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.digitalspirit.asaka.config.entity.ConfigParam;
import ru.digitalspirit.asaka.config.enums.ParameterCode;
import ru.digitalspirit.asaka.config.repository.ConfigParamRepository;
import ru.digitalspirit.asaka.exception.NbuRuntimeException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigParametersServiceImpl implements ConfigParametersService {

    private static final long MILLISECONDS_PER_MINUTE = 60000;

    private static final long REFRESH_INTERVAL_MILLIS = 2 * MILLISECONDS_PER_MINUTE;

    private volatile long lastRefreshTime = 0;

    @Autowired
    ConfigParamRepository configParamRepository;

    private ConcurrentHashMap<String, ConfigParam> configParameters = new ConcurrentHashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public ConfigParam getParameter(String code) {
        refreshIfNeeded();

        ConfigParam configParam = configParameters.get(code);
        if (configParam == null) {
            throw new NbuRuntimeException("Unknown config parameter code: " + code + " !!!");
        }
        return configParam;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConfigParam getParameter(ParameterCode code) {
        return getParameter(code.getCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getParameterValue(String code) {
        return getParameter(code).getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getParameterValue(ParameterCode code) {
        return getParameterValue(code.getCode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ConfigParam> getAllParameters() {
        refreshIfNeeded();

        return new ArrayList<>(configParameters.values());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConfigParam updateParameterValue(String code, String value) {
        ConfigParam param = configParamRepository.findOneByCode(code);
        if (param == null) {
            throw new NbuRuntimeException("Unknown config parameter code!!!");
        }

        param.setValue(value);
        param = configParamRepository.save(param);

        configParameters.put(code, param);
        return param;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConfigParam updateParameterValue(ParameterCode code, String value) {
        return updateParameterValue(code.getCode(), value);
    }

    /**
     * Refresh config parameters cache if needed
     */
    private void refreshIfNeeded() {
        if (configParameters == null || isRefreshNeeded()) {
            lastRefreshTime = System.currentTimeMillis();
            updateConfigParameters();
        }
    }

    /**
     * Check is refresh needed
     * @return Refresh is needed
     */
    private boolean isRefreshNeeded() {
        return System.currentTimeMillis() > lastRefreshTime + REFRESH_INTERVAL_MILLIS;
    }

    /**
     * Refresh config parameters
     */
    private void updateConfigParameters() {
        for (ConfigParam configParam : configParamRepository.findAll()) {
            configParameters.put(configParam.getCode(), configParam);
        }
    }

}
