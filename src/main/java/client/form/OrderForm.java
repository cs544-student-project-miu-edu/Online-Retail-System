package client.form;

import jakarta.validation.Valid;

import java.util.List;

public class OrderForm {
    @Valid
    List<ItemLineForm> items;

    public List<ItemLineForm> getItems() {
        return items;
    }
}
