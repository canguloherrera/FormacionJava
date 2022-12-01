package com.backend.client.criteria;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class ClientPage {
    private int pageNumber = 0;
    private int pageSize = 10;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "name";
}
