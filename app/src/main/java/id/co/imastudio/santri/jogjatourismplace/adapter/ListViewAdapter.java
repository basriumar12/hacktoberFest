package id.co.imastudio.santri.jogjatourismplace.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import id.co.imastudio.santri.jogjatourismplace.ListWisata;
import id.co.imastudio.santri.jogjatourismplace.R;
import id.co.imastudio.santri.jogjatourismplace.SingleItemView;

public class ListViewAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ArrayList<HashMap<String, String>> data;
	ImageLoader imageLoader;
	HashMap<String, String> resultp = new HashMap<String, String>();

	public ListViewAdapter(Context context,
			ArrayList<HashMap<String, String>> arraylist) {
		this.context = context;
		data = arraylist;
		imageLoader = new ImageLoader(context);
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables
		TextView judul;
		TextView alamat;
		TextView detail;
		ImageView gambar;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.listview_item, parent, false);
		// Get the position
		resultp = data.get(position);

		// Locate the TextViews in listview_item.xml
		judul = (TextView) itemView.findViewById(R.id.judul);
		alamat = (TextView) itemView.findViewById(R.id.alamat);
		detail = (TextView) itemView.findViewById(R.id.detail);

		// Locate the ImageView in listview_item.xml
		gambar = (ImageView) itemView.findViewById(R.id.gambar);

		// Capture position and set results to the TextViews
		judul.setText(resultp.get(ListWisata.JUDUL));
		alamat.setText(resultp.get(ListWisata.ALMAT));
		detail.setText(resultp.get(ListWisata.DETAIL));
		// Capture position and set results to the ImageView
		// Passes flag images URL into ImageLoader.class
		imageLoader.DisplayImage(resultp.get(ListWisata.GAMBAR), gambar);
		// Capture ListView item click
		itemView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// Get the position
				resultp = data.get(position);
				Intent intent = new Intent(context, SingleItemView.class);
				// Pass all data rank
				intent.putExtra("judul", resultp.get(ListWisata.JUDUL));
				// Pass all data country
				intent.putExtra("alamat", resultp.get(ListWisata.ALMAT));
				// Pass all data population
				intent.putExtra("detail",resultp.get(ListWisata.DETAIL));
				// Pass all data flag
				intent.putExtra("gambar", resultp.get(ListWisata.GAMBAR));
				// Start SingleItemView Class
				context.startActivity(intent);

			}
		});
		return itemView;
	}
}
