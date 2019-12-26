package vn.edu.usth.musicplayer;

import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.napster.cedar.Napster;
import com.napster.cedar.player.Player;
import com.napster.cedar.player.data.Track;
import com.napster.cedar.session.SessionManager;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SongFragment extends Fragment {
    JSONArray listSong = null;

    protected SessionManager sessionManager;
    public SongFragment(JSONArray a){
        listSong = a;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.scroll_view,container,false);
        LinearLayout display = view.findViewById(R.id.displayList);
        for (int i = 0; i < listSong.length(); i++) {
            JSONObject a = null;
            try {
                a = listSong.getJSONObject(i);
                final Uri url = Uri.parse(a.getString("previewURL"));

                LinearLayout big_container = new LinearLayout(getContext());
                //
                big_container.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("pay","123");
                        ((MainActivity)getActivity()).songToggle(url);
                    }
                });
                big_container.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                                view.setAlpha(0.3f);
                        } else if(motionEvent.getAction() == MotionEvent.ACTION_UP ||
                                    motionEvent.getAction() == MotionEvent.ACTION_CANCEL ) {
                            view.setAlpha(1f);

                        }

                        return false;
                    }
                });



                //
                ImageView image = new ImageView(getContext());
                int paddingPx5 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5f, getContext().getResources().getDisplayMetrics());
                int paddingPx20 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 11f, getContext().getResources().getDisplayMetrics());
                int paddingPx10 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, getContext().getResources().getDisplayMetrics());
                image.setPadding(paddingPx10, paddingPx10, paddingPx10, paddingPx10);
                //Setup Picasso Http
                OkHttpClient client = new OkHttpClient.Builder()
                        .addInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request newRequest = chain.request().newBuilder()
                                        .addHeader("apikey", "YjkxYzdlZGEtNzllMy00OGE4LTg4M2EtMGEzZTU4ODZlOGQ2")
                                        .build();
                                return chain.proceed(newRequest);
                            }
                        }).build();
                Picasso picasso = new Picasso.Builder(getContext())
                        .downloader(new OkHttp3Downloader(client))
                        .build();
                Picasso.get().load("https://api.napster.com/imageserver/v2/albums/" + a.getString("albumId") + "/images/200x200.jpg").into(image);
                LinearLayout small_container = new LinearLayout(getContext());
                small_container.setOrientation(LinearLayout.VERTICAL);
                TextView text1 = new TextView(getContext());
                text1.setText(a.getString("name"));
                text1.setTextSize(paddingPx20);
                text1.setTextColor(getResources().getColor(android.R.color.white));
                text1.setPadding(0, paddingPx10, 0, 0);
                TextView text2 = new TextView(getContext());
                text2.setTextSize(paddingPx5);
                text2.setText((a.getString("artistName")));
                text2.setTextColor(getResources().getColor(android.R.color.darker_gray));
                text2.setPadding(0, paddingPx5, 0, 0);
                TextView text3 = new TextView(getContext());
                text3.setText("Song Descrition");
                text3.setTextSize(paddingPx5);
                text3.setTextColor(getResources().getColor(android.R.color.darker_gray));
                text3.setPadding(0, paddingPx5, 0, 0);
                small_container.addView(text1);
                small_container.addView(text2);
                small_container.addView(text3);
                big_container.addView(image);
                big_container.addView(small_container);
                display.addView(big_container);
                View br = new View(getContext());
                br.setMinimumHeight(5);
                br.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                display.addView(br);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        return view;

    }
}

