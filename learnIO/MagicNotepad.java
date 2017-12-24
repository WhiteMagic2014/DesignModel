package learnIO;

import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class MagicNotepad extends JFrame implements ActionListener {

	// 文本框
	JTextArea jta = null;
	// 菜单条
	JMenuBar jmb = null;
	// 菜单组
	JMenu jm1 = null;
	// 具体功能
	JMenuItem jmi1 = null;
	JMenuItem jmi2 = null;

	public MagicNotepad() {
		jta = new JTextArea();

		jmb = new JMenuBar();
		jm1 = new JMenu("文档");
		jmi1 = new JMenuItem("打开");
		jmi2 = new JMenuItem("保存");
		jmi1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
		jmi2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));// 快捷键
		jmi1.setMnemonic(KeyEvent.VK_O);
		jmi2.setMnemonic(KeyEvent.VK_S);

		jmi1.addActionListener(this);
		jmi1.setActionCommand("open");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("save");

		this.setJMenuBar(jmb);
		jmb.add(jm1);
		jm1.add(jmi1);
		jm1.add(jmi2);

		this.add(new JScrollPane(jta));

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 300);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		MagicNotepad mn = new MagicNotepad();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("open")) {
			System.out.println("open");
			// 文件选择器组件
			JFileChooser jfc1 = new JFileChooser();
			jfc1.setDialogTitle("请选择文件...");
			// 使用默认方式打开
			jfc1.showOpenDialog(null);
			jfc1.setVisible(true);

			// 得到绝对路径
			String filepath = jfc1.getSelectedFile().getAbsolutePath();
			FileReader fr = null;
			BufferedReader br = null;
			try {
				fr = new FileReader(filepath);
				br = new BufferedReader(fr);

				// 从文件中读取后显示在jtextarea中
				String s = null;
				String all = null;
				while ((s = br.readLine()) != null) {
					all += s + "\r\n";
				}

				jta.setText(all);

			} catch (Exception e2) {
				e2.printStackTrace();
			} finally {
				try {
					fr.close();
					br.close();
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}

		} else if (e.getActionCommand().equals("save")) {
			System.out.println("save");

			// 文件选择器组件
			JFileChooser jfc2 = new JFileChooser();
			jfc2.setDialogTitle("保存");
			// 使用默认方式打开
			jfc2.showSaveDialog(null);
			jfc2.setVisible(true);

			// 得到绝对路径
			String filepath = jfc2.getSelectedFile().getAbsolutePath();
			// 写入指定文件
			FileWriter fw = null;
			BufferedWriter bw = null;

			try {

				fw = new FileWriter(filepath);
				bw = new BufferedWriter(fw);

				bw.write(this.jta.getText());

				// char c[] = new char[1024];
				// while (c != null) {
				// bw.write(c);
				// }

			} catch (Exception e2) {
				e2.printStackTrace();
			} finally {
				try {
					bw.close();
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}

		}
	}

}
