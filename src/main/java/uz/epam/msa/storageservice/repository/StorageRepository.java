package uz.epam.msa.storageservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.epam.msa.storageservice.domain.Storage;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Integer> {
}
