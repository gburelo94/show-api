package com.gburelo.shows_api.controllers;

import com.gburelo.shows_api.domain.dto.requests.ShowRequest;
import com.gburelo.shows_api.services.ShowService;
import com.gburelo.shows_api.utilities.ApiResponse;
import com.gburelo.shows_api.utilities.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shows")
public class ShowController {

    @Autowired private ShowService showService;

    @GetMapping
    public ApiResponse getShows(@ModelAttribute Pagination request) {
        return showService.getShows(request);
    }

    @PostMapping
    public ApiResponse createShow(@RequestBody ShowRequest request) {
        return showService.createShow(request);
    }

    @PutMapping("/{id}")
    public ApiResponse updateShow(
            @PathVariable(value = "id") int showId,
            @RequestBody ShowRequest request) {
        return showService.updateShow(showId, request);
    }
}
