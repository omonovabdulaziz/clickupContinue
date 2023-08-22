package uz.pdp.appclickup.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appclickup.entity.Status;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.repository.StatusRepository;
import uz.pdp.appclickup.service.StatusService;

import java.util.List;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    StatusRepository statusRepository;

    @Override
    public ApiResponse addStatus(Status status) {
        if (statusRepository.existsByNameAndSpaceId(status.getName(), status.getSpace().getId()))
            return new ApiResponse("Bunday nomli status ushbu spacega qo'shildi", false);
        statusRepository.save(status);
        return new ApiResponse("Ushbu status ushbu spacega saqlandi", true);
    }

    @Override
    public ApiResponse deleteStatus(Long id) {
        try {
            statusRepository.deleteById(id);
            return new ApiResponse("O'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("!Xatolik", false);
        }
    }

    @Override
    public List<Status> getListStatusBySpaceId(Long id) {
        return statusRepository.findAllBySpaceId(id);
    }
}
