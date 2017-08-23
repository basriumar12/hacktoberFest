package id.co.imastudio.santri.jogjatourismplace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import id.co.imastudio.santri.jogjatourismplace.adapter.ImageLoader;

public class SingleItemView extends AppCompatActivity {

    // Declare Variables
    String judul;
    String alamat;
    String detail;
    String gambar;
    String position;
    ImageLoader imageLoader = new ImageLoader(this);
    private Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // Get the view from singleitemview.xml
        setContentView(R.layout.activity_single_item_view);

        Intent i = getIntent();

        judul = i.getStringExtra("judul");

        alamat = i.getStringExtra("alamat");

        detail = i.getStringExtra("detail");

        gambar = i.getStringExtra("gambar");

        // Locate the TextViews in singleitemview.xml
        TextView judulSi = (TextView) findViewById(R.id.judulSi);
        TextView alamatSI = (TextView) findViewById(R.id.alamatSi);
        TextView detailSi = (TextView) findViewById(R.id.detailSi);

        // Locate the ImageView in singleitemview.xml
        ImageView imgflag = (ImageView) findViewById(R.id.imgflag);

        // Set results to the TextViews
        judulSi.setText(judul);
        alamatSI.setText(alamat);
        detailSi.setText(detail);

        imageLoader.DisplayImage(gambar, imgflag);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}