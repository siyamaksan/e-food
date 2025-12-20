package com.example.demo.dto.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendMessageRequest {

    @JsonProperty("chat_id")
    private Long chatId;

    private String text;

    @JsonProperty("parse_mode")
    private String parseMode;
}
