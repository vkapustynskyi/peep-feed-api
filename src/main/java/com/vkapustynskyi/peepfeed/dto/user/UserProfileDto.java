package com.vkapustynskyi.peepfeed.dto.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserProfileDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String nickname;

    private LocalDate birthday;

    private String role;

}
