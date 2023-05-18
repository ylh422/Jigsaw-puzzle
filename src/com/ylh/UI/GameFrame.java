package com.ylh.UI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class GameFrame extends JFrame implements KeyListener , ActionListener {
    //存储图片的二维数据
    int[][] arr2 = new int[4][4];
    //判断成功的数据
    int[][] win={
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    //记录空白图像的二维索引坐标
    int x, y;
    //记录步数
    int  step;
    //jmenuitem 重新游戏，重新登录，关闭游戏,公众号
    JMenuItem replayjMenuItem = new JMenuItem("重新游戏");
    JMenuItem reloginjMenuItem = new JMenuItem("重新登录");
    JMenuItem closejMenuItem = new JMenuItem("关闭游戏");
    JMenuItem accountjMenuItem = new JMenuItem("公众号");
    JMenuItem girl = new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sport = new JMenuItem("运动");

    //图片切换
     int k=1;

    //图片路径
    String path="F:\\code2\\Jigsaw puzzle\\image\\animal\\animal2\\";



    public GameFrame() {
        //初始化界面
        initGameFrame();
        initjMenuBar();

        //初始化数据（打乱图片）
        initData();


        //初始化图片
        initImage();

        //界面显示出来
        this.setVisible(true);//默认是不可见的



    }


    //打乱图片
    private void initData() {


        //打乱的一维数据
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            int i1 = random.nextInt(arr.length);
            int temp = 0;
            temp = arr[i];
            arr[i] = arr[i1];
            arr[i1] = temp;
        }
        //打乱的一维数据赋值给二维数据
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] == 0) {
                x = j / 4;
                y = j % 4;
                System.out.println(x);
                System.out.println(y);
            }


            arr2[j / 4][j % 4] = arr[j];
        }
    }


    //初始化图片
    private void initImage() {

        //先加载的图片在前面，后加载的在后面，有覆盖。

        //情况原本已经出现的图片
        this.getContentPane().removeAll();

        if (vectory()){
            ImageIcon imageIcon = new ImageIcon("F:\\code2\\Jigsaw puzzle\\image\\win.png");

            //创建一个Jlabel对象（管理容器）
            JLabel jLabel = new JLabel(imageIcon);
            //指定位置
            jLabel.setBounds(203, 283, 197, 73);

            //把Jlable添加到界面
            this.getContentPane().add(jLabel);
        }


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int temp = arr2[i][j];
                //创建一个图片ImageIcon对象
                ImageIcon imageIcon = new ImageIcon(path + temp + ".jpg");



                //创建一个Jlabel对象（管理容器）
                JLabel jLabel = new JLabel(imageIcon);
                //指定位置
                jLabel.setBounds(j * 105 + 83, i * 105 + 134, 105, 105);
                //给图片添加边框 0:图片突出来；1图片凹下去
                jLabel.setBorder(new BevelBorder(0));

                //把Jlable添加到界面
                this.getContentPane().add(jLabel);
            }

        }
        //背景图片
        ImageIcon imageIcon = new ImageIcon("F:\\code2\\Jigsaw puzzle\\image\\background.png");
        JLabel jLabel = new JLabel(imageIcon);
        jLabel.setBounds(40, 40, 508, 560);
        this.getContentPane().add(jLabel);

        //步数统计
        JLabel jLabel1 = new JLabel("步数" + step);
        jLabel1.setBounds(50,30,100,20);
        this.getContentPane().add(jLabel1);

        //刷新界面
        this.getContentPane().repaint();


    }


    //菜单初始化
    private void initjMenuBar() {
        //菜单
        //菜单对象JMenuBar
        JMenuBar jMenuBar = new JMenuBar();
        //功能，联系我们（JMenu）
        JMenu functionjmenu = new JMenu("功能");
        JMenu aboutjmenu = new JMenu("联系我们");
        //创建更换图片
        JMenu changeImage = new JMenu("更换图片");

         //添加点击事件
        replayjMenuItem.addActionListener(this);
        reloginjMenuItem.addActionListener(this);
        closejMenuItem.addActionListener(this);
        accountjMenuItem.addActionListener(this);
        girl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);






        //jmenuitem放入JMenu,JMenu放入JMenu
        functionjmenu.add(replayjMenuItem);
        functionjmenu.add(reloginjMenuItem);
        functionjmenu.add(closejMenuItem);
        aboutjmenu.add(accountjMenuItem);
        functionjmenu.add(changeImage);
        //把美女，动物，运动添加到更换图片当中
        changeImage.add(girl);
        changeImage.add(animal);
        changeImage.add(sport);

        //JMenu放入jMenuBar
        jMenuBar.add(functionjmenu);
        jMenuBar.add(aboutjmenu);
        //jMenuBar放入GameFrame
        this.setJMenuBar(jMenuBar);


    }


    //GameFrame设置
    private void initGameFrame() {
        this.setSize(603, 680);  //单位是像素
        this.setTitle("拼图单机游戏");
        //界面置顶

        this.setAlwaysOnTop(true);
        //界面居中
        this.setLocationRelativeTo(null);
        //设置退出模式，
        // 关闭任意界面，虚拟机提出运行.相关默认有0，1，2，3
        this.setDefaultCloseOperation(3);

        //清除getContentPane这个容器默认居中的设置
        this.setLayout(null);
        this.addKeyListener(this);
    }

    //判断胜利
    private boolean vectory(){
        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr2[0].length; j++) {
                if (win[i][j]!=arr2[i][j]){
                    return  false;

                }

            }
        }
        return  true;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override//按住不放
    public void keyPressed(KeyEvent e) {
        if(vectory()){
            return;
        }
        //左是37 上38 右39 下40

        //向上移动
        int keyCode = e.getKeyCode();
        if (keyCode == 38) {
            if (x == 3) {
                return; //退出方法
            }
            //x,y代表空白快
            //x+1,y代表白块下面的块
            arr2[x][y] = arr2[x + 1][y];
            arr2[x + 1][y] = 0;
            x++;
            step++;
            //调用方法
            initImage();

        }
        //向下移动
        else if (keyCode == 40) {
            if (x == 0) {
                return;
            }
            arr2[x][y] = arr2[x - 1][y];
            arr2[x - 1][y] = 0;
            x--;
            step++;
            initImage();
        }
        //向左移动
        else if (keyCode == 37) {
            if (y == 3) {
                return;
            }
            arr2[x][y] = arr2[x][y + 1];
            arr2[x][y + 1] = 0;
            y++;
            step++;
            initImage();

        }
        //向右移动
        else if (keyCode == 39) {
            if (y == 0) {
                return;
            }
            arr2[x][y] = arr2[x][y - 1];
            arr2[x][y - 1] = 0;
            y--;
            step++;
            initImage();

        } //查看原图 a
        else if (keyCode == 65) {
            this.getContentPane().removeAll();
            ImageIcon imageIconall = new ImageIcon(path+"all.jpg");
            JLabel jLableall = new JLabel(imageIconall);
            jLableall.setBounds(83, 134, 420, 420);
            this.getContentPane().add(jLableall);

            //添加背景
            ImageIcon imageIcon = new ImageIcon("F:\\code2\\Jigsaw puzzle\\image\\background.png");
            JLabel jLabel = new JLabel(imageIcon);
            jLabel.setBounds(40, 40, 508, 560);
            this.getContentPane().add(jLabel);

            //刷新
            this.getContentPane().repaint();


        }


    }

    @Override//松开按键
    public void keyReleased(KeyEvent e) {
        if (vectory()){
            return;
        }
        int keyCode = e.getKeyCode();
        if (keyCode == 65) {
            initImage();
        }
        //w直接胜利
        else if (keyCode==87) {
            if (keyCode == 87) {
                arr2 = new int[][]{
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 0}
                };
            }
            initImage();
        }

    }

    @Override//动作事件
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source==replayjMenuItem){
            System.out.println("点击了重新游戏");
            //清空步数
            step=0;
            //重新打乱图片
            initData();;
            //重新加载数据
            initImage();
        }else if(source==reloginjMenuItem){
            System.out.println("点击了重新登录");
            //关闭当前界面
            this.setVisible(false);
            //打开登录界面
            new  loginFrame();
        }else if(source==closejMenuItem){
            System.out.println("点击了关闭事件");
            //直接关闭虚拟机
            System.exit(0);

        }else if(source==accountjMenuItem){
            System.out.println("点击了公众号");
            //创建弹框对象
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon("F:\\code2\\Jigsaw puzzle\\image\\196b9736a17e58d5b2e4d652ec83827.png"));
            jLabel.setBounds(0,0,277,277);

            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344,344);
            //弹框置顶
            jDialog.setAlwaysOnTop(true);
            //弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭无法操作下面的界面
            jDialog.setModal(true);
            //弹框可见
            jDialog.setVisible(true);
        }else if(source==girl){
            System.out.println("点击了女孩");

//            修改PATH变量记录的值

            if(k<=13){
                path="F:\\code2\\Jigsaw puzzle\\image\\girl\\girl"+k+"\\";
                k++;
            }else{
                k=1;
                path="F:\\code2\\Jigsaw puzzle\\image\\girl\\girl"+k+"\\";
            }

            //写一些重开一把的逻辑
            //清空步数
            step=0;
            //重新打乱图片
            initData();;
            //重新加载数据
            initImage();


        }else if(source==animal){
            System.out.println("点击了动物");

//            修改PATH变量记录的值

            if(k<=8){
                path="F:\\code2\\Jigsaw puzzle\\image\\animal\\animal"+k+"\\";
                k++;
            }else{
                k=1;
                path="F:\\code2\\Jigsaw puzzle\\image\\animal\\animal"+k+"\\";
            }

            //写一些重开一把的逻辑
            //清空步数
            step=0;
            //重新打乱图片
            initData();;
            //重新加载数据
            initImage();

        }else if(source==sport){
            System.out.println("点击了运动");
            if(k<=10){
                path="F:\\code2\\Jigsaw puzzle\\image\\sport\\sport"+k+"\\";
                k++;
            }else{
                k=1;
                path="F:\\code2\\Jigsaw puzzle\\image\\sport\\sport"+k+"\\";
            }

            //写一些重开一把的逻辑
            //清空步数
            step=0;
            //重新打乱图片
            initData();;
            //重新加载数据
            initImage();

        }


    }
}
