package com.kalpak.tworkshop.service.mapper;

import com.kalpak.tworkshop.domain.*;
import com.kalpak.tworkshop.service.dto.TResourceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TResource and its DTO TResourceDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TResourceMapper extends EntityMapper <TResourceDTO, TResource> {
    
    

}
