package com.tubes;

import java.text.NumberFormat;

//Program memproses barang-barang (items) yang akan ditampilkan

public class Item {

  /**
   * membuat tipe data untuk nama barang (string)
   */
  private final String my_item_names;

  /**
   * membuat tipe data double untuk harga barang
   */
  private final double my_price;

  /**
   * input tipe data untuk harga barang banyak.
   */
  private final int my_bulk;

  /**
   * double untuk tipe data harga barang banyak.
   */
  private final double my_bulk_cost;

  /**
   * NumberFormat my_formatted_numbers untuk menampilkan mata uang yang akan
   * ditampilkan.
   */
  private final NumberFormat my_formatted_numbers;

  /**
   * @param the_item_name  variable nama barang
   * @param the_item_price variable untuk harga barang
   */
  public Item(final String the_item_name, final double the_item_price) {

    this(the_item_name, the_item_price, 0, 0.0);

  }

  /**
   * @param the_quantity_in_bulk variable untuk jumlah barang banyak
   * @param the_price_in_bulk    variable untuk harga banyak barang
   */
  public Item(final String the_item_name, final double the_item_price,
      final int the_quantity_in_bulk, final double the_price_in_bulk) {
    my_item_names = the_item_name;
    my_price = the_item_price;
    my_bulk = the_quantity_in_bulk;
    my_bulk_cost = the_price_in_bulk;
    my_formatted_numbers = NumberFormat.getCurrencyInstance();
  }

  /**
   * @param a_quantity varibale jumlah barang yang ditentukan.
   * @return price
   */
  public double priceFor(final int a_quantity) {

    final int quantity = a_quantity;
    double bulk_num;

    if (quantity >= my_bulk && my_bulk > 0) {
      final int bulk_quantity = quantity / my_bulk;
      final int reg_quantity = quantity % my_bulk;
      bulk_num = bulk_quantity * my_bulk_cost + reg_quantity * my_price;
    } else {
      bulk_num = quantity * my_price;
    }
    return bulk_num;
  }

  // proses method tipe data string

  public String toString() {
    StringBuilder stringbuilder;
    stringbuilder = new StringBuilder().append(my_item_names);
    stringbuilder.append(", ");
    stringbuilder.append(my_formatted_numbers.format(my_price));
    if (my_bulk > 0) {
      stringbuilder.append(" (");
      stringbuilder.append(my_bulk);
      stringbuilder.append(" for ");
      stringbuilder.append(my_formatted_numbers.format(my_bulk_cost));
      stringbuilder.append(")");
    }
    return stringbuilder.toString();
  }

  public boolean equals(final Object the_other) {
    final Item check = (Item) the_other;
    boolean temporary_checker;

    if (check.my_item_names != null &&
        check.my_item_names.equals(my_item_names) &&
        check.my_price == my_price &&
        check.my_bulk == my_bulk &&
        check.my_bulk_cost == my_bulk_cost) {
      temporary_checker = true;
    } else {
      temporary_checker = false;
    }

    return temporary_checker;

  }

  public int hashCode() {

    return my_item_names.hashCode();
  }
}
