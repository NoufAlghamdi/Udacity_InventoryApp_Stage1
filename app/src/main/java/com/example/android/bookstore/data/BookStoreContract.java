package com.example.android.bookstore.data;

import android.provider.BaseColumns;

public final class BookStoreContract {

    public static abstract class ProductInfoEntry implements BaseColumns {

        public static final String TABLE_NAME = "productInfo";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PRODUCT_INFO_BOOK_TITLE = "bookTitle";
        public static final String COLUMN_PRODUCT_INFO_AUTHOR_NAME = "authorName";
        public static final String COLUMN_PRODUCT_INFO_IMAGE = "image";
        public static final String COLUMN_PRODUCT_INFO_PRICE = "price";
        public static final String COLUMN_PRODUCT_INFO_AVAILABLE_QUANTITY = "availableQuantity";
        public static final String COLUMN_PRODUCT_INFO_SUPPLIER_NAME = "supplierName";
        public static final String COLUMN_PRODUCT_INFO_SUPPLIES_EMAIL = "supplierEmail";
        public static final String COLUMN_PRODUCT_INFO_SUPPLIER_PHONE_NUMBER = "supplierPhoneNumber";
    }
}
