package com.tubes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ini adalah program untuk pembuatan(proses) shooping cart
 */

public class ShoppingCart {

  /**
   * Menentukan program untuk diskon yang diberikan.
   */
  private static final double DISCOUNT = 0.7;

  /**
   * fungsi boolean untuk menentukan apakah program diskon dijalankan/tidak.
   */
  private boolean my_discount;
  private final List<ItemOrder> my_item_list = new ArrayList<ItemOrder>();

  public ShoppingCart() {
    my_discount = false;
  }

  /**
   * Proses Pengumpulan barang-barang yang akan dibeli
   * 
   * @param the_item_order barang-barang yang dibeli
   */
  public final void add(final ItemOrder the_item_order) {

    for (int i = 0; i < my_item_list.size(); i++) {
      if (((ItemOrder) my_item_list.get(i)).getItem().equals(the_item_order.getItem())) {
        my_item_list.set(i, the_item_order);
        return;
      }
    }
    my_item_list.add(the_item_order);

  }

  /**
   * Proses jika barang akan didiskonkan
   * 
   * @param the_discount variable untuk method diskon
   */
  public void setDiscount(final boolean the_discount) {
    my_discount = the_discount;
  }

  /**
   * mengembalikan total harga.
   */
  public double getTotal() {

    double total = 0.0;

    for (final Iterator<ItemOrder> iterator = my_item_list.iterator(); iterator.hasNext();) {
      final ItemOrder next_item = (ItemOrder) iterator.next();
      total = total + next_item.getPrice();
    }

    if (my_discount) {
      total = total * DISCOUNT;
    }

    return total;
  }
}
