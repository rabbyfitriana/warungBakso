package com.tubes;

import java.util.Arrays;

/**
 * ShoppingMain Program utama dari Shopping Cart dengan GUI
 */

public final class ShoppingMain {
  /**
   * List Item yang ada di toko
   */
  private static final Item[] ITEMS = new Item[] {
      new Item("MIE BAKSO BIASA", 15000, 10, 100000),
      new Item("MIE BAKSO TAMBAH TETELAN", 20000, 10, 150000),
      new Item("MIE BAKSO TELOR", 25000, 10, 200000),
      new Item("MIE AYAM BAKSO", 25000),
      new Item("PAKET BERDUA", 50000),
      new Item("PAKET KOMPLIT", 120000),
      new Item("ES TEH SOSRO", 5000, 10, 40000),
      new Item("ES JERUK", 6000),
      new Item("TEH TAWAR", 3000),
      new Item("KERUPUK", 1000, 3, 2000) };

  /**
   * Pakai constructor Private, supaya tidak ada instance
   */
  private ShoppingMain() {
  }

  // @param the_args untuk mendefinisikan jika main method merupakan string
  public static void main(final String... the_args) {
    new ShoppingFrame(Arrays.asList(ITEMS));
  }
}
