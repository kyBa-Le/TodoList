package com.example.todolist.API.Restful.Dto.Base;

public record ResponseWithData<T>(String message, T data) {
}