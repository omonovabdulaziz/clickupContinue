package uz.pdp.appclickup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appclickup.entity.Space;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.SpaceDTO;
import uz.pdp.appclickup.service.SpaceService;

import java.util.List;

@RestController
@RequestMapping("/api/space")
public class SpaceController {
    @Autowired
    SpaceService spaceService;

    @PostMapping("/addSpace")
    public ResponseEntity<?> addSpace(@RequestBody SpaceDTO spaceServiceDTO) {
        ApiResponse apiResponse = spaceService.addSpace(spaceServiceDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @GetMapping("/getSpaces")
    public List<Space> getListSpace() {
        return spaceService.getSpace();
    }

    @GetMapping("/getSpace/{id}")
    public Space getSpace(@PathVariable Long id) {
        return spaceService.getSpaceById(id);
    }

    @PutMapping("/updateSpace/{id}")
    public ResponseEntity<?> updateSpace(@PathVariable Long id, SpaceDTO spaceDTO) {
        ApiResponse apiResponse = spaceService.updateSpace(id, spaceDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/deleteSpace/{id}")
    public ResponseEntity<?> deleteSpace(@PathVariable Long id) {
        ApiResponse apiResponse = spaceService.deleteSpace(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }


}
