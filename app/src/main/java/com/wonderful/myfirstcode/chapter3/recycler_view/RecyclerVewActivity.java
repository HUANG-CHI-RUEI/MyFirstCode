package com.wonderful.myfirstcode.chapter3.recycler_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.wonderful.myfirstcode.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyclerVewActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        // 初始化水果数据
        initFruits();
        // 获取RecyclerView的实例
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        // LayoutManager用于指定RecyclerView的布局方式，LinearLayoutManager表示线性布局
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 设置布局横向排列（默认是纵向排列的）
        //layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        // 创建StaggeredGridLayoutManager的实例（构造函数中的两个参数：第一个指定布局的列数，第二个指定布局的排列方向）
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        // 创建FruitAdapter的实例
        FruitAdapter adapter = new FruitAdapter(fruitList);
        // 设置适配器
        recyclerView.setAdapter(adapter);
    }

    private void initFruits() {

        for (int i = 0;i < 2;i++){
            Fruit apple = new Fruit(getRandomLengthName("Apple"),R.drawable.pic_apple);
            fruitList.add(apple);
            Fruit banana = new Fruit(getRandomLengthName("Banana"),R.drawable.pic_banana);
            fruitList.add(banana);
            Fruit orange = new Fruit(getRandomLengthName("orange"),R.drawable.pic_orange);
            fruitList.add(orange);
            Fruit watermelon = new Fruit(getRandomLengthName("watermelon"),R.drawable.pic_watermelon);
            fruitList.add(watermelon);
            Fruit grape = new Fruit(getRandomLengthName("grape"),R.drawable.pic_grape);
            fruitList.add(grape);
            Fruit pineapple = new Fruit(getRandomLengthName("pineapple"),R.drawable.pic_pineapple);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit(getRandomLengthName("strawberry"),R.drawable.pic_strawberry);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit(getRandomLengthName("cherry"),R.drawable.pic_cherry);
            fruitList.add(cherry);
            Fruit mango = new Fruit(getRandomLengthName("mango"),R.drawable.pic_mango);
            fruitList.add(mango);
        }

        /*for (int i = 0;i < 2;i++){
            Fruit apple = new Fruit("Apple",R.drawable.pic_apple);
            fruitList.add(apple);
            Fruit banana = new Fruit("Banana",R.drawable.pic_banana);
            fruitList.add(banana);
            Fruit orange = new Fruit("orange",R.drawable.pic_orange);
            fruitList.add(orange);
            Fruit watermelon = new Fruit("watermelon",R.drawable.pic_watermelon);
            fruitList.add(watermelon);
            Fruit grape = new Fruit("grape",R.drawable.pic_grape);
            fruitList.add(grape);
            Fruit pineapple = new Fruit("pineapple",R.drawable.pic_pineapple);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit("strawberry",R.drawable.pic_strawberry);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit("cherry",R.drawable.pic_cherry);
            fruitList.add(cherry);
            Fruit mango = new Fruit("mango",R.drawable.pic_mango);
            fruitList.add(mango);
        }*/
    }

    /**
     * 随机生成水果名字的长度
     * @param name
     * @return
     */
    private String getRandomLengthName(String name){
        Random random = new Random();
        int length = random.nextInt(20)+1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0 ;i < length;i++){
            builder.append(name);
        }
        return builder.toString();
    }
}
