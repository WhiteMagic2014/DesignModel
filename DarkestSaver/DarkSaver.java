package DarkestSaver;

import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.PrintStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class DarkSaver extends JFrame implements ActionListener{
	
	
	JButton jButton = null;
	JPanel jPanel = null;
	JLabel jLabel1 = null;
	JLabel jLabel2 = null;
	JLabel jLabel3 = null;


		
	public DarkSaver() {
		
		
		
		jPanel = new JPanel();
		
		
		jButton = new JButton("保存");
		jButton.addActionListener(this);
		jButton.setActionCommand("save");
		
		jLabel1 = new JLabel();
		jLabel1.setText("\n将桌面上dgSaver中的 262060 文件夹");
		
		jLabel2 = new JLabel();
		jLabel2.setText("\n替换 Steam/userdata/你的steamID/262060 文件夹");
		
		jLabel3 = new JLabel();
		jLabel3.setText("\n即可读档");
		
		jPanel.add(jButton);
		jPanel.add(jLabel1);
		jPanel.add(jLabel2);
		jPanel.add(jLabel3);

		

		
		
		this.add(jPanel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 200);
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		DarkSaver ds = new DarkSaver();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("save")) {

			copy("D:/Program Files (x86)/Steam/userdata/145012434/262060","C:/Users/Administrator/Desktop/dgSaver/262060");
		}
	}
	
	
	//文件夹拷贝
	private static void copy(String src, String des) {  
        File file1=new File(src);  
        File[] fs=file1.listFiles();  
        File file2=new File(des);  
        if(!file2.exists()){  
            file2.mkdirs();  
        }  
        for (File f : fs) {  
            if(f.isFile()){  
                fileCopy(f.getPath(),des+"\\"+f.getName()); //调用文件拷贝的方法  
            }else if(f.isDirectory()){  
                copy(f.getPath(),des+"\\"+f.getName());  
            }  
        }  
          
    }  
  
    /** 
     * 文件拷贝的方法 
     */  
    private static void fileCopy(String src, String des) {  
      
        BufferedReader br=null;  
        PrintStream ps=null;  
          
        try {  
            br=new BufferedReader(new InputStreamReader(new FileInputStream(src)));  
            ps=new PrintStream(new FileOutputStream(des));  
            String s=null;  
            while((s=br.readLine())!=null){  
                ps.println(s);  
                ps.flush();  
            }  
              
        } catch (FileNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }finally{  
              
                try {  
                    if(br!=null)  br.close();  
                    if(ps!=null)  ps.close();  
                } catch (IOException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
                  
        }  
          
          
    }  
	
	
	
	
}
