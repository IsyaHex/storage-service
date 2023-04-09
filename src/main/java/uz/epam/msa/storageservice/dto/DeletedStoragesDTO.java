package uz.epam.msa.storageservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DeletedStoragesDTO {
    @JsonProperty(value = "ids")
    private List<Integer> ids;
}
