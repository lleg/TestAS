package ru.digitalspirit.asaka.bpm.service;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalspirit.asaka.cache.service.CacheService;
import ru.digitalspirit.asaka.bpm.dto.UserContextDataDTO;
import ru.digitalspirit.asaka.bpm.dto.ResponseResult;
import ru.digitalspirit.asaka.bpm.dto.SessionStateDTO;
import ru.digitalspirit.asaka.bpm.model.UserContextData;

@Service
public class StartDataService {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private DirectoryService directoryService;

    public UserContextDataDTO startData(String sessionId) {
        UserContextData data;
        String userEmail;
        try {
            if (!cacheService.isAlive(sessionId)) {
                return new UserContextDataDTO();
            }
            data = (UserContextData) cacheService.get(sessionId);
            userEmail = directoryService.getUserEmail(data.getUserLogin());
        } catch (Exception e) {
            e.printStackTrace();
            return new UserContextDataDTO();
        }

        UserContextDataDTO dataDTO = mapperFacade.map(data, UserContextDataDTO.class);
        dataDTO.setEmail(userEmail);

        return dataDTO;
    }

    public SessionStateDTO isAlive(String sessionId) {
        SessionStateDTO sessionState = new SessionStateDTO();
        sessionState.setAlive(cacheService.isAlive(sessionId));
        return sessionState;
    }

    public ResponseResult delete(String sessionId) {
        ResponseResult result = new ResponseResult();
        cacheService.deactivate(sessionId);
        result.setSuccess(true);
        return result;
    }
}
