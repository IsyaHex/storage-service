package uz.epam.msa.storageservice.exception;

public class InternalServerError extends Error {


    public InternalServerError() {
    }

    public InternalServerError(String message) {
        super(message);
    }
}
