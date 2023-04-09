package uz.epam.msa.storageservice.exception;

public class StorageValidationException extends RuntimeException{

    public StorageValidationException(){}

    public StorageValidationException(String message) {
        super(message);
    }

}
