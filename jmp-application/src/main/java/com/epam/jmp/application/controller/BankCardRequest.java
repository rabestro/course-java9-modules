package com.epam.jmp.application.controller;

import com.epam.jmp.dto.BankCardType;
import com.epam.jmp.dto.User;

public record BankCardRequest(User user, BankCardType type) {
}
