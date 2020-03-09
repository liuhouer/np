package cn.northpark.test;

import cn.northpark.utils.TimeUtils;

import java.awt.*;

public class StayActiveTask {
    private Dimension dim; //存储屏幕尺寸
    private Robot robot;//自动化对象


    public StayActiveTask() {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println("屏幕大小为：" + dim.getWidth() + " " + dim.getHeight());
        try {
            robot = new Robot();

        } catch (AWTException e) {
            e.printStackTrace();

        }
    }


    public void Move(int width,int heigh){    //鼠标移动函数
        System.out.println("enter Move()...");
        Point mousepoint = MouseInfo.getPointerInfo().getLocation();
        System.out.println("移动前坐标：" + mousepoint.x + " " + mousepoint.y);
        width += mousepoint.x;
        heigh += mousepoint.y;
        try{
            robot.delay(3000);
            robot.mouseMove(width,heigh);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("移动后坐标：" + width + " " + heigh);
        //robot.mousePress(InputEvent.BUTTON1_MASK);//鼠标单击
    }

    public static void moveFunction() throws InterruptedException {
        StayActiveTask mmc = new StayActiveTask();

        System.out.println("mouse control moving X--->");
        mmc.Move(20,20);//坐标为相对坐标
        Thread.sleep(10000 * 2);
        System.out.println("mouse control stop.");
        mmc.Move(-20,-20);//坐标为相对坐标
    }

    public static void main(String[] args){

        Thread thread = new Thread() {
            public void run() {
                try {
                    while (true) {

                        if(TimeUtils.isWorkClockTime()){

                            moveFunction();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
