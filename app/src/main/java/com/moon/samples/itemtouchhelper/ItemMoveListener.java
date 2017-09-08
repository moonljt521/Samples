package com.moon.samples.itemtouchhelper;

/**
 * Created by moon on 2017/9/8.
 */

public interface ItemMoveListener {
    boolean onItemMove(int fromPosition, int toPosition);

    boolean onItemRemove(int position);
}
