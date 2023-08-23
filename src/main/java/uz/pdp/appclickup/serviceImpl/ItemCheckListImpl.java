package uz.pdp.appclickup.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appclickup.entity.CheckList;
import uz.pdp.appclickup.entity.Item;
import uz.pdp.appclickup.payload.ApiResponse;
import uz.pdp.appclickup.payload.ItemDTO;
import uz.pdp.appclickup.repository.ChecklistRepository;
import uz.pdp.appclickup.repository.ItemRepository;
import uz.pdp.appclickup.service.ItemService;

import java.io.Serial;
import java.util.Optional;

@Service
public class ItemCheckListImpl implements ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ChecklistRepository checklistRepository;

    @Override
    public ApiResponse addItemToCheckList(ItemDTO itemDTO) {
        Optional<CheckList> optionalCheckList = checklistRepository.findById(itemDTO.getCheckListId());
        if (!optionalCheckList.isPresent())
            return new ApiResponse("Topilmadi", false);
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setCheckList(optionalCheckList.get());
        itemRepository.save(item);
        return new ApiResponse("Saved item", true);
    }

    @Override
    public ApiResponse deleteItemFromCheckList(Long id) {
        try {
            itemRepository.deleteById(id);
            return new ApiResponse("Deleted", true);
        } catch (Exception e) {
            return new ApiResponse("Xatolikk", false);
        }
    }
}
