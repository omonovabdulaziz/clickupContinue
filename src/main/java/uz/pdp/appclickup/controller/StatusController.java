package uz.pdp.appclickup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appclickup.entity.Status;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.service.StatusService;

import java.util.List;

@RestController
@RequestMapping("/api/status")
public class StatusController {
    @Autowired
    StatusService statusService;

    @PostMapping("/addStatus")
    public ResponseEntity<?> addStatus(@RequestBody Status status) {
        ApiResponse apiResponse = statusService.addStatus(status);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.ACCEPTED).body(apiResponse);
    }

    @DeleteMapping("/deleteStatus/{id}")
    public ResponseEntity<?> deleteStatus(@PathVariable Long id) {
        ApiResponse apiResponse = statusService.deleteStatus(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/getStatusBySpaceId/{id}")
    public List<Status> getListStatusBySpaceId(@PathVariable Long id) {
        return statusService.getListStatusBySpaceId(id);
    }


}
