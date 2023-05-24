package com.vkapustynskyi.peepfeed.authentication.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

}
