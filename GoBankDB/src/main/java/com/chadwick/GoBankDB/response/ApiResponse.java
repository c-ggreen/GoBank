package com.chadwick.GoBankDB.response;

import java.time.ZonedDateTime;

public record ApiResponse(String message, int code, Object data, ZonedDateTime timestamp) {
}
