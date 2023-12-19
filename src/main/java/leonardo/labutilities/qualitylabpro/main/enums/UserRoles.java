package leonardo.labutilities.qualitylabpro.main.enums;

import lombok.Getter;

@Getter
public enum UserRoles {
    USER("USER"),
    ADMIN("ADMIN");
    private final String role;
    UserRoles(String role) {
        this.role = role;
    }
}