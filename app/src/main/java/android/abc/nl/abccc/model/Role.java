package android.abc.nl.abccc.model;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {

    public enum Authority {
        ROLE_ADMIN, ROLE_USER
    }

    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }


}
