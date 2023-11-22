package com.fpmislata.JoanAparici1evalExam.http_response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@Getter
@Setter
@JsonPropertyOrder({ "totalRecords", "pagination", "data"})
@JsonInclude(JsonInclude.Include.NON_NULL) // No incluirá atributos nulos en el JSON
@Builder
public class Response {

    private Object data;

}
