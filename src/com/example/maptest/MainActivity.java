package com.example.maptest;

<<<<<<< HEAD
import java.util.List;

=======
>>>>>>> e27a7116dd12eee1ab85b11141cba00743eeb04d
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
<<<<<<< HEAD
import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BaiduMap.OnMarkerClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.maptest.MyOrientionListener.OnOrientationListener;
=======
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
>>>>>>> e27a7116dd12eee1ab85b11141cba00743eeb04d
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
<<<<<<< HEAD
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
=======
import android.view.Window;
>>>>>>> e27a7116dd12eee1ab85b11141cba00743eeb04d
import android.widget.Toast;

public class MainActivity extends Activity {  
    MapView mMapView = null;  
    private BaiduMap mBaiduMap;
    
    private Context context;
    
<<<<<<< HEAD
    private LocationMode mLocationMode;
    
//    覆盖物相关
    private BitmapDescriptor mMarker;
    private RelativeLayout mMarkerLy;
    
=======
>>>>>>> e27a7116dd12eee1ab85b11141cba00743eeb04d
//    定位相关
    private LocationClient mLocationClient;
    private MylocationListener mLocationListener;
    private boolean isFirstIn = true;
    private double mLantitude;
    private double mLongtitude;
//    自定义定位图标
    private BitmapDescriptor mIconLocation;
<<<<<<< HEAD
    private MyOrientionListener myOrientationListener;
    private float mCurrentX;
=======
>>>>>>> e27a7116dd12eee1ab85b11141cba00743eeb04d
    
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);   
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext  
        //注意该方法要再setContentView方法之前实现  
        SDKInitializer.initialize(getApplicationContext());  
        setContentView(R.layout.activity_main); 
        initView();
//        初始化定位
<<<<<<< HEAD
        this.context = this;
        initLocation();
        initMarker();
        
        mBaiduMap.setOnMarkerClickListener(new OnMarkerClickListener() {
			
			@Override
			public boolean onMarkerClick(Marker marker) {
				// TODO Auto-generated method stub
				Bundle extraInfo = marker.getExtraInfo();
				Info info = (Info)extraInfo.getSerializable("info");
				ImageView iv = (ImageView)mMarkerLy
						.findViewById(R.id.id_info_img);
				TextView name = (TextView)mMarkerLy
						.findViewById(R.id.id_info_name);
				TextView distance = (TextView)mMarkerLy
						.findViewById(R.id.id_info_distance);
				TextView zan = (TextView)mMarkerLy
						.findViewById(R.id.id_info_zan);
				
				iv.setImageResource(info.getImgId());
				distance.setText(info.getDistance());
				name.setText(info.getName());
				zan.setText(info.getZan() + " ");
				
				mMarkerLy.setVisibility(View.VISIBLE);
				return true;
			}
		});
        mBaiduMap.setOnMapClickListener(new OnMapClickListener() {
			
			@Override
			public boolean onMapPoiClick(MapPoi arg0) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void onMapClick(LatLng arg0) {
				// TODO Auto-generated method stub
				mMarkerLy.setVisibility(View.GONE);
			}
		});
    }  
    
    private void initMarker() {
    	mMarker = BitmapDescriptorFactory.fromResource(R.drawable.icon_gcoding);
    	mMarkerLy = (RelativeLayout) findViewById(R.id.id_marker_layout); 
    }
    private void initLocation() {
    	mLocationMode = LocationMode.NORMAL;
=======
        initLocation();
        this.context = this;
    }  
    
    private void initLocation() {
>>>>>>> e27a7116dd12eee1ab85b11141cba00743eeb04d
    	mLocationClient = new LocationClient(this);
    	mLocationListener = new MylocationListener(); 
    	mLocationClient.registerLocationListener(mLocationListener);
    	
    	LocationClientOption option= new LocationClientOption();
    	option.setCoorType("bd09ll");
    	option.setIsNeedAddress(true);
    	option.setOpenGps(true);
    	option.setScanSpan(1000);
    	
    	mLocationClient.setLocOption(option);
//    	初始化图标
    	mIconLocation = BitmapDescriptorFactory
    			.fromResource(R.drawable.navi_arrow);
<<<<<<< HEAD
    	myOrientationListener = new MyOrientionListener(context);
    	
    	myOrientationListener.setOnOrientationListener(new OnOrientationListener() {
			
			@Override
			public void onOrientationChanged(float x) {
				// TODO Auto-generated method stub
				mCurrentX = x;
			}
		});
=======
>>>>>>> e27a7116dd12eee1ab85b11141cba00743eeb04d
    }
    private void initView(){
    	//获取地图控件引用  
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap(); 
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
        mBaiduMap.setMapStatus(msu);
    }
    @Override  
    protected void onDestroy() {  
        super.onDestroy();  
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理  
        mMapView.onDestroy();  
    }  
    @Override
    protected void onStart() {
    	// TODO Auto-generated method stub
    	super.onStart();
//    	开启定位
    	mBaiduMap.setMyLocationEnabled(true);
    	if(!mLocationClient.isStarted())
    		mLocationClient.start();
<<<<<<< HEAD
//    	开启方向传感器
    	myOrientationListener.start();
=======
>>>>>>> e27a7116dd12eee1ab85b11141cba00743eeb04d
    }
    
    @Override  
    protected void onResume() {  
        super.onResume();  
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理  
        mMapView.onResume();  
        }  
    
    @Override
    protected void onStop() {
    	// TODO Auto-generated method stub
    	super.onStop();
//    	关闭定位
    	mBaiduMap.setMyLocationEnabled(false);
    	mLocationClient.stop();
<<<<<<< HEAD
//    	停止方向传感器
    	myOrientationListener.stop();
=======
>>>>>>> e27a7116dd12eee1ab85b11141cba00743eeb04d
    }
    
    @Override  
    protected void onPause() {  
        super.onPause();  
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理  
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
    			item.setTitle("实时交通(off)");
    		}else {
    			mBaiduMap.setTrafficEnabled(true);
    			item.setTitle("实时交通(on)");
    		}
    		break;
    	case  R.id.id_map_reset:
    		centerToMyLocation();
    		break;
