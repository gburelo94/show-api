package com.gburelo.shows_api.utilities;

import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class ApiResponse {

    private int status;

    private String message;

    private Object body;

    private String error;
}
