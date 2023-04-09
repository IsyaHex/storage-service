package uz.epam.msa.storageservice.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String storageType;
    @NotNull
    private String bucket;
    @NotNull
    private String path;
    private boolean deleted;
}
