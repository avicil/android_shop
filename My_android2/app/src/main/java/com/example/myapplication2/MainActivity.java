package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import shop.bean.CartBean;
import shop.database.SQLiteHelper;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SQLiteHelper sqLiteHelper;
    TextView content_name, content_price, content_introduction;
    Button btn;
    ImageView pic3;
    List<CartBean> list;
    ListView mListView;
    String[] titles={
            "桌子","苹果","蛋糕","线衣","猕猴桃","围巾","劳斯莱斯","海华丝","单车","摩托"};
    String[] introductions={
            "红木 d",
            "红富士 d ",
            "定制蛋糕 d ",
            "冬季线衣  d ",
            "来自澳大利亚  d ",
            "冬季围巾  d",
            "4S d",
            "海华丝洗发液 d",
            "登山车 d ",
            "郝雷 d"

    };
    String[] prices={
            "￥199起",
            "￥5/斤",
            "￥50起",
            "￥100起",
            "￥90起",
            "￥99起",
            "￥20000000起",
            "￥33起",
            "￥2000起",
            "￥26999起"
    };
    int[] pics={
            R.drawable.table,
            R.drawable.apple,
            R.drawable.cake,
            R.drawable.wireclothes,
            R.drawable.kiwifruit,
            R.drawable.scarf,
            R.drawable.timg,
            R.drawable.hefe,
            R.drawable.blick,
            R.drawable.mot
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        mListView = (ListView) findViewById(R.id.list);
        btn = (Button) findViewById(R.id.content_btn);
        pic3 = (ImageView) findViewById(R.id.pic3);
        MyBaseAdapter mAdapter = new MyBaseAdapter();
        mListView.setAdapter(mAdapter);
        initData();
    }

    protected void initData() {
        sqLiteHelper = new SQLiteHelper(this);
    }

    @Override
    public void onClick(View v) {
        String title, price, introduction;
        switch (v.getId()) {
            case R.id.pic3:
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivityForResult(intent,1);
                break;
        }
    }

    class MyBaseAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Object getItem(int position) {
            return titles[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder vh;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.list_item, null);
                vh = new ViewHolder();
                vh.title = (TextView) convertView.findViewById(R.id.content_name);
                vh.price = (TextView) convertView.findViewById(R.id.content_price);
                vh.introduction = (TextView) convertView.findViewById(R.id.content_introduction);
                vh.iv = (ImageView) convertView.findViewById(R.id.iv);
                vh.btn = (Button) convertView.findViewById(R.id.content_btn);
                convertView.setTag(vh);
                final ViewHolder finalVh = vh;
                vh.btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String CartName = finalVh.title.getText().toString();
                        String CartPrice = finalVh.price.getText().toString();
                        String CartIntroduction = finalVh.introduction.getText().toString();
                        if (sqLiteHelper.insertData(CartName, CartPrice, CartIntroduction)) {
                            Toast.makeText(MainActivity.this, "已加入购物车", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "加入购物车失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.title.setText(titles[position]);
            vh.price.setText(prices[position]);
            vh.introduction.setText(introductions[position]);
            vh.iv.setBackgroundResource(pics[position]);
            return convertView;
        }
    }

    class ViewHolder {
        TextView title, price, introduction;
        Button btn;
        ImageView iv;
    }

    private class ShopCartActivity {
    }
}
