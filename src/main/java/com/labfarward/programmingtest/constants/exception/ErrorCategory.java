package com.labfarward.programmingtest.constants.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCategory {
    VALIDATION("Validation"),
    SYSTEM("System"),
    BALANCE("Balance"),
    AUTHORIZATION("Authorization");

    private String description;

    @Override
    public String toString() {
        return this.description;
    }
}
