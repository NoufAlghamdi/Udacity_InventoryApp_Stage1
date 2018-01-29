package com.example.android.bookstore;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.bookstore.data.BookStoreContract.ProductInfoEntry;
import com.example.android.bookstore.data.BookStoreDbHelper;

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG = MainActivity.class.getSimpleName();

    private BookStoreDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new BookStoreDbHelper(this);

        insertData();
        displayData();
    }

    private void insertData() {
        //Git the data repository in write mode.
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProductInfoEntry.COLUMN_PRODUCT_INFO_BOOK_TITLE, "Zaad Alma'ad fe Hadee Khair Ale'bad");
        values.put(ProductInfoEntry.COLUMN_PRODUCT_INFO_AUTHOR_NAME, "Ibn Qayem Aljawzyah");
        values.put(ProductInfoEntry.COLUMN_PRODUCT_INFO_PRICE, 60);
        values.put(ProductInfoEntry.COLUMN_PRODUCT_INFO_AVAILABLE_QUANTITY, 13);
        values.put(ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIER_NAME, "Ar-Resalah");
        values.put(ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIES_EMAIL, "books@resalah.com");
        values.put(ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIER_PHONE_NUMBER, "0500005555");

        long newRowId;
        newRowId = db.insert(ProductInfoEntry.TABLE_NAME, null, values);

        if (newRowId == -1) {
            Log.e(LOG_TAG, "Error with saving product info");
        } else {
            Log.i(LOG_TAG, "Product saved with ID " + newRowId);
        }
    }

    private Cursor queryData(){

        // Create and/or open a database to read from it
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                ProductInfoEntry._ID,
                ProductInfoEntry.COLUMN_PRODUCT_INFO_BOOK_TITLE,
                ProductInfoEntry.COLUMN_PRODUCT_INFO_AUTHOR_NAME,
                ProductInfoEntry.COLUMN_PRODUCT_INFO_PRICE,
                ProductInfoEntry.COLUMN_PRODUCT_INFO_AVAILABLE_QUANTITY,
                ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIER_NAME,
                ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIES_EMAIL,
                ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIER_PHONE_NUMBER
        };

        Cursor cursor = db.query(
                ProductInfoEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        return cursor;
    }

    private void displayData() {

        Cursor cursor = queryData();

        try {

            Log.i(LOG_TAG, "Number of rows on productInfo table = " + cursor.getCount());

            Log.i(LOG_TAG, ProductInfoEntry._ID
                    + "\t" + ProductInfoEntry.COLUMN_PRODUCT_INFO_BOOK_TITLE
                    + "\t" + ProductInfoEntry.COLUMN_PRODUCT_INFO_AUTHOR_NAME
                    + "\t" + ProductInfoEntry.COLUMN_PRODUCT_INFO_PRICE
                    + "\t" + ProductInfoEntry.COLUMN_PRODUCT_INFO_AVAILABLE_QUANTITY
                    + "\t" + ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIER_NAME
                    + "\t" + ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIES_EMAIL
                    + "\t" + ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIER_PHONE_NUMBER
            );

            int idColumnIndex = cursor.getColumnIndex(ProductInfoEntry._ID);
            int bookTitleColumnIndex = cursor.getColumnIndex(ProductInfoEntry.COLUMN_PRODUCT_INFO_BOOK_TITLE);
            int authorNameColumnIndex = cursor.getColumnIndex(ProductInfoEntry.COLUMN_PRODUCT_INFO_AUTHOR_NAME);
            int priceColumnIndex = cursor.getColumnIndex(ProductInfoEntry.COLUMN_PRODUCT_INFO_PRICE);
            int availableQuantityColumnIndex = cursor.getColumnIndex(ProductInfoEntry.COLUMN_PRODUCT_INFO_AVAILABLE_QUANTITY);
            int supplierNameColumnIndex = cursor.getColumnIndex(ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIER_NAME);
            int supplierEmailColumnIndex = cursor.getColumnIndex(ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIES_EMAIL);
            int supplierPhoneNumberColumnIndex = cursor.getColumnIndex(ProductInfoEntry.COLUMN_PRODUCT_INFO_SUPPLIER_PHONE_NUMBER);

            if (cursor.moveToFirst()) {
                do {
                    int currentId = cursor.getInt(idColumnIndex);
                    String currentBookTitle = cursor.getString(bookTitleColumnIndex);
                    String currentAuthorName = cursor.getString(authorNameColumnIndex);
                    int currentPrice = cursor.getInt(priceColumnIndex);
                    int currentAvailableQuantity = cursor.getInt(availableQuantityColumnIndex);
                    String currentSupplierName = cursor.getString(supplierNameColumnIndex);
                    String currentSupplierEmail = cursor.getString(supplierEmailColumnIndex);
                    String currentSupplierPhoneNumber = cursor.getString(supplierPhoneNumberColumnIndex);

                    Log.i(LOG_TAG, currentId
                            + "\t" + currentBookTitle
                            + "\t" + currentAuthorName
                            + "\t" + currentPrice
                            + "\t" + currentAvailableQuantity
                            + "\t" + currentSupplierName
                            + "\t" + currentSupplierEmail
                            + "\t" + currentSupplierPhoneNumber
                    );
                } while (cursor.moveToNext());
            }
        }finally {
            cursor.close();
        }
    }
}
