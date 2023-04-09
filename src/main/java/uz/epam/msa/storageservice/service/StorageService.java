package uz.epam.msa.storageservice.service;

import uz.epam.msa.storageservice.dto.CreateStorageResponseDTO;
import uz.epam.msa.storageservice.dto.DeletedStoragesDTO;
import uz.epam.msa.storageservice.dto.CreateStorageDTO;
import uz.epam.msa.storageservice.dto.GetStorageDTO;
import uz.epam.msa.storageservice.exception.StorageValidationException;

import java.util.List;

public interface StorageService {
    CreateStorageResponseDTO createStorage(CreateStorageDTO data);
    List<GetStorageDTO> getStorages();
    DeletedStoragesDTO deleteStorages(String ids);
}
