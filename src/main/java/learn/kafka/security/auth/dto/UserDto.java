package learn.kafka.security.auth.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import learn.kafka.security.auth.utils.annotations.ValidEmail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    private String password;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;
}
