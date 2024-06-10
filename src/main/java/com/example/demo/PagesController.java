package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PagesController {

    private double xValue = 0;

    @GetMapping("/get_x")
    public XValueResponse getXValue() {
        return new XValueResponse(xValue);
    }

    @PostMapping("/set_x")
    public MessageResponse setXValue(@RequestBody XValueRequest request) {
        this.xValue = request.getX();
        return new MessageResponse("X value set successfully");
    }
}

class XValueResponse {
    private double x;

    public XValueResponse(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }
}

class XValueRequest {
    private double x;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }
}

class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
