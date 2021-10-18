package ru.digitalspirit.asaka.applicationlist.service;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalspirit.asaka.applicationlist.dto.ApplicationInfoListDTO;
import ru.digitalspirit.asaka.applicationlist.dto.ColumnFilterDTO;
import ru.digitalspirit.asaka.bpm.model.ColumnCondition;

import java.util.List;

@Service
public class ApplicationInfoService {

    @Autowired
    private MapperFacade mapperFacade;

    @Autowired
    private ApplicationListService applicationListService;

    public ApplicationInfoListDTO getApplicationInfosByLogin(String login, String nfoRole, List<ColumnFilterDTO> filters, Integer page, Integer size) {
        return mapperFacade.map(applicationListService.getByLogin(login, nfoRole, mapperFacade.mapAsList(filters, ColumnCondition.class), page, size), ApplicationInfoListDTO.class);
    }

}
