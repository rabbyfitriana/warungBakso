package com.tubes;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.NumberFormat;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * ShoppingFrame, Program untuk tampilan GUI aplikasi Shopping Cart.
 */
public final class ShoppingFrame extends JFrame {
  /**
   * Serialization ID, di perlukan untuk merepresentasikan bagian - bagian dari
   * program agar termasuk kedalam objek data.
   */
  private static final long serialVersionUID = 0;

  /**
   * Membuat Bidang untuk text agar bisa ditampilkan di GUI.
   */
  private static final int TEXT_FIELD_WIDTH = 12;

  /**
   * Membuat warna background untuk interface barang-barang di GUI.
   */
  private static final Color BG_COLOR = new Color(252, 140, 3);

  /**
   * memprogram GUI Shopping Cart.
   */
  private final ShoppingCart my_items;

  /**
   * Membuat bidang kosong untuk total harga yang customer beli.
   */
  private final JTextField my_total;

  /**
   * Membuat GUI shooping cart untuk item yang dijual ke custumer.
   * 
   * @param the_items adalah barang - barang yang diinput oleh user.
   */
  public ShoppingFrame(final List<Item> the_items) {
    // membuat bingkai (nama TOKO) dan daftar orderan
    super("WARUNG BAKSO");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    my_items = new ShoppingCart();

    // inputan untuk text di total harga
    my_total = new JTextField("Rp.0.00", TEXT_FIELD_WIDTH);
    add(makeTotalPanel(), "North");
    add(makeItemsPanel(the_items), "Center");
    add(makeCheckBoxPanel(), "South");

    // fit the size untuk tampilan pada GUI
    pack();
    setVisible(true);
  }

  /**
   * Membuat panel untuk total harga
   */
  private JPanel makeTotalPanel() {
    // mengatur bidang agar tidak bisa di edit oleh user
    // dan menentukan warna untuk tulisan

    my_total.setEditable(false);
    my_total.setEnabled(false);
    my_total.setDisabledTextColor(Color.black);

    // Membuat panel untuk tampilan total harga order di GUI

    final JPanel p = new JPanel();
    p.setBackground(Color.red);
    final JLabel l = new JLabel("Total Harga Order");
    l.setForeground(Color.green);
    p.add(l);
    p.add(my_total);
    return p;
  }

  /**
   * Membuat panel untuk menampung daftar barang yang dibeli.
   */
  private JPanel makeItemsPanel(final List<Item> the_items) {
    final JPanel p = new JPanel(new GridLayout(the_items.size(), 1));

    for (Item item : the_items) {
      addItem(item, p);
    }

    return p;
  }

  /**
   * Membuat checkbox untuk opsi kalau memasukan harga diskon
   * 
   * @param the_event adalah panggilan untuk adanya diskon
   */
  private JPanel makeCheckBoxPanel() {
    final JPanel p = new JPanel();
    p.setBackground(Color.yellow);
    final JCheckBox cb = new JCheckBox("Ada Diskon 30% bayar pake PayPay");
    p.add(cb);
    cb.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent the_event) {
        my_items.setDiscount(cb.isSelected());
        updateTotal();
      }
    });
    return p;
  }

  /**
   * program untuk menambahkan barang yang dibeli ke panel yang telah ditentukan
   * 
   * @param the_item  barang yang dipilih
   * @param the_panel Panel yang dipakai (telah ditentukan).
   */
  private void addItem(final Item the_item, final JPanel the_panel) {
    final JPanel sub = new JPanel(new FlowLayout(FlowLayout.LEFT));
    sub.setBackground(BG_COLOR);
    final JTextField quantity = new JTextField(3);
    quantity.setHorizontalAlignment(SwingConstants.CENTER);
    quantity.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent the_event) {
        updateItem(the_item, quantity);
        quantity.transferFocus();
      }
    });
    quantity.addFocusListener(new FocusAdapter() {
      public void focusLost(final FocusEvent the_event) {
        updateItem(the_item, quantity);
      }
    });
    sub.add(quantity);
    final JLabel l = new JLabel(the_item.toString());
    l.setForeground(Color.BLACK);
    sub.add(l);
    the_panel.add(sub);
  }

  /**
   * program untuk menjumlahkan (mengupdate) barang barang yang telah ditentukan.
   * 
   * @param the_item     barang yang diupdate.
   * @param the_quantity Jumlah barang yang telah ditentukan.
   */
  private void updateItem(final Item the_item, final JTextField the_quantity) {
    final String text = the_quantity.getText().trim();
    int number;
    try {
      number = Integer.parseInt(text);
      if (number < 0) {
        // mencegah ada bilangan negatif (barang =! -1)
        throw new NumberFormatException();
      }
    } catch (final NumberFormatException e) {
      number = 0;
      the_quantity.setText("");
    }
    my_items.add(new ItemOrder(the_item, number));
    updateTotal();
  }

  /**
   * Menampilkan total barang yang dpilih
   */
  private void updateTotal() {
    final double total = my_items.getTotal();
    my_total.setText(NumberFormat.getCurrencyInstance().format(total));
  }
}

// END of ShoppingCart GUI
