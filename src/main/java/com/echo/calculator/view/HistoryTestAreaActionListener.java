package com.echo.calculator.view;

/**
 * Created by echo on 16-9-24.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import javax.swing.*;

public class HistoryTestAreaActionListener implements ActionListener {

  private JTextArea jta;
  private JFrame jf;
  private JFileChooser fileChooser = new JFileChooser("..") {
    private static final long serialVersionUID = 3348615193412527885L;

    public void approveSelection() {
      File savedFile = fileChooser.getSelectedFile();

      if (savedFile.exists()) {
        int overwriteSelect = JOptionPane.showConfirmDialog(jf,
            "<html><font size=3>文件" + savedFile.getName() + "已存在，是否覆盖?</font><html>",
            "是否覆盖?",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        if (overwriteSelect != JOptionPane.YES_OPTION) {
          return;
        }
      }
      super.approveSelection();
    }
  };

  public HistoryTestAreaActionListener(JFrame jf, JTextArea jta) {
    this.jf = jf;
    this.jta = jta;
  }

  public void actionPerformed(ActionEvent e) {
    String cmd = e.getActionCommand();
    if ("copy".equals(cmd)) {
      jta.copy();
    } else if ("save".equals(cmd)) {
      fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      fileChooser.setMultiSelectionEnabled(false);

      int isSelect = fileChooser.showSaveDialog(jf);
      if (isSelect == JFileChooser.APPROVE_OPTION) {
        File savedFile = fileChooser.getSelectedFile();

        String filePathStr = savedFile.getAbsolutePath();
        //如果文件不是以".xls"结尾，则补充.xls
        if (!filePathStr.endsWith("txt")) {
          filePathStr = filePathStr + ".txt";
        }
        //写入文件
        File file = new File(filePathStr);

        try {
          FileOutputStream fop = new FileOutputStream(file);
          OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
          // 构建OutputStreamWriter对象,参数可以指定编码,默认为操作系统默认编码,windows上是gbk
          writer.append(jta.getText());
          writer.close();
          //关闭写入流,同时会把缓冲区内容写入文件,所以上面的注释掉
          fop.close();
          // 关闭输出流,释放系统资源
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    } else if ("clear".equals(cmd)) {
      jta.setText("");
    }
  }

}
