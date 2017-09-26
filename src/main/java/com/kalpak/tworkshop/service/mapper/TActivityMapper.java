package com.kalpak.tworkshop.service.mapper;

import com.kalpak.tworkshop.domain.*;
import com.kalpak.tworkshop.service.dto.TActivityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TActivity and its DTO TActivityDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TActivityMapper extends EntityMapper <TActivityDTO, TActivity> {
    
    

}
