package uz.epam.msa.storageservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.epam.msa.storageservice.dto.CreateStorageDTO;
import uz.epam.msa.storageservice.dto.CreateStorageResponseDTO;
import uz.epam.msa.storageservice.dto.DeletedStoragesDTO;
import uz.epam.msa.storageservice.dto.GetStorageDTO;
import uz.epam.msa.storageservice.service.StorageService;

import javax.validation.constraints.Max;
import java.util.List;

@RestController
@RequestMapping("/storages")
public class StorageController {
    private final StorageService service;

    public StorageController(StorageService service) {
        this.service = service;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateStorageResponseDTO> uploadSong(@RequestBody CreateStorageDTO dto) {
        CreateStorageResponseDTO response = service.createStorage(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetStorageDTO> getSongs() {
        return service.getStorages();
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public DeletedStoragesDTO deleteSongs(@RequestParam(value = "id") @Max(200) String ids) {
        return service.deleteStorages(ids);
    }
}
