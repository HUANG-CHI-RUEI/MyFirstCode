package com.wonderful.myfirstcode.chapter12;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.wonderful.myfirstcode.R;
import com.wonderful.myfirstcode.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MaterialDesignActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;//抽屉

    private Partner[] partners = {
            new Partner("路飞",R.mipmap.partner_luffy,R.string.partner_luffy),
            new Partner("索隆",R.mipmap.partner_zoro,R.string.partner_zoro),
            new Partner("山治",R.mipmap.partner_sanji,R.string.partner_sanji),
            new Partner("艾斯",R.mipmap.partner_ace,R.string.partner_ace),
            new Partner("罗",R.mipmap.partner_law,R.string.partner_law),
            new Partner("娜美",R.mipmap.partner_nami,R.string.partner_nami),
            new Partner("罗宾",R.mipmap.partner_robin,R.string.partner_robin),
            new Partner("薇薇",R.mipmap.partner_vivi,R.string.partner_vivi),
            new Partner("蕾贝卡",R.mipmap.partner_rebecca,R.string.partner_rebecca),
            new Partner("汉库克",R.mipmap.partner_hancock,R.string.partner_hancock)};

    private List<Partner> partnerList = new ArrayList<>();

    private PartnerAdapter adapter;//适配器

    private SwipeRefreshLayout swipeRefresh;// 刷新

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);// 将 Toolbar 的实例传入

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true); //让导航按钮显示出来
            actionBar.setHomeAsUpIndicator(R.mipmap.menu);//设置导航按钮图标
        }
        navView.setCheckedItem(R.id.nav_call);//设置默认选中项
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();//关闭抽屉
                ToastUtils.showShort("点击了"+item.getTitle());
                return true;
            }
        });

        // 悬浮按钮
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToastUtils.showShort("点击了悬浮按钮");
                Snackbar.make(v,"删除数据",Snackbar.LENGTH_SHORT)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showShort("数据恢复");
                            }
                        }).show();
            }
        });

        // 初始数据
        initPartner();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_one_piece);
        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PartnerAdapter(partnerList);
        recyclerView.setAdapter(adapter);

        // 下拉刷新
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);//设置刷新进度条颜色
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 处理刷新逻辑
                refreshPartner();
            }
        });
    }

    /**
     * 下拉刷新数据（为简单起见没和网络交互）
     */
    private void refreshPartner() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initPartner();//重新生成数据
                        adapter.notifyDataSetChanged();//通知数据变化
                        swipeRefresh.setRefreshing(false);//隐藏刷新进度条
                    }
                });
            }
        }).start();
    }

    /**
     * 初始化数据，随机挑选50条数据
     */
    private void initPartner() {
        partnerList.clear();
        for (int i = 0;i < 50 ;i++){
            Random random = new Random();
            int index = random.nextInt(partners.length);
            partnerList.add(partners[index]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 加载菜单
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // 设置点击事件
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);//打开抽屉
                break;

            case R.id.backup:
                ToastUtils.showShort("点击了备份");
                break;
            case R.id.delete:
                ToastUtils.showShort("点击了删除");
                break;
            case R.id.settings:
                ToastUtils.showShort("点击了设置");
                break;
        }
        return true;
    }
}
