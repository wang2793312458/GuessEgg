package com.feicui.guessegg;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int[] imageIds = new int[] { R.mipmap.shoe_ok, R.mipmap.shoe_sorry,
            R.mipmap.shoe_sorry };
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private TextView result;//显示结果
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image1 = (ImageView) findViewById(R.id.imageView1);
        image2 = (ImageView) findViewById(R.id.imageView2);
        image3 = (ImageView) findViewById(R.id.imageView3);
        result = (TextView) findViewById(R.id.textView1);//获取textview组件
        reset();//将携子顺序打乱
        /**
         * 给第一只鞋子添加点击事件监听器
         */
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRight(v,0);//判断结果
            }
        });
        /**
         * 给第2只鞋子添加点击事件监听器
         */
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRight(v,1);//判断结果
            }
        });

        /**
         * 给第3只鞋子添加点击事件监听器
         */
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRight(v,2);//判断结果
            }
        });
        mButton = (Button) findViewById(R.id.button1);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();                                    //将标题恢复为默认值
                result.setText(R.string.title);
                image1.setAlpha(255);
                image2.setAlpha(255);
                image3.setAlpha(255);
                image1.setImageDrawable(getResources().getDrawable(R.mipmap.shoe_default));
                image2.setImageDrawable(getResources().getDrawable(R.mipmap.shoe_default));
                image3.setImageDrawable(getResources().getDrawable(R.mipmap.shoe_default));
            }
        });
    }

    /**
     * 判断猜出的结果
     * @param v
     * @param index
     */
    protected void isRight(View v, int index) {
        //使用随机数组中图片资源ID设置每个Imageview
        image1.setImageDrawable(getResources().getDrawable(imageIds[0]));
        image2.setImageDrawable(getResources().getDrawable(imageIds[1]));
        image3.setImageDrawable(getResources().getDrawable(imageIds[2]));
        //为每个Imageview设置半透明效果
        image1.setAlpha(100);
        image1.setAlpha(100);
        image1.setAlpha(100);
        ImageView v1= (ImageView) v;  //获取被点记的图像试图
        v1.setAlpha(255);              //设置图像视图的透明度
        if (imageIds[index]==R.mipmap.shoe_ok){      //判断是否猜对
            result.setText("猜对了");
        }else {
            result.setText("猜错了，敢不敢再来一次");
        }
    }
//随机制定鸡蛋所在的鞋子
    private void reset() {
        for (int i = 0; i < 3; i++) {
            int temp = imageIds[i];//讲数组元素i保存到临时变量中
            int index = (int) (Math.random() * 2);//生成一个随机数
            imageIds[i] = imageIds[index];//将随机数指定的元素内容赋值给数组元素i
            imageIds[index] = temp;//降临时变量的值复制给随机数组指定的数组元素
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
}
