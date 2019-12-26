package vn.edu.usth.musicplayer;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.napster.cedar.player.data.Track;

import java.net.URL;

public class Adapter extends FragmentPagerAdapter{
    private final int page_count =2;
    public Uri url;

    public Adapter(FragmentManager fm, Uri u){
        super(fm);
        url = u;
    }

    @Override
    public int getCount() {
        return page_count;
    }

    public void setURL(Uri a){
        url = a;

    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = new MenuFragment();
        switch (position){
            case 0:
                frag = new MenuFragment();
                break;
            case 1:
                frag =new MusicPlayerFragment(url);
                break;
        }
        return frag;
    }

    @Override
    public int getItemPosition(Object object){
        return POSITION_NONE;
    }
}
