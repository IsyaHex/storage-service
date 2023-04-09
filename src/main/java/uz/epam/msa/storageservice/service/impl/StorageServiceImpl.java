package uz.epam.msa.storageservice.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.epam.msa.storageservice.constant.Constants;
import uz.epam.msa.storageservice.domain.Storage;
import uz.epam.msa.storageservice.dto.CreateStorageDTO;
import uz.epam.msa.storageservice.dto.CreateStorageResponseDTO;
import uz.epam.msa.storageservice.dto.DeletedStoragesDTO;
import uz.epam.msa.storageservice.dto.GetStorageDTO;
import uz.epam.msa.storageservice.exception.StorageValidationException;
import uz.epam.msa.storageservice.repository.StorageRepository;
import uz.epam.msa.storageservice.service.StorageService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StorageServiceImpl implements StorageService {

    private final StorageRepository repository;
    private final ModelMapper mapper;

    public StorageServiceImpl(StorageRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CreateStorageResponseDTO createStorage(CreateStorageDTO data) {
        Storage storage;
        CreateStorageResponseDTO createStorageResponseDTO;
        try {
            storage = mapper.map(data, Storage.class);
            storage.setDeleted(false);
            createStorageResponseDTO = mapper.map(repository.save(storage), CreateStorageResponseDTO.class);
        } catch (Exception e) {
            throw new StorageValidationException(Constants.VALIDATION_EXCEPTION);
        }
        return createStorageResponseDTO;
    }

    @Override
    public List<GetStorageDTO> getStorages() {
        return repository.findAll().stream()
                .filter(storage -> !storage.isDeleted())
                .map(storage -> mapper.map(storage, GetStorageDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DeletedStoragesDTO deleteStorages(String ids) {
        DeletedStoragesDTO dto = new DeletedStoragesDTO();
        dto.setIds(Arrays.stream(ids.split(Constants.COMMA_REGEX))
                .filter(id -> id.matches(Constants.NUMBER_REGEX))
                .map(id -> repository.findById(Integer.parseInt(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(storage -> !storage.isDeleted())
                .peek(storage -> storage.setDeleted(true))
                .map(repository::save)
                .map(Storage::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
