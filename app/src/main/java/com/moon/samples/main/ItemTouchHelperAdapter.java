package com.moon.samples.main;

/**
 * author: moon
 * created on: 17/9/8 下午6:30
 * description:
 */
public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPos,int toPos);

    void onItemDismiss(int pos);

}
