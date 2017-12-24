package learnIO;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class MagicNotepadS extends JFrame implements ActionListener {

	// 文本框
	JTextArea jta = null;
	// 菜单条
	JMenuBar jmb = null;
	// 菜单组
	JMenu jm1 = null;
	// 具体功能
	JMenuItem jmi1 = null;
	JMenuItem jmi2 = null;
	JMenuItem jmi3 = null;
	JMenuItem jmi4 = null;
	Key key;

	public MagicNotepadS() {

		// getKey("magic");
		key = getMagicKey("magickey");

		jta = new JTextArea();

		jmb = new JMenuBar();
		jm1 = new JMenu("文档");
		jmi1 = new JMenuItem("打开");
		jmi2 = new JMenuItem("保存");
		jmi3 = new JMenuItem("加密");
		jmi4 = new JMenuItem("解密");

		jmi2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));// 快捷键
		jmi2.setMnemonic(KeyEvent.VK_S);

		jmi1.addActionListener(this);
		jmi1.setActionCommand("open");
		jmi1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
		jmi1.setMnemonic(KeyEvent.VK_O);

		jmi2.addActionListener(this);
		jmi2.setActionCommand("save");
		jmi2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));// 快捷键
		jmi2.setMnemonic(KeyEvent.VK_S);

		jmi3.addActionListener(this);
		jmi3.setActionCommand("encode");
		jmi3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));// 快捷键
		jmi3.setMnemonic(KeyEvent.VK_E);

		jmi4.addActionListener(this);
		jmi4.setActionCommand("decode");
		jmi4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));// 快捷键
		jmi4.setMnemonic(KeyEvent.VK_D);

		this.setJMenuBar(jmb);
		jmb.add(jm1);

		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jm1.add(jmi4);

		this.add(new JScrollPane(jta));
		this.setTitle("MagicDES加密记事本");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 300);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		MagicNotepadS mn = new MagicNotepadS();
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
			// 要用加密算法得用stream
			InputStream is = null;
			CipherInputStream cis = null;

			try {
				is = new FileInputStream(filepath);
				Cipher cipher = Cipher.getInstance("DES");
				cipher.init(Cipher.DECRYPT_MODE, this.key);
				cis = new CipherInputStream(is, cipher);// 主要是用这个流去装饰
														// inputstream
														// 和outputstream，然后直接操作这个流

				// 从文件中读取后显示在jtextarea中
				byte[] buffer = new byte[1024];
				int r;
				String all = "";
				while ((r = cis.read(buffer)) != -1) {
					String info = new String(buffer, 0, r);
					all += info;
				}
				System.out.println(all);
				jta.setText(all);

			} catch (Exception e2) {
				e2.printStackTrace();
			} finally {
				try {
					cis.close();
					is.close();
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
			OutputStream os = null;
			CipherOutputStream cos = null;

			try {

				os = new FileOutputStream(filepath);
				Cipher cipher = Cipher.getInstance("DES");
				cipher.init(Cipher.ENCRYPT_MODE, this.key);
				cos = new CipherOutputStream(os, cipher);
				cos.write(this.jta.getText().getBytes());

			} catch (Exception e2) {
				e2.printStackTrace();
			} finally {
				try {
					cos.close();// 对于流的操作 后开的先关 先开的后关
					os.close();

				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}

		} else if (e.getActionCommand().equals("encode")) {
			// 文件选择器组件
			JFileChooser jfc1 = new JFileChooser();
			jfc1.setDialogTitle("请选择文件...");
			// 使用默认方式打开
			jfc1.showOpenDialog(null);
			jfc1.setVisible(true);
			// 得到绝对路径
			String filepath = jfc1.getSelectedFile().getAbsolutePath();
			try {
				encrypt(filepath, filepath + "加密版");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		} else if (e.getActionCommand().equals("decode")) {
			// 文件选择器组件
			JFileChooser jfc1 = new JFileChooser();
			jfc1.setDialogTitle("请选择文件...");
			// 使用默认方式打开
			jfc1.showOpenDialog(null);
			jfc1.setVisible(true);

			// 得到绝对路径
			String filepath = jfc1.getSelectedFile().getAbsolutePath();

			try {
				decrypt(filepath, filepath + "解密版");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}

	}

	public SecretKey getMagicKey(String key) {
		byte[] keyData = key.getBytes();
		SecretKey myDesKey = new SecretKeySpec(keyData, "DES");
		return myDesKey;
	}

	public void getKey(String strKey) {
		try {
			KeyGenerator _generator = KeyGenerator.getInstance("DES");
			_generator.init(new SecureRandom(strKey.getBytes()));
			this.key = _generator.generateKey();
			_generator = null;
		} catch (Exception e) {
			throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
		}
	}

	/**
	 * 文件file进行加密并保存目标文件destFile中
	 * 
	 * @param file
	 *            要加密的文件 如c:/test/srcFile.txt
	 * @param destFile
	 *            加密后存放的文件名 如c:/加密后文件.txt
	 */
	public void encrypt(String file, String destFile) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");
		// cipher.init(Cipher.ENCRYPT_MODE, getKey());
		cipher.init(Cipher.ENCRYPT_MODE, this.key);
		InputStream is = new FileInputStream(file);
		OutputStream out = new FileOutputStream(destFile);
		CipherInputStream cis = new CipherInputStream(is, cipher);
		byte[] buffer = new byte[1024];
		int r;
		while ((r = cis.read(buffer)) > 0) {
			out.write(buffer, 0, r);
		}
		cis.close();
		is.close();
		out.close();
	}

	/**
	 * 文件采用DES算法解密文件
	 * 
	 * @param file
	 *            已加密的文件 如c:/加密后文件.txt * @param destFile 解密后存放的文件名 如c:/
	 *            test/解密后文件.txt
	 */
	public void decrypt(String file, String dest) throws Exception {
		Cipher cipher = Cipher.getInstance("DES");
		cipher.init(Cipher.DECRYPT_MODE, this.key);
		InputStream is = new FileInputStream(file);
		OutputStream out = new FileOutputStream(dest);
		CipherOutputStream cos = new CipherOutputStream(out, cipher);
		byte[] buffer = new byte[1024];
		int r;
		while ((r = is.read(buffer)) >= 0) {
			System.out.println();
			cos.write(buffer, 0, r);
		}
		cos.close();
		out.close();
		is.close();
	}

}
