package com.liubo.jianyue.controller;

import com.bastlibrary.utils.DebugLogs;
import com.liubo.jianyue.bean.MainBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liubo on 2017/7/1 0001.
 */

public class JsoupTool {
    /**
     * 简书列表页
     * @param html
     * @return
     */
    public  List<MainBean> getJianshuList(String  html){
        //将html转为Document对象
        Document document = Jsoup.parse(html);
        //获得li的元素集合
        Elements elements = document.select("div#list-container ul li");
        List<MainBean>  mDatas= new ArrayList<>();
        MainBean blogModel;
            for (int i = 0; i<elements.size(); i++){

            //获得作者
            String author = elements.get(i).select("div.name a").first().text();
            //来源
            String from = elements.get(i).select("div.meta a").first().text();
            //获得标题
            String title = elements.get(i).select("a.title").text();
            //获得标题
            String subtitile = elements.get(i).select("p.abstract").text();
            //获得图片url，因为文章有可能没有图片，所以这里需要特殊处理一下
            String image = elements.get(i).select("a.wrap-img").first() != null ?
                    elements.get(i).select("a.wrap-img").first().children().first().attr("src") : "";

            //获得文章详情url
            String targetUrl = elements.get(i).select("a.title").first().attr("href");
//            DebugLogs.e("----title>"+title);
//            DebugLogs.e("----subtitile>"+subtitile);
//            DebugLogs.e("----from>"+from);
            blogModel = new MainBean();
            blogModel.setAuthor(author);
            blogModel.setName(title);
            blogModel.setImage(image);
            blogModel.setTargetUrl(targetUrl);
            blogModel.setFrom(from);
            blogModel.setSubtitile(subtitile);
            if(i==3){
                blogModel.setAd("1");
            }else{
                blogModel.setAd("0");
            }
            mDatas.add(blogModel);
        }
        return mDatas;
    }
    public  void getJianshuListDetails(String  html,call call){
        //将html转为Document对象
        Document document = Jsoup.parse(html);
        //获得li的元素集合
        Elements titleelements = document.select("div.article");
        Elements elements = document.select("div.show-content");
        Elements imgelements = document.select("div.image-package");
        String title= titleelements.get(0).select("h1.title").text();
        String body= elements.html();
        String img = imgelements.get(0).select("img").attr("src");
        call.callStr(title,body,img);
    }
    public  interface call{
        public  void callStr(String title, String body, String img);
    }
    public  interface Onecall{
        public  void callStr(String title, String body);
    }
    public  void getOneText(String  html,Onecall call){
        //将html转为Document对象
        Document document = Jsoup.parse(html);
        //获得li的元素集合
        Elements titleelements = document.select("h1");
        Elements elements = document.select("div.article_text");
        //Elements img =document.select("style").get(0);
        String body= elements.html();
        String title= titleelements.text();
        //String img = imgelements.get(0).select("img").attr("src");
        DebugLogs.e("----title>"+title);
        DebugLogs.e("----body>"+body);
        call.callStr(title,body);
    }
}
