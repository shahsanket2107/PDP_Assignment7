package view;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * Class bar chart.
 */
public class BarChart extends JPanel {

  private int[] data;
  private String[] names;

  /**
   * Constructor for bar chart.
   *
   * @param names names of columns.
   * @param data  data for each column.
   */
  public BarChart(String[] names, String[] data) {
    this.names = names;

    int[] d = new int[data.length];
    for (int i = 0; i < data.length; i++) {
      String[] starsLength = data[i].split("");
      d[i] = starsLength.length;
    }
    this.data = d;
  }


  /**
   * Draw bar chart.
   *
   * @param g the <code>Graphics</code> object to protect
   */
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    double minValue = 0;
    double maxValue = 0;
    for (int i = 0; i < data.length; i++) {
      if (minValue > data[i]) {
        minValue = data[i];
      }
      if (maxValue < data[i]) {
        maxValue = data[i];
      }
    }

    Dimension d = getSize();
    int barWidth = d.width / data.length;
    int nameWidth = 50;
    double scale = d.height / (maxValue - minValue);

    for (int i = 0; i < data.length; i++) {
      int valueX = i * barWidth + 1;
      int valueY = 25;
      int height = (int) (data[i] * scale);
      if (data[i] >= 0) {
        valueY += (int) ((maxValue - data[i]) * scale);
      } else {
        valueY += (int) (maxValue * scale);
      }

      g.setColor(Color.red);
      g.fillRect(valueX, valueY, barWidth - 2, height);
      g.setColor(Color.black);
      g.drawRect(valueX, valueY, barWidth - 2, height);
      int x = i * barWidth + (barWidth - nameWidth) / 2;
      g.drawString(names[i], x, d.height);
    }
  }
}
