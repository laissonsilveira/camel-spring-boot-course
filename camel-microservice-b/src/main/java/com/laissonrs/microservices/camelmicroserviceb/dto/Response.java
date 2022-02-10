package com.laissonrs.microservices.camelmicroserviceb.dto;

public class Response {

    private String data;

    public Response(final String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(final String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{ data='" + data + "'}";
    }

}