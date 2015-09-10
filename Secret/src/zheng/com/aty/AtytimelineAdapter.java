/**
 * 
 */
package zheng.com.aty;

import java.util.ArrayList;
import java.util.List;

import zheng.com.R;
import zheng.com.net.Message;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author michael
 *
 */
public class AtytimelineAdapter extends BaseAdapter {
	
	
	private List<Message> list = new ArrayList<Message>();
	
	public void addAll(List<Message> date){
		this.list = date;
		notifyDataSetChanged();
	}
	
	public void clear(){
		this.list.clear();
		notifyDataSetChanged();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount() {
		return list.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Message getItem(int position) {
		return list.get(position);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	private Context context;
	
	/**
	 * 
	 */
	public AtytimelineAdapter(Context context) {
		this.context = context;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.message_cell, null);
			convertView.setTag(new MessCell((TextView) convertView.findViewById(R.id.tvMesCell)));
		}
		
		MessCell messCell = (MessCell) convertView.getTag();
		messCell.getTextView().setText(list.get(position).getMsg());
		
		return convertView;
	}

	public class MessCell{
		
		private TextView textView;
		
		public MessCell(TextView textView){
			this.textView = textView;
		}
		
		public TextView getTextView(){
			return this.textView;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
