package exercise.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

// BEGIN
@Getter
@Setter
public class GuestCreateDTO {
    @NotEmpty(message = "is not empty")
    private String name;
    @Email(message = "wrong email")
    private String email;
    @Size(min = 12, max = 14, message = "wrong phone number")
    @Pattern(regexp = "^\\+\\d{11,13}")
    private String phoneNumber;
    @Size(min = 4, max = 4, message = "wrong club card")
    private String clubCard;
    @Future(message = "club card getting old")
    private LocalDate cardValidUntil;
}
// END
