package com.maxiran.maxiran.appexplorer;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {
    private GridView gv;
    private List<PackageInfo> packageInfos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.show_app_grid);
        init();
        gv = (GridView) findViewById(R.id.gv_apps);
        gv.setAdapter(new GridViewAdapter(this));
    }

    private void init(){
        packageInfos = getPackageManager().getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
    }
   class GridViewAdapter extends BaseAdapter{
       LayoutInflater inflater;
       public GridViewAdapter(Context context){
           inflater = LayoutInflater.from(context);
       }
       @Override
       public int getCount() {
           return packageInfos.size();
       }

       @Override
       public Object getItem(int position) {
           return packageInfos.get(position);
       }

       @Override
       public long getItemId(int position) {
           return position;
       }

       @Override
       public View getView(int position, View convertView, ViewGroup parent) {
             View view = inflater.inflate(R.layout.gv_item,null);
             TextView tv = (TextView) view.findViewById(R.id.gv_item_appname);
             ImageView iv = (ImageView) view.findViewById(R.id.gv_item_icon);
             tv.setText(packageInfos.get(position).applicationInfo.loadLabel(getPackageManager()));
             iv.setImageDrawable(packageInfos.get(position).applicationInfo.loadIcon(getPackageManager()));
             return view;
       }
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
