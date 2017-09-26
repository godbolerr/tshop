package com.kalpak.tworkshop.service.mapper;

import com.kalpak.tworkshop.domain.*;
import com.kalpak.tworkshop.service.dto.ResourceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Resource and its DTO ResourceDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ResourceMapper extends EntityMapper <ResourceDTO, Resource> {
    
    

}
