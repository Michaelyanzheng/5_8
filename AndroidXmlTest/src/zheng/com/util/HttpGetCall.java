package zheng.com.util;

import java.io.IOException;
import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class HttpGetCall {
	
	private static final String TAG = "HttpGetCall";

	public HttpGetCall(String url,final HttpGetCall.SuccessCallback successCallback,final HttpGetCall.FailCallback failCallback) {
		new HttpResponse(url, new zheng.com.util.HttpResponse.SuccessCallback() {
			
			@Override
			public void onSuccess(String result) {
				
				try {
					XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
					XmlPullParser xmlPullParser = factory.newPullParser();
					xmlPullParser.setInput(new StringReader(result));
					
					String id = "";
					String name = "";
					String version = "";
					
					StringBuffer resultBuffer = new StringBuffer();
					
					int event = xmlPullParser.getEventType();
					while (event != XmlPullParser.END_DOCUMENT) {
						
						String eventName = xmlPullParser.getName();
						xmlPullParser.getAttributeCount();
						
						switch (event) {
						case XmlPullParser.START_TAG:
							if (Config.XML_ID.equals(eventName)) {
								id = xmlPullParser.nextText();
								resultBuffer.append(eventName).append("--").append(id).append("\n");
							}else if (Config.XML_NAME.equals(eventName)) {
								name = xmlPullParser.nextText();
								resultBuffer.append(eventName).append("--").append(name).append("\n");
							}else if (Config.XML_VERSION.equals(eventName)) {
								version = xmlPullParser.nextText();
								resultBuffer.append(eventName).append("--").append(version).append("\n");
							}else if ("app".equals(eventName)) {
								for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
									resultBuffer.append("app").append("--").append(xmlPullParser.getAttributeValue(i)).append("\n");
								};
							}
							
							break;
//						case XmlPullParser.END_TAG:
//							if ("app".equals(eventName)) {
//								
//								resultBuffer.append(id).append("\n").append(name).append("\n").append(version);
//							}

						default:
							break;
						}
						event = xmlPullParser.next();
					}
					
					if (successCallback != null) {
						successCallback.onSuccess(resultBuffer.toString());
					}
					
				} catch (XmlPullParserException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, new zheng.com.util.HttpResponse.FailCallback() {
			
			@Override
			public void onFail() {
				
				
			}
		});
	}

	public interface SuccessCallback{
		void onSuccess(String result);
	}
	public interface FailCallback{
		void onFail();
	}
}
