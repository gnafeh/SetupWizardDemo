package com.example.setupwizarddemo.wifi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.setupwizarddemo.R;
import com.example.setupwizarddemo.wifi.bean.WifiListBean;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder>{
    private List<WifiListBean> wifiListBeanList;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public MainAdapter.OnItemClickListener mOnItemClickListerer;

    public void setmOnItemClickListerer(MainAdapter.OnItemClickListener listerer) {
        this.mOnItemClickListerer = listerer;
    }

    public MainAdapter(List<WifiListBean> wifiListBeanList) {
        this.wifiListBeanList = wifiListBeanList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_main, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_name.setText("wifi名：" + wifiListBeanList.get(position).getName());
        holder.tv_encrypt.setText("加密方式：" + wifiListBeanList.get(position).getEncrypt());
        holder.tv_level.setText("信号强度：" + wifiListBeanList.get(position).getLevel());
        holder.btn_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListerer.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return wifiListBeanList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_name, tv_encrypt, tv_level;
        Button btn_link;

        public MyViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_encrypt = view.findViewById(R.id.tv_encrypt);
            tv_level = view.findViewById(R.id.tv_level);
            btn_link = view.findViewById(R.id.btn_link);
        }
    }

}
