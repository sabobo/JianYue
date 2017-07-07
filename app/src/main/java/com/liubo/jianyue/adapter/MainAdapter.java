package com.liubo.jianyue.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bastlibrary.adapter.BaseViewHolder;
import com.bastlibrary.adapter.SimpleAdapter;
import com.bumptech.glide.Glide;
import com.liubo.jianyue.R;
import com.liubo.jianyue.bean.MainBean;

import java.text.ParseException;
import java.util.List;

/**
 * Created by liubo on 2017/6/27 0027.
 */

public class MainAdapter extends SimpleAdapter<MainBean> {
    public MainAdapter(Context context, List<MainBean> datas) {
        super(context, R.layout.activity_main_item1, datas);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, final MainBean item) throws ParseException {
        //NativeResponse nrAd = (NativeResponse) getItem(0);
//        if("1".equals(item.getAd())){
//            viewHoder.getTextView(R.id.title).setText(nrAd.getTitle());
//            viewHoder.getTextView(R.id.time).setText(nrAd.getBrandName());
//            viewHoder.getTextView(R.id.frome).setText("来自--"+item.getFrom());
//            ImageView view = viewHoder.getImageView(R.id.img);
//            Glide.with(context).load(nrAd.getImageUrl()).into(view);
//            LinearLayout layout =viewHoder.getLinearLayout(R.id.ll_ad);
//            layout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    NativeResponse nrAd = (NativeResponse) getItem(0);
//                    nrAd.handleClick(view);
//                }
//            });
//        }else {
//            viewHoder.getTextView(R.id.title).setText(item.getName());
//            viewHoder.getTextView(R.id.time).setText(item.getAuthor());
//            viewHoder.getTextView(R.id.frome).setText("来自--"+item.getFrom());
//            ImageView view = viewHoder.getImageView(R.id.img);
//            Glide.with(context).load("http:"+item.getImage()).into(view);
//        }
        viewHoder.getTextView(R.id.title).setText(item.getName());
        viewHoder.getTextView(R.id.time).setText(item.getAuthor());
        viewHoder.getTextView(R.id.frome).setText("来自--"+item.getFrom());
        ImageView view = viewHoder.getImageView(R.id.img);
        Glide.with(context).load("http:"+item.getImage()).into(view);
//        SimpleDraweeView simpleDraweeView = viewHoder.getSimpleDraweeView(R.id.sdv_1);
//        Phoenix.with(simpleDraweeView)
//                .setControllerListener(new SingleImageControllerListener(simpleDraweeView))
//                .load("http:"+item.getImage());
    }

}