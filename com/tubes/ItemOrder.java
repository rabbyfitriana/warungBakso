package com.tubes;
// Program Pemrosesan barang-barang yang di order

public class ItemOrder {

  private final Item my_item;

  private final int my_quantity;

  /**
   * @param the_item_name         variable untuk nama-nama barang
   * @param the_quantity_of_items variable untuk jumlah(harga) barang
   */
  public ItemOrder(final Item the_item_name, final int the_quantity_of_items) {
    my_item = the_item_name;
    my_quantity = the_quantity_of_items;
  }

  /**
   * Mengembalikan item(barang) untuk mendapatkan harga brang
   * 
   * @return my_item.(priceFor(my_quantity)
   */
  public double getPrice() {

    return my_item.priceFor(my_quantity);
  }

  /**
   * dikembalikan ke barang
   * 
   * @return my_item
   */
  public Item getItem() {

    return my_item;
  }
}
