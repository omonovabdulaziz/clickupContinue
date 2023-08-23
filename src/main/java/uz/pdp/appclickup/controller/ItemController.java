package uz.pdp.appclickup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.ItemDTO;
import uz.pdp.appclickup.service.ItemService;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    @Autowired
    ItemService itemService;

    @PostMapping("/addItemToCheckList")
    public HttpEntity<?> addItemToCheckList(@RequestBody ItemDTO itemDTO) {
        ApiResponse apiResponse = itemService.addItemToCheckList(itemDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/deleteItemFromCheckList/{id}")
    public HttpEntity<?> deleteItemFromCheckList(@PathVariable Long id) {
        ApiResponse apiResponse = itemService.deleteItemFromCheckList(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
}
