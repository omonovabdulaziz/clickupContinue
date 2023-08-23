package uz.pdp.appclickup.service;

import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.ItemDTO;

public interface ItemService {
    ApiResponse addItemToCheckList(ItemDTO itemDTO);
    ApiResponse deleteItemFromCheckList(Long id);
}
