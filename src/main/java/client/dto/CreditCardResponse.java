package client.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditCardResponse {

    private int id;
    private String number;
    private String expirationDate;
    private String securityCode;
}
