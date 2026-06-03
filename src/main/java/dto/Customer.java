package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Data
@AllArgsConstructor
public class Customer {
    String firstName;
    String lastName;
    String zipPostalCode;
}

