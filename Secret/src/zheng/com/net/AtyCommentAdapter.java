/**
 * 
 */
package zheng.com.net;

import java.util.ArrayList;
import java.util.List;

import zheng.com.R;
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
public class AtyCommentAdapter extends BaseAdapter {
	
	private Context context = null;
	
	public Context getContext(){
		return this.context;
	}
	
	public AtyCommentAdapter(Context context){
		this.context = context;
	}
	
	private List<Comment> list = new ArrayList<Comment>();
	
	public void addAll(List<Comment> data){
		this.list = data;
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
		return this.list.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Comment getItem(int position) {
		return list.get(position);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		return position;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.comment_cell, null);
			convertView.setTag(new CommentCell((TextView) convertView.findViewById(R.id.tvComCell)));
		}
		
		CommentCell commentCell = (CommentCell) convertView.getTag();
		commentCell.getTextView().setText(this.list.get(position).getContent());
		
		return convertView;
	}
	
	class CommentCell{
		private TextView textView;
		public CommentCell(TextView textView){
			this.textView = textView;
		}
		public TextView getTextView(){
			return this.textView;
		}
	}
	

}
