package com.miguelpoyatosmora.controller.dto;

public final class RequestErrorDTO {

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    private String errorDescription;

    public RequestErrorDTO(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RequestErrorDTO that = (RequestErrorDTO) o;

        if (errorDescription != null ? !errorDescription.equals(that.errorDescription) : that.errorDescription != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return errorDescription != null ? errorDescription.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RequestErrorDTO{" +
                "errorDescription='" + errorDescription + '\'' +
                '}';
    }
}
