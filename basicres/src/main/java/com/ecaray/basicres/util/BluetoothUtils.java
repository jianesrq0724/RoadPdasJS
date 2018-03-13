package com.ecaray.basicres.util;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * 蓝牙打印,排版打印格式
 */
@SuppressLint("NewApi")
public class BluetoothUtils {

    public static final String TAG = BluetoothUtils.class.getSimpleName();

    /**
     * 打印纸一行最大的字节
     */
    private static final int LINE_BYTE_SIZE = 32;
    /**
     * 分隔符
     */

    private static StringBuffer sb = new StringBuffer();

    /**
     * 排版居中标题
     */
    public static String printTitle(String title) {
        sb.delete(0, sb.length());
        for (int i = 0; i < (LINE_BYTE_SIZE - getBytesLength(title)) / 2; i++) {
            sb.append(" ");
        }
        sb.append(title);
        return sb.toString();
    }

    /**
     * 排版居中内容(以':'对齐)
     * <p/>
     * 例：姓名：李白 病区：5A病区 住院号：11111
     */
    public static String printMiddleMsg(LinkedHashMap<String, String> middleMsgMap) {
        sb.delete(0, sb.length());
        int i = 0;
        for (Entry<String, String> middleEntry : middleMsgMap.entrySet()) {
            if(!TextUtils.isEmpty(middleEntry.getKey())){
                sb.append(middleEntry.getKey()).append(" : ").append(middleEntry.getValue());
            }else {
                sb.append(middleEntry.getValue());
            }
            i++;
            if (i != middleMsgMap.size()) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
    /**
     * 方法描述：给内容添加空格行数 不带虚线
     *<p>
     * @param   rows 间隔的行数
     * @return  void
     */
    public static String setPaperSpaceRows(int rows){
        String spaceStr = "";
        for (int i = 0; i <rows ; i++) {
            spaceStr += "\n";
        }
        return spaceStr ;
    }

    /**
     * 方法描述：给头部添加空格行数 带虚线
     *<p>
     * @param rows 间隔的行数
     * @return
     */
    public static String setPaperSpaceRowsForDot(int rows){
        String headSpaceStr = setPaperSpaceRows(rows)+"--------------------------------"+ setPaperSpaceRows(rows);
        return  headSpaceStr;
    }

    /**
     * 排版居中内容(以':'对齐)
     * <p/>
     * 例：姓名：李白 病区：5A病区 住院号：11111
     */
    public static String printMiddleMsg4HF(LinkedHashMap<String, String> middleMsgMap) {
        sb.delete(0, sb.length());
        for (Entry<String, String> middleEntry : middleMsgMap.entrySet()) {
            sb.append(middleEntry.getKey()).append(" : ").append(middleEntry.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }


    /**
     * 获取数据长度
     */
    private static int getBytesLength(String msg) {
        return msg.getBytes(Charset.forName("GB2312")).length;
    }

    public static String getDash(int line_byte_size){
        sb.delete(0, sb.length());
        for (int i = 0; i < line_byte_size; i++) {
            sb.append("-");
        }
        return sb.toString();
    }

    /**
     * 虚线
     */
    public static String getDash() {
        sb.delete(0, sb.length());
        for (int i = 0; i < LINE_BYTE_SIZE; i++) {
            sb.append("-");
        }
        return sb.toString();
    }

    /**
     * 与设备配对 参考源码：platform/packages/apps/Settings.git
     * /Settings/src/com/android/settings/bluetooth/CachedBluetoothDevice.java
     */
    static public boolean createBond(Class btClass, BluetoothDevice btDevice)
            throws Exception {
        Method createBondMethod = btClass.getMethod("createBond");
        return (Boolean) createBondMethod.invoke(btDevice);
    }

    /**
     * 与设备解除配对 参考源码：platform/packages/apps/Settings.git
     * /Settings/src/com/android/settings/bluetooth/CachedBluetoothDevice.java
     */
    static public boolean removeBond(Class btClass, BluetoothDevice btDevice)
            throws Exception {
        Method removeBondMethod = btClass.getMethod("removeBond");
        return (Boolean) removeBondMethod.invoke(btDevice);
    }

    static public boolean setPin(Class btClass, BluetoothDevice btDevice,
                                 String str) throws Exception {
        try {
            Method removeBondMethod = btClass.getDeclaredMethod("setPin", byte[].class);
            Boolean returnValue = (Boolean) removeBondMethod.invoke(btDevice,
                    new Object[]
                            {str.getBytes()});
            LogUtils.i(returnValue.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;

    }

    /**
     * 取消用户输入
     * @param btClass
     * @param device
     * @return
     * @throws Exception
     */
    static public boolean cancelPairingUserInput(Class btClass,
                                                 BluetoothDevice device)

            throws Exception {
        Method createBondMethod = btClass.getMethod("cancelPairingUserInput");
        // cancelBondProcess()
        return (Boolean) createBondMethod.invoke(device);
    }

    /**
     * 取消配对
     * @param btClass
     * @param device
     * @return
     * @throws Exception
     */
    static public boolean cancelBondProcess(Class btClass,
                                            BluetoothDevice device)

            throws Exception {
        Method createBondMethod = btClass.getMethod("cancelBondProcess");
        return (Boolean) createBondMethod.invoke(device);
    }


    static public void printAllInform(Class clsShow) {
        try {
            // 取得所有方法
            Method[] hideMethod = clsShow.getMethods();
            int i = 0;
            for (; i < hideMethod.length; i++) {
                LogUtils.i("method name", hideMethod[i].getName() + ";and the i is:" + i);
            }
            // 取得所有常量
            Field[] allFields = clsShow.getFields();
            for (i = 0; i < allFields.length; i++) {
                LogUtils.i("Field name", allFields[i].getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 方法描述：执行自动连接蓝牙功能
     */
    public static boolean pair(String strAddr, String strPsw) {
        boolean result = false;
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter
                .getDefaultAdapter();

        bluetoothAdapter.cancelDiscovery();

        if (!bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.enable();
        }

        // 检查蓝牙地址是否有效
        if (!BluetoothAdapter.checkBluetoothAddress(strAddr)) {
            LogUtils.i("devAdd un effient!");
        }

        BluetoothDevice device = bluetoothAdapter.getRemoteDevice(strAddr);

        if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
            try {
                LogUtils.i(TAG, "NOT BOND_BONDED");
                // 手机和蓝牙采集器配对
                setPin(device.getClass(), device, strPsw);
                createBond(device.getClass(), device);
                result = true;
            } catch (Exception e) {

                LogUtils.i(TAG, "setPiN failed!");
                e.printStackTrace();
            }

        } else {
            LogUtils.i(TAG, "HAS BOND_BONDED");
            try {
                createBond(device.getClass(), device);
                // 手机和蓝牙采集器配对
                setPin(device.getClass(), device, strPsw);
                createBond(device.getClass(), device);
                result = true;
            } catch (Exception e) {
                LogUtils.i(TAG, "setPiN failed!");
                e.printStackTrace();
            }
        }
        return result;
    }

}
