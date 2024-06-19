package org.project.demo_picture_service.domain.exception;

public class ImageUploadException extends RuntimeException {
    public ImageUploadException(final String message) {
        super(message);
    }
}
