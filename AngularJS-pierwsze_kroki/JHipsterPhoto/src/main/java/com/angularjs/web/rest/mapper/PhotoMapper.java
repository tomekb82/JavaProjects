package com.angularjs.web.rest.mapper;

import com.angularjs.domain.*;
import com.angularjs.web.rest.dto.PhotoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Photo and its DTO PhotoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PhotoMapper {

    PhotoDTO photoToPhotoDTO(Photo photo);

    Photo photoDTOToPhoto(PhotoDTO photoDTO);
}
