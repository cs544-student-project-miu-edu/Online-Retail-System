package client.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@AllArgsConstructor
@NoArgsConstructor
public class ItemLineResponse {

    private int id;

    private ItemResponse item;

    private int quantity;
    private double discount;


}
