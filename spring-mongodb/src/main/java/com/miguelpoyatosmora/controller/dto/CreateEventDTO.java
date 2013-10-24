package com.miguelpoyatosmora.controller.dto;

public  class CreateEventDTO {

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    private  String name;
    private Long time;

    public CreateEventDTO() {
    }



    @Override
    public String toString() {
        return "CreateEventDTO{" +
                "name='" + name + '\'' +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreateEventDTO that = (CreateEventDTO) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    public Long getTime() {

        return time;
    }

    public String getName() {

        return name;
    }
}
