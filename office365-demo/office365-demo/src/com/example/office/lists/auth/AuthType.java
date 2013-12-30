package com.example.office.lists.auth;

/**
 * Authentication type for connections.
 */
public enum AuthType {
    UNDEFINED, COOKIE, BASIC, OAUTH, NTLM, AD;

    /**
     * @return String with authentication type.
     */
    public String getAuthType() {
        return String.valueOf(this.ordinal() - 1);
    }

    /**
     * Returns Authentication type based on its string name.
     *
     * @param name Auth scheme name.
     *
     * @return Authentication type based on its string name.
     */
    public AuthType fromString(String name) {
        for (int i = 0; i < AuthType.values().length; i++) {
            AuthType type = AuthType.values()[i];
            if (type.toString().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return UNDEFINED;
    }

    public boolean is(AuthType type) {
        if(type == null) return false;
        return this.name().equals(type.name());
    }
}