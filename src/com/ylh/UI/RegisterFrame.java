package com.ylh.UI;

import javax.swing.*;

public class RegisterFrame  extends JFrame {
     public RegisterFrame(){
         this.setSize(488,500);  //单位是像素
         this.setTitle("拼图单机游戏注册界面");

         //界面置顶
         this.setAlwaysOnTop(true);
         //界面居中
         this.setLocationRelativeTo(null);
         //设置突出模式，关闭任意界面，虚拟机提出运行.相关默认有0，1，2，3
         this.setDefaultCloseOperation(3);

         this.setVisible(true);//默认是不可见的
     }
}
