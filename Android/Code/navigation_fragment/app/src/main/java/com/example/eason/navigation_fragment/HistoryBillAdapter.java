//package com.example.eason.navigation_fragment;
//
//import android.app.Activity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseExpandableListAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import java.util.List;
//
///**
// * Created by eason on 2016-04-22.
// */
//public class HistoryBillAdapter extends BaseExpandableListAdapter {
//
//    private LayoutInflater mLayoutInflater;
//    private Activity activity;
//    private List<String> groupArray;
//    private List<List<String>> childArray;
//
//    public HistoryBillAdapter(Activity activity,List<String> groupArray, List<List<String>> childArray){
//
//        this.activity = activity;
//        this.groupArray = groupArray;
//        this.childArray = childArray;
//        mLayoutInflater = LayoutInflater.from(activity);
//    }
//    public HistoryBillAdapter(Activity activity){
//
//        this.activity = activity;
//        mLayoutInflater = LayoutInflater.from(activity);
//    }
//    @Override
//    public Object getChild(int groupPosition, int childPosition) {
//        return childArray.get(groupPosition).get(childPosition);
//    }
//
//
//
//    @Override
//    public long getChildId(int groupPosition, int childPosition) {
//        return childPosition;
//    }
//
//    @Override
//    public int getChildType(int groupPosition, int childPosition) {
//        if (childPosition == (-1 + getChildrenCount(groupPosition))) {
//            return 1;
//        }else {
//            return 0;
//        }
//    }
//
//    @Override
//    public int getChildTypeCount() {
//        return 2;
//    }
//
//    @Override
//    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
//                             ViewGroup parent) {
//        ChildViewHolder childViewHolder;
//        View view = convertView;
//        int i1 = getChildType(groupPosition, childPosition);
//        if (view == null) {
//            childViewHolder = new ChildViewHolder();
//            view = mLayoutInflater.inflate(R.layout.fragmentdemo3_main, null);
//            view.setTag(childViewHolder);
//        }else {
//            childViewHolder = (ChildViewHolder)view.getTag();
//        }
//        if ( i1 != 0) {
//            return mLayoutInflater.inflate(R.layout.child_mian, null);
//        }
//        return view;
//    }
//
//    class ChildViewHolder{
//        ImageView bankIcon;
//        TextView consumerInfo;
//        TextView consumerMoney;
//    }
//
//    @Override
//    public int getChildrenCount(int groupPosition) {
//        return childArray.get(groupPosition).size();
//    }
//
//    @Override
//    public Object getGroup(int groupPosition) {
//        return groupArray.get(groupPosition);
//    }
//
//    @Override
//    public int getGroupCount() {
//        if (groupArray != null) {
//            return groupArray.size();
//        }else {
//            return 0;
//        }
//    }
//
//    @Override
//    public long getGroupId(int groupPosition) {
//        return groupPosition;
//    }
//
//    @Override
//    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//        GroupViewHolder groupViewHolder;
//        if (convertView == null) {
//            groupViewHolder = new GroupViewHolder();
//            convertView = mLayoutInflater.inflate(R.layout.his_group_item, null);
//            groupViewHolder.view_bottom = (ImageView)convertView.findViewById(R.id.ImageView_Bottom);
//            groupViewHolder.view_bottom_line = (ImageView)convertView.findViewById(R.id.ImageView_Divider_Line);
//            convertView.setTag(groupViewHolder);
//        }else{
//            groupViewHolder = (GroupViewHolder)convertView.getTag();
//        }
//        if (isExpanded) {
//            groupViewHolder.view_bottom_line.setVisibility(View.VISIBLE);
//            groupViewHolder.view_bottom.setBackgroundResource(R.drawable.buttom);
//        }else {
//            groupViewHolder.view_bottom_line.setVisibility(View.INVISIBLE);
//            groupViewHolder.view_bottom.setBackgroundResource(R.drawable.buttom);
//        }
//
//        return convertView;
//    }
//
//    class GroupViewHolder{
//        ImageView view_bottom;
//        ImageView view_bottom_line;
//    }
//
//    @Override
//    public boolean hasStableIds() {
//        return false;
//    }
//
//    @Override
//    public boolean isChildSelectable(int groupPosition, int childPosition) {
//        return true;
//    }
//
//
//}
