package com.epam.engx.jmp.cloud.bank.impl;

import java.util.function.Supplier;

@FunctionalInterface
public interface CardNumberSupplier extends Supplier<String> {
}
