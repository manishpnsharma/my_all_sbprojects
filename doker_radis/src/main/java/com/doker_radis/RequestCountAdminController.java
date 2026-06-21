package com.doker_radis;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/admin/request-count")
public class RequestCountAdminController {
    private final RequestCountService requestCountService;
    public RequestCountAdminController(RequestCountService requestCountService) {
        this.requestCountService = requestCountService;
    }

    @GetMapping
    public Map<String, String> getAllRequestCounts() {
        return requestCountService.getAllCounts();
    }

    @GetMapping("/single")
    public Map<String, String> getSingleRequestCount(@RequestParam String method,
                                                     @RequestParam String uri) {

        String count = requestCountService.getCount(method, uri);

        return Map.of(
                "method", method,
                "uri", uri,
                "count", count
        );
    }

    @DeleteMapping("/single")
    public Map<String, String> resetSingleRequestCount(@RequestParam String method,
                                                       @RequestParam String uri) {

        requestCountService.resetCount(method, uri);

        return Map.of(
                "message", "Request count reset successfully",
                "method", method,
                "uri", uri
        );
    }

    @DeleteMapping
    public Map<String, String> resetAllRequestCounts() {
        requestCountService.resetAllCounts();

        return Map.of("message", "All request counts reset successfully");
    }
}