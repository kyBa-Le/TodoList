package com.example.todolist.API.Restful.Dto.Base;

import java.util.List;

public record Error(String field, List<ErrorDetail> errorDetails) {
}