package shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication2.R;

import java.util.List;

import shop.bean.CartBean;


public class CartAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<CartBean> list;
    public CartAdapter(Context context,List<CartBean>list){
        this.layoutInflater=LayoutInflater.from(context);
        this.list=list;
    }

    @Override
    public int getCount() {
        return list==null?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(converView==null){
            converView=layoutInflater.inflate(R.layout.shopping_car,null);
            viewHolder=new ViewHolder(converView);
            converView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)converView.getTag();
        }
        CartBean cartBean=(CartBean)getItem(position);
        viewHolder.car_name.setText(cartBean.getCar_name());
        viewHolder.car_price.setText(cartBean.getCar_price());
        viewHolder.car_introduction.setText(cartBean.getCar_introduction());
        return converView;
    }
    class ViewHolder{
        TextView car_name,car_price,car_introduction;
        public ViewHolder(View view){
            car_name=(TextView) view.findViewById(R.id.cart_name);
            car_price=(TextView)view.findViewById(R.id.cart_price);
            car_introduction=(TextView)view.findViewById(R.id.cart_introduction);
        }
    }
}
