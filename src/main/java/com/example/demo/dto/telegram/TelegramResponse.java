package com.example.demo.dto.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TelegramResponse {
    private boolean ok;
    private List<Update> result;

    // getters and setters
    public boolean isOk() { return ok; }
    public void setOk(boolean ok) { this.ok = ok; }
    public List<Update> getResult() { return result; }
    public void setResult(List<Update> result) { this.result = result; }
}