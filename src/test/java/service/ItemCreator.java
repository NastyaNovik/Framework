package service;

import model.Item;
import static util.Resolver.resolveTemplate;

public class ItemCreator {

    public static final String ITEM_URL="test.data.%s.item.url";
    public static final String ITEM_NAME = "test.data.%s.item.name";
    public static final String ITEM_SIZE = "test.data.%s.item.size";
    public static final String ITEM_COST = "test.data.%s.item.cost";

    public static Item withCredentialsFromProperty(String orderNumber){
        orderNumber = orderNumber.toLowerCase();
        String itemUrl = resolveTemplate(ITEM_URL, orderNumber);
        String itemName = resolveTemplate(ITEM_NAME, orderNumber);
        String itemSize = resolveTemplate(ITEM_SIZE, orderNumber);
        String itemCost = resolveTemplate(ITEM_COST, orderNumber);
        return new Item(TestDataReader.getTestData(itemUrl),TestDataReader.getTestData(itemName),TestDataReader.getTestData(itemSize),Double.parseDouble(TestDataReader.getTestData(itemCost)));
    }
}
