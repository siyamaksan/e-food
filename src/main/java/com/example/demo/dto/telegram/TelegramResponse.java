package com.example.demo.dto.telegram;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class TelegramResponse {
    private boolean ok;
    private List<Update> result;

    public boolean isOk() { return ok; }
    public void setOk(boolean ok) { this.ok = ok; }
    public List<Update> getResult() { return result; }
    public void setResult(List<Update> result) { this.result = result; }
}