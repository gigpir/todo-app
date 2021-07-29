package it.polito.det.springTemplate.authentication;

import it.polito.det.springTemplate.constraints.FieldMatch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "Le password devono corrispondere"),
})
public class SignUpRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String username;

    @Email(message = "L'email deve essere valida")
    private String email;

    @NotBlank
    @Size(min=8)
    private String password;

    @NotBlank
    @Size(min=8)
    private String confirmPassword;
}
