package net.muslu.mros.Api;

import android.util.Log;

public final class LinkHelper {
    private static final String API_URI = "https://mros.api.muslu.net/v1/";
    public static String GetLink(String qrcode, LinkType linkType){
        String result = "";
        switch (linkType){
            case FETCH_RESTAURANT_WITH_TABLE:
                result = API_URI + "table/" + qrcode;
                break;
            case LIST_PRODUCT_CATEGORIES:
                result= API_URI + "category/restaurant/" +qrcode;
                break;
            case RESTAURANT_IMG:
                result= API_URI + "restaurant/image/" +qrcode; // id
                break;
            case LIST_PRODUCTS:
                result= API_URI + "product/categoryProducts/"+ qrcode;
                break;
            case CUSTOMER_FEEDS:
                result= API_URI + "/feeds/restaurant/"+ qrcode; // id
                break;

        }
        Log.v("URL_OUTPUT", result);
        return result;
    }

    public enum LinkType{
        FETCH_RESTAURANT_WITH_TABLE,
        LIST_PRODUCT_CATEGORIES,
        LIST_PRODUCTS,
        RESTAURANT_IMG,
        CUSTOMER_FEEDS
    }
}
