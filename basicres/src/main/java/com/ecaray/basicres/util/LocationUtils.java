package com.ecaray.basicres.util;

import android.text.TextUtils;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.ecaray.basicres.base.BaseApplication;

import java.util.List;

/**
 * 类描述:
 * @author : Eric_Huang
 * 创建时间: 2016/9/9 15:53
 * 修改人:Eric_Huang
 * 修改时间: 2016/9/9 15:53
 */
public class LocationUtils {

    protected static LocationUtils sLocationUtils;
    private LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();
    public LocationCallback mLocationCallback;

    private LocationUtils() {
        if (mLocationClient == null) {
            mLocationClient = new LocationClient(BaseApplication.getInstance());
            initLocation();
            setMyListener();
        }
    }

    public static synchronized LocationUtils getInstance() {
        if (sLocationUtils == null) {
            sLocationUtils = new LocationUtils();
        }
        return sLocationUtils;
    }

    /**
     * 初始化配置
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，默认gcj02，设置返回的定位结果坐标系
        option.setCoorType("bd09ll");
        int span = 0;
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setScanSpan(span);
        //可选，设置是否需要地址信息，默认不需要
        option.setIsNeedAddress(true);
        //可选，默认false,设置是否使用gps
        option.setOpenGps(true);
        //可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setLocationNotify(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIsNeedLocationPoiList(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setIgnoreKillProcess(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
    }

    public void setMyListener() {
        if (mLocationClient != null && myListener != null) {
            mLocationClient.registerLocationListener(myListener);
        } else {
            LogUtils.i("定位服务与监听为null导致不能定位");
        }
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
            StringBuilder sb = new StringBuilder(256);
            sb.append("time : ");
            sb.append(location.getTime());
            sb.append("\nerror code : ");
            sb.append(location.getLocType());
            sb.append("\nlatitude : ");
            sb.append(location.getLatitude());
            sb.append("\nlontitude : ");
            sb.append(location.getLongitude());
            sb.append("\nradius : ");
            sb.append(location.getRadius());
            // GPS定位结果
            if (location.getLocType() == BDLocation.TypeGpsLocation) {
                sb.append("\nspeed : ");
                // 单位：公里每小时
                sb.append(location.getSpeed());
                sb.append("\nsatellite : ");
                sb.append(location.getSatelliteNumber());
                sb.append("\nheight : ");
                // 单位：米
                sb.append(location.getAltitude());
                sb.append("\ndirection : ");
                // 单位度
                sb.append(location.getDirection());
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                sb.append("\ndescribe : ");
                sb.append("gps定位成功");
                // 网络定位结果
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                sb.append("\naddr : ");
                sb.append(location.getAddrStr());
                //运营商信息
                sb.append("\noperationers : ");
                sb.append(location.getOperators());
                sb.append("\ndescribe : ");
                sb.append("网络定位成功");
                // 离线定位结果
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {
                sb.append("\ndescribe : ");
                sb.append("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                sb.append("\ndescribe : ");
                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                sb.append("\ndescribe : ");
                sb.append("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                sb.append("\ndescribe : ");
                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
            sb.append("\nlocationdescribe : ");
            // 位置语义化信息
            sb.append(location.getLocationDescribe());
            // POI数据
            List<Poi> list = location.getPoiList();
            if (list != null) {
                sb.append("\npoilist size = : ");
                sb.append(list.size());
                for (Poi p : list) {
                    sb.append("\npoi= : ");
                    sb.append(p.getId()).append(" ").append(p.getName()).append(" ").append(p.getRank());
                }
            }
            Log.i("BaiduLocationApiDem", sb.toString());

            //保存经纬度
            SPUtils.put(SPKeyUtils.s_Longitude, location.getLongitude());
            SPUtils.put(SPKeyUtils.s_Latitude, location.getLatitude());
            SPUtils.put(SPKeyUtils.s_ADDRESS, location.getAddrStr());

            //保存省份
            if (TextUtils.isEmpty(location.getProvince()) ||
                    !SPUtils.get(SPKeyUtils.s_Province, "").equals(location.getProvince())) {
                SPUtils.put(SPKeyUtils.s_Province, location.getProvince().substring(0, 2));
            }

            //得到定位后，回调
            if (mLocationCallback != null) {
                mLocationCallback.takeLocation(location);
            }

            //需要暂停，再次定位start时才能成功
            mLocationClient.stop();
        }
    }

    /**
     * 重新定位一次
     *
     * @param getLocation 回调事件
     */
    public void getLocation(LocationCallback getLocation) {
        mLocationCallback = getLocation;
        //重新定位，获取回调
        if (mLocationClient != null) {
            if (mLocationClient.isStarted()) {
                mLocationClient.stop();
            }
            mLocationClient.start();
        }
    }

    public void getLocation() {
        if (mLocationClient != null) {
            if (mLocationClient.isStarted()) {
                mLocationClient.stop();
            }
            mLocationClient.start();
        }
    }

    /**
     * 移除定位，防止内存泄漏
     */
    public void removeLocation() {
        if (mLocationCallback != null) {
            mLocationCallback = null;
        }
    }

    public interface LocationCallback {
        void takeLocation(BDLocation location);
    }

}
