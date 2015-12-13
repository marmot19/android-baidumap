package com.example.maptest;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends Activity {  
    MapView mMapView = null;  
    private BaiduMap mBaiduMap;
    
    private Context context;
    
//    ��λ���
    private LocationClient mLocationClient;
    private MylocationListener mLocationListener;
    private boolean isFirstIn = true;
    private double mLantitude;
    private double mLongtitude;
//    �Զ��嶨λͼ��
    private BitmapDescriptor mIconLocation;
    
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);   
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //��ʹ��SDK�����֮ǰ��ʼ��context��Ϣ������ApplicationContext  
        //ע��÷���Ҫ��setContentView����֮ǰʵ��  
        SDKInitializer.initialize(getApplicationContext());  
        setContentView(R.layout.activity_main); 
        initView();
//        ��ʼ����λ
        initLocation();
        this.context = this;
    }  
    
    private void initLocation() {
    	mLocationClient = new LocationClient(this);
    	mLocationListener = new MylocationListener(); 
    	mLocationClient.registerLocationListener(mLocationListener);
    	
    	LocationClientOption option= new LocationClientOption();
    	option.setCoorType("bd09ll");
    	option.setIsNeedAddress(true);
    	option.setOpenGps(true);
    	option.setScanSpan(1000);
    	
    	mLocationClient.setLocOption(option);
//    	��ʼ��ͼ��
    	mIconLocation = BitmapDescriptorFactory
    			.fromResource(R.drawable.navi_arrow);
    }
    private void initView(){
    	//��ȡ��ͼ�ؼ�����  
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap(); 
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
        mBaiduMap.setMapStatus(msu);
    }
    @Override  
    protected void onDestroy() {  
        super.onDestroy();  
        //��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���  
        mMapView.onDestroy();  
    }  
    @Override
    protected void onStart() {
    	// TODO Auto-generated method stub
    	super.onStart();
//    	������λ
    	mBaiduMap.setMyLocationEnabled(true);
    	if(!mLocationClient.isStarted())
    		mLocationClient.start();
    }
    
    @Override  
    protected void onResume() {  
        super.onResume();  
        //��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���  
        mMapView.onResume();  
        }  
    
    @Override
    protected void onStop() {
    	// TODO Auto-generated method stub
    	super.onStop();
//    	�رն�λ
    	mBaiduMap.setMyLocationEnabled(false);
    	mLocationClient.stop();
    }
    
    @Override  
    protected void onPause() {  
        super.onPause();  
        //��activityִ��onPauseʱִ��mMapView. onPause ()��ʵ�ֵ�ͼ�������ڹ���  
        mMapView.onPause();  
        }
    
    public boolean onCreateOptionsMenu(Menu menu){
    	getMenuInflater().inflate(R.menu.main, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
    	case R.id.id_map_command:
    		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
    		break;
    	case R.id.id_map_site:
    		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
    		break;
    	case R.id.id_map_traffic:
    		if(mBaiduMap.isTrafficEnabled()) {
    			mBaiduMap.setTrafficEnabled(false);
    			item.setTitle("ʵʱ��ͨ(off)");
    		}else {
    			mBaiduMap.setTrafficEnabled(true);
    			item.setTitle("ʵʱ��ͨ(on)");
    		}
    		break;
    	case  R.id.id_map_reset:
    		centerToMyLocation();
    		break;
    	default:
    		break;
    	}
    	return super.onOptionsItemSelected(item);
    }
    
    /**
     * ��λ���ҵ�λ��
     */
	private void centerToMyLocation() {
		LatLng latLng = new LatLng(mLantitude, mLongtitude);
		MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
		mBaiduMap.animateMapStatus(msu);
	}
    
    private class MylocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			MyLocationData data = new MyLocationData.Builder() //
					.accuracy(location.getRadius())//
					.latitude(location.getLatitude())//
					.longitude(location.getLongitude())//
					.build();
			
			mBaiduMap.setMyLocationData(data);
			MyLocationConfiguration config = new //
					MyLocationConfiguration(LocationMode.NORMAL, true, mIconLocation);
			mBaiduMap.setMyLocationConfigeration(config);
			
			mLantitude = location.getLatitude();
			mLongtitude = location.getLongitude();
			if(isFirstIn) 
			{
				LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
				MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
				mBaiduMap.animateMapStatus(msu);
				isFirstIn = false;
				
				Toast.makeText(context, location.getAddrStr(), 
						Toast.LENGTH_SHORT).show();
			}
		}
    	
    }
}