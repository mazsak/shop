package api.shop.shop.utils;

import api.shop.shop.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemsResponse {
    List<Product> products;
    int currentPage;
    int pageSize;
    int pageAmount;
}
