package com.example.office.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.office.Constants.UI.Screen;
import com.example.office.Constants.UI.ScreenGroup;
import com.example.office.R;
import com.example.office.logger.Logger;

public class SlidingDrawerAdapter extends ArrayAdapter<Screen> {
    
    /**
     * Layout inflater.
     */
    private LayoutInflater mInflater;
    
    /**
     * Resource id for single ListView item
     */
    private int mItemResource;
    
    /**
     * Default constructor.
     * 
     * @param context Application context.
     * @param itemResource Drawer item resource id.
     */
    public SlidingDrawerAdapter(Context context, int itemResource) {
        super(context, itemResource);        
        try {
            mInflater = LayoutInflater.from(context);
            mItemResource = itemResource;
            
            for (Screen screen: Screen.values()) {
                if (screen.in(ScreenGroup.DRAWER)) {
                    add(screen);
                }
            }
        } catch (Exception e) {
            Logger.logApplicationException(e, getClass().getSimpleName() + ".SlidingDrawerAdapter(): Error.");
        }
    }

    private class ItemHolder {
        ImageView icon;
        TextView title;
        TextView count;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            ItemHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(mItemResource, null);

                holder = new ItemHolder();
                holder.icon = (ImageView) convertView.findViewById(R.id.drawer_item_icon);
                holder.title = (TextView) convertView.findViewById(R.id.drawer_item_title);
                holder.count = (TextView) convertView.findViewById(R.id.drawer_item_count);

                convertView.setTag(holder);
            } else {
                holder = (ItemHolder) convertView.getTag();
            }

            Screen screen = getItem(position);
            if (screen != null) {
                String title = screen.getName(getContext());
                Drawable icon = screen.getIcon(getContext());

                String count = "";
                if (title.equals(getContext().getString(R.string.screens_mailbox))) {
                    // TODO load mailbox items count from local persistence
                }
                // TODO do the same for Later, Archive, Trash and sent screens

                holder.icon.setImageDrawable(icon);
                holder.title.setText(title);
                holder.count.setText(count);
            }

        } catch (Exception e) {}
        
        return convertView;
    }
    
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return super.getCount();
    }
}
