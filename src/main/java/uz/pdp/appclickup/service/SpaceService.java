package uz.pdp.appclickup.service;


import uz.pdp.appclickup.entity.Space;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.SpaceDTO;

import java.util.List;

public interface SpaceService {
    ApiResponse addSpace(SpaceDTO spaceServiceDTO);

    List<Space> getSpace();

    ApiResponse updateSpace(Long id, SpaceDTO spaceDTO);
    ApiResponse deleteSpace(Long id);
    Space getSpaceById(Long id);
}
