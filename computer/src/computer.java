package computer.src;


import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class computer {
	public static void main(String[] args) {
		JS win=new JS();
	}
}
class JS extends JFrame implements ActionListener,KeyListener{
	StringBuilder sBuilder = new StringBuilder();  //利用StringBuilder类来显示
	Stack<String> sstack=new Stack<>();
	Stack<String> astack=new Stack<>();
	JTextArea text=new JTextArea();
	public JS()
	{	

		setBounds(100,100,400,400);
		setTitle("路先生的private computer");
		JMenuBar menubar=new JMenuBar();//创建菜单条
		JMenu menu1=new JMenu("没什么用的查看(V)");//创建和设置菜单名
		JMenu menu2=new JMenu("没什么用的编辑(E)");//创建和设置菜单名
		JMenu menu3=new JMenu("没什么用的帮助(H)");//创建和设置菜单名
		menubar.add(menu1);//将菜单加入到菜单条中
		menubar.add(menu2);
		menubar.add(menu3);
		this.setJMenuBar(menubar);//将设置好的菜单条放在窗口中
		this.setLayout(new BorderLayout());//添加文本框
		
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();

		text.setPreferredSize(new Dimension (370,60));//设置组件大小
		p2.setLayout(new FlowLayout());
		text.addKeyListener(this);

		p1.add(text);
		this.add(p1,BorderLayout.NORTH);
		
		p2.setLayout(new GridLayout(7,4));	//添加按钮
		JButton button[]=new JButton[28];
		button[0]=new JButton("CLEAR");
		button[1]=new JButton("SQRT");			//开平方
		button[2]=new JButton("BIT");				
		button[3]=new JButton("DELE");				
		button[4]=new JButton("(");   			//上一次的算式
		button[5]=new JButton(")");  				
		button[6]=new JButton("*");  				
		button[7]=new JButton("/");  				//清除之前输入的一个数据
	    button[8]=new JButton("7");
		button[9]=new JButton("8");
		button[10]=new JButton("9");
		button[11]=new JButton("-");
		button[12]=new JButton("4");
		button[13]=new JButton("5");
		button[14]=new JButton("6");
		button[15]=new JButton("+");
		button[16]=new JButton("1");
		button[17]=new JButton("2");
		button[18]=new JButton("3"); 				
		button[19]=new JButton("^");				//次方
		button[20]=new JButton("%");				//求余
		button[21]=new JButton("0");
		button[22]=new JButton(".");
		button[23]=new JButton(",");
		button[24]=new JButton("  ");
		button[25]=new JButton("MEMO");
		button[26]=new JButton("=");
		button[27]=new JButton("  ");

		for(int i=0;i<button.length;i++ )	//将组件加入容器
			p2.add(button[i]);
		button[button.length-2].setBackground(Color.ORANGE);//设置"="按钮的背景颜色为橙色
		p2.add(button[button.length-1]);
		add(p2,BorderLayout.CENTER);
		for(int i=0;i<button.length;i++)	//为每一个事件(按钮)添加监视器
			button[i].addActionListener(this);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //点击X号可以关闭程序
	}

	public void actionPerformed(ActionEvent e) //事件处理
	{
		// TODO Auto-generated method stub
		String lab=e.getActionCommand();			//得到当前的按钮字符，后面进行匹配
		
        if(lab.equals("=")){
			String s=sBuilder.toString();
			System.out.print(s);
			sstack.add(s);
			sBuilder.delete(0,sBuilder.length());
			text.setText(cal.calculate(s));	
			System.out.println('='+cal.calculate(s));
		}else if(lab.equals("DELE")){
			if(sBuilder.length()>0){
				sBuilder.deleteCharAt(sBuilder.length()-1);
			    text.setText(sBuilder.toString());	
			}
		}else if(lab.equals(("MEMO"))){
			if(!sstack.isEmpty()){
				sBuilder.append(sstack.peek());
				text.setText(sBuilder.toString());
			}else{
				sBuilder.append("");
				text.setText(sBuilder.toString());
			}
		}else if(lab.equals((" "))){

		}else if(lab.equals("CLEAR")){
			sBuilder.delete(0,sBuilder.length());
			text.setText(sBuilder.toString());	
		}else if(lab.equals("SQRT")||lab.equals("BIT")){
			sBuilder.append(lab.toLowerCase());
            text.setText(sBuilder.toString());
		}else{
			sBuilder.append(lab);
            text.setText(sBuilder.toString());
        }
	}


	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		char key=e.getKeyChar();
		sBuilder.append(key);
		text.setText(sBuilder.toString());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}