<<<<<<< HEAD
    	case  R.id.id_map_mode_common:
    		mLocationMode = LocationMode.NORMAL;
    		break;
    	case  R.id.id_map_mode_follow:
    		mLocationMode = LocationMode.FOLLOWING;
    		break;
    	case  R.id.id_map_mode_compass:
    		mLocationMode = LocationMode.COMPASS;
    		break;
    	case  R.id.id_add_overlay:
    		addOverlays(Info.infos);
    		break;
=======
>>>>>>> e27a7116dd12eee1ab85b11141cba00743eeb04d
    	default:
    		break;
    	}
    	return super.onOptionsItemSelected(item);
    }
    
    /**
<<<<<<< HEAD
     * 添加覆盖物
     * @param infos
     */
    private void addOverlays(List<Info> infos) {
		// TODO Auto-generated method stub
		mBaiduMap.clear();
		LatLng latLng = null;
		Marker marker = null;
		OverlayOptions options;
		for(Info info : infos) {
//			经纬度
			latLng = new LatLng(info.getLatitude(), info.getLongitude());
//			图标
			options = new MarkerOptions().position(latLng)
					.icon(mMarker).zIndex(5);
			marker = (Marker)mBaiduMap.addOverlay(options);
			Bundle arg0 = new Bundle();
			arg0.putSerializable("info", info);
			marker.setExtraInfo(arg0);
		}
		
		MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
		mBaiduMap.setMapStatus(msu);
	}

	/**
=======
>>>>>>> e27a7116dd12eee1ab85b11141cba00743eeb04d
     * 定位到我的位置
     */
	private void centerToMyLocation() {
		LatLng latLng = new LatLng(mLantitude, mLongtitude);
		MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
		mBaiduMap.animateMapStatus(msu);
	}
    
    private class MylocationListener implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
<<<<<<< HEAD
			MyLocationData data = new MyLocationData.Builder()
					.direction(mCurrentX)//
=======
			MyLocationData data = new MyLocationData.Builder() //
>>>>>>> e27a7116dd12eee1ab85b11141cba00743eeb04d
					.accuracy(location.getRadius())//
					.latitude(location.getLatitude())//
					.longitude(location.getLongitude())//
					.build();
			
			mBaiduMap.setMyLocationData(data);
<<<<<<< HEAD
//			设置自定义图标
			MyLocationConfiguration config = new //
					MyLocationConfiguration(mLocationMode, true, mIconLocation);
			mBaiduMap.setMyLocationConfigeration(config);
//			更新经纬度
=======
			MyLocationConfiguration config = new //
					MyLocationConfiguration(LocationMode.NORMAL, true, mIconLocation);
			mBaiduMap.setMyLocationConfigeration(config);
			
>>>>>>> e27a7116dd12eee1ab85b11141cba00743eeb04d
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