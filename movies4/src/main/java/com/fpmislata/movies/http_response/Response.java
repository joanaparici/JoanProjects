package com.fpmislata.movies.http_response;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.servlet.http.HttpServletRequest;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL) // No incluirá atributos nulos en el JSON
@JsonPropertyOrder({ "metadata", "pagination", "data"})
public class Response {

    private Object data;


    @JsonProperty("Pagination data")
    private Map<String, Object> pagination;

    @JsonProperty("Additional data")
    private Map<String, Object> metadata;

    public Response(Object data) {
        this.data = data;
    }

    public Response(Object data, int totalRecords) {
        this.data = data;
        this.metadata = new HashMap<>();
        this.metadata.put("total_records", totalRecords);
    }

    public Response(Object data, int totalRecords, int page, int pageSize) {
        this.data = data;
        this.metadata = new HashMap<>();
        this.metadata.put("total_records", totalRecords);
        this.paginate(page, pageSize, totalRecords);
    }

    public Response paginate(int page, int pageSize, int totalRecords) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String url = request.getRequestURL().toString();
        this.pagination = new HashMap<>();
        this.pagination.put("page", page);
        this.pagination.put("page size", pageSize);
        int totalPages = (int) (Math.ceil((double) totalRecords / pageSize));
        this.pagination.put("total pages", totalPages);
        if(page > 1 && totalPages > 1)
            this.pagination.put("previous", url + "movies?page=" + (page - 1));
        if(page < totalPages)
            this.pagination.put("next", url + "movies?page=" + (page + 1));
        return this;
    }
}


