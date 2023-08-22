package uz.pdp.appclickup.service;

import uz.pdp.appclickup.entity.Status;
import uz.pdp.appclickup.payload.ApiResponse;

import java.util.List;

public interface StatusService {
    ApiResponse addStatus(Status status);

    ApiResponse deleteStatus(Long id);

    List<Status> getListStatusBySpaceId(Long id);
}
