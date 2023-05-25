//package client.form;
//
//import com.retail.ItemService.ResponseError.NotAcceptable;
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "items")
//@Data
//@Inheritance(strategy = InheritanceType.JOINED)
//public abstract class Item {
//    @Id
//    @GeneratedValue
//    private int itemID;
//    private String name;
//    private String description;
//    protected double price;
//    @Transient
//    private byte[] image;
//    private String barcodeNumber;
//    private int quantity;
//
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "itemID")
//    private List<ReviewForm> reviews;
//
//    public Item() {
//    }
//
//
//
//
//}
