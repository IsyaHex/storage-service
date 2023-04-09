package uz.epam.msa.storageservice.dto;

import lombok.Data;

@Data
public class CreateStorageDTO {
    private String storageType;
    private String bucket;
    private String path;
}
