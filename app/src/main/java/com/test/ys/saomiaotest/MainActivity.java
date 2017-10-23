package com.test.ys.saomiaotest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.printsdk.cmd.PrintCmd;
import com.printsdk.usbsdk.UsbDriver;
import com.youngsee.posterdisplayer.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class MainActivity
        extends Activity {

    private UsbDevice m_Device;
    SimpleDateFormat m_sdfDate = new SimpleDateFormat("HH:mm:ss");
    View main;
    String name = "";
    String normal = "";
    String notConnectedOrNotPopwer = "";
    String notMatch = "";
    private String num = "";
    String paperExh = "";
    String paperWillExh = "";
    PopupWindow popuWindow;
    String printHeadOverheated = "";
    String printerHeadOpen = "";
    String receive = "";
    List<Request1301> request1301List = null;
    List<Request3030> request3030List = null;
    Response1301 response1301 = null;
    Response3030 response3030 = null;
    Request1301 request1301;
    private int rotate = 0;




    private static final String ACTION_USB_PERMISSION = "com.usb.sample.USB_PERMISSION";
    static int Number = 1000;
    static String endPoint;
    static String endPoint2;
    static String methodName;
    static String methodName2;
    static String nameSpace;
    static String nameSpace2;
    static String soapAction;
    static String soapAction2;
    public static boolean zantingfuwu = false;
    private int QrSize = 1;
    String abnormal = "";
    private int align = 0;
    String blackMarkError = "";
    private Button bt;
    private int clickFlag = 1;
    private String codeStr = "";
    private int cutter = 0;
    String cutterNotReset = "";
    Handler dialoghandler = new Handler();
    Runnable dialogrunnable = new Runnable() {
        public void run() {
            MainActivity.this.dialoghandler.postDelayed(this, 1000L);
            if (MainActivity.this.dialogtime > 0) {
                dialogtime = dialogtime - 1;
                if ((MainActivity.this.popuWindow.isShowing()) && dialogtime <= 0) {
                    MainActivity.this.popuWindow.dismiss();
                }
            }

        }
    };
    public int dialogtime = 0;
    public int downX = 0;
    public int downY = 0;
    Handler fangdouhandler = new Handler();
    Runnable fangdourunnable = new Runnable() {
        public void run() {
            MainActivity localMainActivity = MainActivity.this;
            localMainActivity.fangdoutime += 1;
            if (MainActivity.this.fangdoutime > 100000) {
                MainActivity.this.fangdoutime = 0;
            }
            MainActivity.this.fangdouhandler.postDelayed(this, 1000L);
        }
    };
    int fangdoutime = 0;
    Handler handler = new Handler() {
        public void handleMessage(Message paramAnonymousMessage) {
            switch (paramAnonymousMessage.what) {
                default:
                    return;
                case 1301:
                    MainActivity.this.t.setText("服务器异常：1301");
                    if (MainActivity.this.popuWindow.isShowing()) {
                        MainActivity.this.popuWindow.dismiss();
                    }
                    MainActivity.this.dialogtime = 5;
                    MainActivity.this.popuWindow.showAtLocation(MainActivity.this.main, 17, 0, 0);
                    return;
                case 3030:
                    MainActivity.this.t.setText("服务器异常：3030");
                    if (MainActivity.this.popuWindow.isShowing()) {
                        MainActivity.this.popuWindow.dismiss();
                    }
                    MainActivity.this.dialogtime = 5;
                    MainActivity.this.popuWindow.showAtLocation(MainActivity.this.main, 17, 0, 0);
                    return;
                case 6:
                    Toast.makeText(MainActivity.this.getApplicationContext(), (String) paramAnonymousMessage.obj, Toast.LENGTH_SHORT).show();
                    MainActivity.this.t.setText("报到失败-没有可报到的挂号记录");
                    if (MainActivity.this.popuWindow.isShowing()) {
                        MainActivity.this.popuWindow.dismiss();
                    }
                    MainActivity.this.dialogtime = 5;
                    MainActivity.this.popuWindow.showAtLocation(MainActivity.this.main, 17, 0, 0);
                    return;
                case 5:
                    MainActivity.this.response1301 = ((Response1301) paramAnonymousMessage.obj);
                    if (MainActivity.this.response1301 == null) {
                        MainActivity.this.t.setText("报到失败：1301");
                        if (MainActivity.this.popuWindow.isShowing()) {
                            MainActivity.this.popuWindow.dismiss();
                        }
                        MainActivity.this.dialogtime = 5;
                        MainActivity.this.popuWindow.showAtLocation(MainActivity.this.main, 17, 0, 0);
                        return;
                    }
                    if (!MainActivity.this.PrintConnStatus()) {
                        Toast.makeText(MainActivity.this.getApplicationContext(), "打印机未开始打印，请重试！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    String a = MainActivity.this.response1301.getTip().replace("^", "\n\n");
                    MainActivity.this.getPrintTicketData(MainActivity.this.mUsbDev1);
                    MainActivity.this.t.setText(a);
                    if (MainActivity.this.popuWindow.isShowing()) {
                        MainActivity.this.popuWindow.dismiss();
                    }
                    MainActivity.this.dialogtime = 5;
                    MainActivity.this.popuWindow.showAtLocation(MainActivity.this.main, 17, 0, 0);
                    return;
                case 3:
                    Toast.makeText(MainActivity.this.getApplication(), "报到成功", Toast.LENGTH_SHORT).show();
                    return;
                case 1:
                    try {
                        MainActivity.this.response3030 = ((Response3030) paramAnonymousMessage.obj);
                        Log.i("报到", "case 1:");
                        if (MainActivity.this.response3030 == null) {
                            Log.i("报到", "response3030==null");
                            MainActivity.this.t.setText("无此病人信息,请重新刷卡！");
                            if (MainActivity.this.popuWindow.isShowing()) {
                                MainActivity.this.popuWindow.dismiss();
                            }
                            MainActivity.this.dialogtime = 5;
                            MainActivity.this.popuWindow.showAtLocation(MainActivity.this.main, 17, 0, 0);
                            return;
                        } else {
                            if (TextUtils.equals(MainActivity.this.name, "")) {
                                MainActivity.this.t.setText("请联系工作人员设置计算机名称！");
                                if (MainActivity.this.popuWindow.isShowing()) {
                                    MainActivity.this.popuWindow.dismiss();
                                }
                                MainActivity.this.dialogtime = 5;
                                MainActivity.this.popuWindow.showAtLocation(MainActivity.this.main, 17, 0, 0);
                                return;
                            }

                            MainActivity.this.request1301List.clear();
                            request1301 = new Request1301();
                            request1301.setTradeCode("1301");
                            request1301.setExtOrgCode("");
                            request1301.setClientType("");
                            request1301.setHospitalId("");
                            request1301.setExtUserID(MainActivity.this.name);
                            request1301.setPatientID(MainActivity.this.response3030.getPatientID());
                            request1301.setServiceName("");
                            request1301.setIDType("2");
                            request1301.setMachineID("");
                            request1301.setZoneID("");
                            request1301.setAdmNo("");
                            MainActivity.this.request1301List.add(request1301);
                            Log.i("SOAPtest", "request1301.tostring:" + paramAnonymousMessage.toString());
                            MainActivity.this.response3030 = null;
                            String request1301string = pullpuser.save1301(request1301List);
                            Log.i("SOAPtest", "request1301.pull:" + paramAnonymousMessage);
                            if ((paramAnonymousMessage != null) && (!TextUtils.equals(request1301string, ""))) {
                                MainActivity.this.getOPAllocAutoReport(request1301string);
                                MainActivity.this.request1301List.clear();
                                return;
                            } else {
                                Log.i("SOAPtest", "handler.case1.pull==null");
                                Toast.makeText(MainActivity.this.getApplicationContext(), "报到失败", Toast.LENGTH_SHORT).show();

                                MainActivity.this.t.setText("报到失败! /n" + (String) paramAnonymousMessage.obj);
                                if (MainActivity.this.popuWindow.isShowing()) {
                                    MainActivity.this.popuWindow.dismiss();
                                }
                                MainActivity.this.dialogtime = 5;
                                MainActivity.this.popuWindow.showAtLocation(MainActivity.this.main, 17, 0, 0);
                                return;
                            }


                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }


            }

        }
    };
    int houmen = 0;
    private int linespace = 40;
    UsbDevice mUsbDev1;
    UsbDevice mUsbDev2;
    UsbDriver mUsbDriver;
    private UsbManager mUsbManager;
    /*
     *  BroadcastReceiver when insert/remove the device USB plug into/from a USB port
     *  创建一个广播接收器接收USB插拔信息：当插入USB插头插到一个USB端口，或从一个USB端口，移除装置的USB插头
     */
    BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
                if (mUsbDriver.usbAttached(intent)) {
                    UsbDevice device = (UsbDevice) intent
                            .getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if ((device.getProductId() == 8211 && device.getVendorId() == 1305)
                            || (device.getProductId() == 8213 && device
                            .getVendorId() == 1305)) {
                        if (mUsbDriver.openUsbDevice(device)) {
                            if (device.getProductId() == 8211)
                                mUsbDev1 = device;
                            else
                                mUsbDev2 = device;
                        }
                    }
                }
            } else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
                UsbDevice device = (UsbDevice) intent
                        .getParcelableExtra(UsbManager.EXTRA_DEVICE);
                if ((device.getProductId() == 8211 && device.getVendorId() == 1305)
                        || (device.getProductId() == 8213 && device
                        .getVendorId() == 1305)) {
                    mUsbDriver.closeUsbDevice(device);
                    if (device.getProductId() == 8211)
                        mUsbDev1 = null;
                    else
                        mUsbDev2 = null;
                }
            } else if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    UsbDevice device = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        if ((device.getProductId() == 8211 && device.getVendorId() == 1305)
                                || (device.getProductId() == 8213 && device
                                .getVendorId() == 1305)) {
                            if (mUsbDriver.openUsbDevice(device)) {
                                if (device.getProductId() == 8211)
                                    mUsbDev1 = device;
                                else
                                    mUsbDev2 = device;
                            }
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "permission denied for device",
                                Toast.LENGTH_SHORT).show();
                        //Log.d(TAG, "permission denied for device " + device);
                    }
                }
            }
        }
    };

    Runnable runnable = new Runnable() {
        public void run() {
            String str = new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss     ").format(new Date(System.currentTimeMillis()));
            MainActivity.this.time.setText(str);
            MainActivity.this.timehandler.postDelayed(this, 1000L);
        }
    };
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private boolean shareFlag = false;
    String state = "";
    private String strData = "";
    TextView t;
    boolean test = false;
    String text = "";
    TextView time;
    Handler timehandler = new Handler();
    private String title = "";
    TextView tv_text;
    String twocode = "";
    String twocode2 = "";
    private int underLine = 0;
    View view;

    static {
        nameSpace = "http://tempuri.org";
        methodName = "GetPatInfo";
        endPoint = "http://192.168.102.20/csp/i-empi/DHC.Doctor.BS.SendPatInfoToDoctor.CLS";
        soapAction = "http://tempuri.org/GetPatInfo";
        nameSpace2 = "http://tempuri.org";
        methodName2 = "OPAllocAutoReport";
        endPoint2 = "http://192.168.102.20/csp/i-empi/DHC.Doctor.BS.SendPatInfoToDoctor.CLS";
        soapAction2 = "http://tempuri.org/OPAllocAutoReport";
    }

    // Get UsbDriver(UsbManager) service
    private boolean PrintConnStatus() {

        boolean blnRtn = false;
        try {
            if (!mUsbDriver.isConnected()) {
//				UsbManager m_UsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
                // USB线已经连接
                for (UsbDevice device : mUsbManager.getDeviceList().values()) {
                    if ((device.getProductId() == 8211 && device.getVendorId() == 1305)
                            || (device.getProductId() == 8213 && device
                            .getVendorId() == 1305)) {
                        blnRtn = mUsbDriver.usbAttached(device);
                        if (blnRtn == false)
                            break;
                        blnRtn = mUsbDriver.openUsbDevice(device);
                        // 打开设备
                        if (blnRtn) {
                            if (device.getProductId() == 8211)
                                mUsbDev1 = device;
                            else
                                mUsbDev2 = device;
                            Toast.makeText(this, getString(R.string.USB_Driver_Success),
                                    Toast.LENGTH_SHORT).show();
                            break;
                        } else {
                            Toast.makeText(this, getString(R.string.USB_Driver_Failed), Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                }
            } else {
                blnRtn = true;
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
        return blnRtn;
    }

    private void SetFeedCutClean(int paramInt) {
        this.mUsbDriver.write(PrintCmd.PrintFeedline(5));
        this.mUsbDriver.write(PrintCmd.PrintCutpaper(paramInt));
        this.mUsbDriver.write(PrintCmd.SetClean());
    }

    private void SetFeedCutClean(int paramInt, UsbDevice paramUsbDevice) {
        this.mUsbDriver.write(PrintCmd.PrintFeedline(5), paramUsbDevice);
        this.mUsbDriver.write(PrintCmd.PrintCutpaper(paramInt), paramUsbDevice);
        this.mUsbDriver.write(PrintCmd.SetClean(), paramUsbDevice);
    }

    private void ShowMessage(String paramString) {
        Toast.makeText(getApplicationContext(), this.m_sdfDate.format(new Date()) + paramString + "\r\n", Toast.LENGTH_SHORT).show();
    }

    private int checkStatus(int iStatus) {
        int iRet = -1;

        StringBuilder sMsg = new StringBuilder();

        //0 打印机正常 、1 打印机未连接或未上电、2 打印机和调用库不匹配
        //3 打印头打开 、4 切刀未复位 、5 打印头过热 、6 黑标错误 、7 纸尽 、8 纸将尽
        switch (iStatus) {
            case 0:
                sMsg.append(normal);       // 正常
                iRet = 0;
                break;
            case 8:
                sMsg.append(paperWillExh); // 纸将尽
                iRet = 0;
                break;
            case 3:
                sMsg.append(printerHeadOpen); //打印头打开
                break;
            case 4:
                sMsg.append(cutterNotReset);
                break;
            case 5:
                sMsg.append(printHeadOverheated);
                break;
            case 6:
                sMsg.append(blackMarkError);
                break;
            case 7:
                sMsg.append(paperExh);     // 纸尽==缺纸
                break;
            case 1:
                sMsg.append(notConnectedOrNotPopwer);
                break;
            default:
                sMsg.append(abnormal);     // 异常
                break;
        }

        ShowMessage(sMsg.toString());
        return iRet;

    }

    private void findView() {
        tv_text = ((TextView) findViewById(R.id.gg));
        time = ((TextView) findViewById(R.id.tv));
        SharedPreferences localSharedPreferences = getSharedPreferences("TR", 0);
        name = localSharedPreferences.getString("name", "");
        text = localSharedPreferences.getString("text", "");
        if (TextUtils.equals(this.text, "")) {
            MessageDialog.getInstance(this).setMsg("请设置计算机名称！！！").show();
        }
        tv_text.setText(this.text);
        timehandler.postDelayed(this.runnable, 0L);
    }

    private void getCommonSettings(UsbDevice paramUsbDevice) {
        this.mUsbDriver.write(PrintCmd.SetAlignment(this.align), paramUsbDevice);
        this.mUsbDriver.write(PrintCmd.SetRotate(this.rotate), paramUsbDevice);
        this.mUsbDriver.write(PrintCmd.SetUnderline(this.underLine), paramUsbDevice);
        this.mUsbDriver.write(PrintCmd.SetLinespace(this.linespace), paramUsbDevice);
    }

    private void getMsgByLanguage() {
        if (Utils.isZh(this)) {
            this.receive = Constant.Receive_CN;
            this.state = Constant.State_CN;
            this.normal = Constant.Normal_CN;
            this.notConnectedOrNotPopwer = Constant.NoConnectedOrNoOnPower_CN;
            this.notMatch = Constant.PrinterAndLibraryNotMatch_CN;
            this.printerHeadOpen = Constant.PrintHeadOpen_CN;
            this.cutterNotReset = Constant.CutterNotReset_CN;
            this.printHeadOverheated = Constant.PrintHeadOverheated_CN;
            this.blackMarkError = Constant.BlackMarkError_CN;
            this.paperExh = Constant.PaperExhausted_CN;
            this.paperWillExh = Constant.PaperWillExhausted_CN;
            this.abnormal = Constant.Abnormal_CN;
            return;
        }
        this.receive = Constant.Receive_US;
        this.state = Constant.State_US;
        this.normal = Constant.Normal_US;
        this.notConnectedOrNotPopwer = Constant.NoConnectedOrNoOnPower_US;
        this.notMatch = Constant.PrinterAndLibraryNotMatch_US;
        this.printerHeadOpen = Constant.PrintHeadOpen_US;
        this.cutterNotReset = Constant.CutterNotReset_US;
        this.printHeadOverheated = Constant.PrintHeadOverheated_US;
        this.blackMarkError = Constant.BlackMarkError_US;
        this.paperExh = Constant.PaperExhausted_US;
        this.paperWillExh = Constant.PaperWillExhausted_US;
        this.abnormal = Constant.Abnormal_US;
    }

    private void getPrintTicketData(UsbDevice paramUsbDevice) {
        getStrDataByLanguage();
        if (checkStatus(getPrinterStatus(paramUsbDevice)) != 0) {
            return;
        }
        try {
            this.mUsbDriver.write(PrintCmd.SetClean(), paramUsbDevice);
            this.mUsbDriver.write(PrintCmd.SetBold(0), paramUsbDevice);
            this.mUsbDriver.write(PrintCmd.SetAlignment(1), paramUsbDevice);
            this.mUsbDriver.write(PrintCmd.SetSizetext(1, 1), paramUsbDevice);
            this.mUsbDriver.write(PrintCmd.SetAlignment(0), paramUsbDevice);
            this.mUsbDriver.write(PrintCmd.SetSizetext(0, 0), paramUsbDevice);
            this.mUsbDriver.write(PrintCmd.SetSizetext(1, 1), paramUsbDevice);
            if (this.response1301 != null) {
                this.mUsbDriver.write(PrintCmd.PrintString("姓名：" + this.response1301.getName(), 0), paramUsbDevice);
                this.mUsbDriver.write(PrintCmd.PrintFeedline(2), paramUsbDevice);
                this.mUsbDriver.write(PrintCmd.PrintFeedline(2), paramUsbDevice);
                this.mUsbDriver.write(PrintCmd.PrintFeedline(1), paramUsbDevice);
                this.mUsbDriver.write(PrintCmd.SetSizetext(1, 1), paramUsbDevice);
                String str = this.response1301.getTip().replace("^", "\n\n");
                this.mUsbDriver.write(PrintCmd.PrintString(str, 0), paramUsbDevice);
            }
            this.mUsbDriver.write(PrintCmd.PrintFeedline(2), paramUsbDevice);
            this.mUsbDriver.write(PrintCmd.PrintFeedline(2), paramUsbDevice);
            this.mUsbDriver.write(PrintCmd.SetSizetext(0, 0), paramUsbDevice);
            this.mUsbDriver.write(PrintCmd.SetAlignment(2), paramUsbDevice);
            this.mUsbDriver.write(PrintCmd.PrintString(this.sdf.format(new Date()).toString() + "\n\n", 1), paramUsbDevice);
            this.mUsbDriver.write(PrintCmd.PrintString(this.response1301.getPatientID(), 0), paramUsbDevice);
            this.mUsbDriver.write(PrintCmd.SetAlignment(0), paramUsbDevice);
            SetFeedCutClean(this.cutter, paramUsbDevice);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("System.out.println", "e.getMessage():" + e.getMessage());
        }
    }

    private int getPrinterStatus(UsbDevice paramUsbDevice) {
        int i = -1;
        byte[] arrayOfByte1 = new byte[1];
        byte[] arrayOfByte2 = PrintCmd.GetStatus1();
        if (this.mUsbDriver.read(arrayOfByte1, arrayOfByte2, paramUsbDevice) > 0) {
            i = PrintCmd.CheckStatus1(arrayOfByte1[0]);
        }
        if (i != 0) {
            return i;
        }
        arrayOfByte1 = new byte[1];
        arrayOfByte2 = PrintCmd.GetStatus2();
        if (this.mUsbDriver.read(arrayOfByte1, arrayOfByte2, paramUsbDevice) > 0) {
            i = PrintCmd.CheckStatus2(arrayOfByte1[0]);
        }
        if (i != 0) {
            return i;
        }
        arrayOfByte1 = new byte[1];
        arrayOfByte2 = PrintCmd.GetStatus3();
        if (this.mUsbDriver.read(arrayOfByte1, arrayOfByte2, paramUsbDevice) > 0) {
            i = PrintCmd.CheckStatus3(arrayOfByte1[0]);
        }
        if (i != 0) {
            return i;
        }
        arrayOfByte1 = new byte[1];
        arrayOfByte2 = PrintCmd.GetStatus4();
        if (this.mUsbDriver.read(arrayOfByte1, arrayOfByte2, paramUsbDevice) > 0) {
            i = PrintCmd.CheckStatus4(arrayOfByte1[0]);
        }
        return i;
    }

    // 根据系统语言获取测试文本
    private void getStrDataByLanguage() {
        codeStr = Constant.WebAddress;
        if ("".equalsIgnoreCase(codeStr))
            codeStr = Constant.WebAddress;
        if (Utils.isZh(this)) {
            title = Constant.TITLE_CN;
            strData = Constant.STRDATA_CN;
        } else {
            title = Constant.TITLE_US;
            strData = Constant.STRDATA_US;
        }
        num = String.valueOf(Number) + "\n\n";
        Number++;
    }

    private void getUsbDriverService() {
        mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
        mUsbDriver = new UsbDriver(mUsbManager, this);
        PendingIntent permissionIntent1 = PendingIntent.getBroadcast(this, 0,
                new Intent(ACTION_USB_PERMISSION), 0);
        mUsbDriver.setPermissionIntent(permissionIntent1);

        // Broadcast listen for new devices
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_USB_PERMISSION);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        this.registerReceiver(mUsbReceiver, filter);
    }

    private void init() {
        findView();
        initPopupWindow();
        View localView = getWindow().getDecorView();
        localView.setSystemUiVisibility(6);
        localView.setSystemUiVisibility(3846);
        getUsbDriverService();
        setListener();
        getMsgByLanguage();
        this.fangdouhandler.postDelayed(this.fangdourunnable, 0L);
    }

    private void initPopupWindow() {
        this.view = getLayoutInflater().inflate(R.layout.dialog_message, null);
        this.popuWindow = new PopupWindow(this.view, -2, -2);
        this.popuWindow.setOutsideTouchable(true);
        this.popuWindow.setFocusable(false);
        this.t = ((TextView) this.view.findViewById(R.id.tv_msg));
        this.dialoghandler.postDelayed(this.dialogrunnable, 0L);
    }

    private void savepull(List<Request1301> paramList)
            throws Exception {
    }

    private void setListener() {
    }

    @SuppressLint({"InlinedApi"})
    public static void setSystemBarVisible(Context paramContext, boolean paramBoolean) {
        Log.i("setSystemBarVisible", "1");
    }

    private void showCustomizeDialog() {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        final View localView = LayoutInflater.from(this).inflate(R.layout.text_dialog, null);
        EditText localEditText1 = (EditText) localView.findViewById(R.id.edit_text);
        EditText localEditText2 = (EditText) localView.findViewById(R.id.edit_name);
        SharedPreferences localSharedPreferences = getSharedPreferences("TR", 0);
        String str = localSharedPreferences.getString("name", "");
        localEditText1.setText(localSharedPreferences.getString("text", ""));
        localEditText2.setText(str);
        ((Button) localView.findViewById(R.id.refresh)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                MainActivity.zantingfuwu = false;
//                paramAnonymousView = (EditText)localView.findViewById(2131492992);
//                EditText localEditText = (EditText)localView.findViewById(2131492993);
//                SharedPreferences localSharedPreferences = MainActivity.this.getSharedPreferences("TR", Context.MODE_PRIVATE);

//                String str = localSharedPreferences.getString("name", "");
//                paramAnonymousView.setText(localSharedPreferences.getString("text", ""));
//                localEditText.setText(str);
            }
        });
        localBuilder.setTitle("设置");
        localBuilder.setView(localView);
        localBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                EditText textedit = (EditText) localView.findViewById(R.id.edit_text);
                EditText localEditText = (EditText) localView.findViewById(R.id.edit_name);
                if (localEditText.getText().toString() == null) {
                    Toast.makeText(MainActivity.this, "计算机名称不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                SharedPreferences sp = MainActivity.this.getSharedPreferences("TR", Context.MODE_PRIVATE);
                sp.edit().putString("name", localEditText.getText().toString()).putString("text", textedit.getText().toString()).putInt("hm", 1).commit();
                MainActivity.this.name = localEditText.getText().toString();
                MainActivity.this.text = textedit.getText().toString();
                Toast.makeText(MainActivity.this, "保存成功！", Toast.LENGTH_SHORT).show();
                MainActivity.this.tv_text.setText(textedit.getText().toString());
            }
        });
        localBuilder.show();
    }

    public void getGetPatLnfo(final String paramString) {
        new Thread(new Runnable() {
            public void run() {
                SoapObject soapObject = new SoapObject(MainActivity.nameSpace, MainActivity.methodName);
                soapObject.addProperty("Input", paramString);
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);
                envelope.bodyOut = soapObject;
                envelope.dotNet = true;
                envelope.setOutputSoapObject(soapObject);
                HttpTransportSE transport = new HttpTransportSE(MainActivity.endPoint);
                try {
                    transport.call(MainActivity.soapAction, envelope);
                    if ((envelope.bodyIn instanceof SoapFault)) {
                        Log.i("envelope.SoapFault", ((SoapFault) (envelope).bodyIn).faultstring);
                        Message message = Message.obtain();
                        message.what = 3030;
                        handler.sendMessage(message);
                        return;
                    } else {
                        SoapObject localObject3 = (SoapObject) envelope.bodyIn;
                        if (localObject3 != null) {
                            Log.i("SOAPtest", "envelope.GetPatLnfo" + envelope.bodyIn);
                            SoapObject localSoapObject = (SoapObject)localObject3.getProperty(0);
                            Log.i("SOAPtest", "object2.getPropertyCount():" + localSoapObject.getPropertyCount());
                            envelope = null;
                            if (localSoapObject.getPropertyCount() == 13) {
                                response3030 = new Response3030();
                                response3030.setResultCode(localSoapObject.getProperty(0).toString());
                                response3030.setPatientID(localSoapObject.getProperty(1).toString());
                                response3030.setPatientName(localSoapObject.getProperty(2).toString());
                                response3030.setSex(localSoapObject.getProperty(3).toString());
                                response3030.setSexCode(localSoapObject.getProperty(4).toString());
                                response3030.setDOB(localSoapObject.getProperty(5).toString());
                                response3030.setIDTypeCode(localSoapObject.getProperty(6).toString());
                                response3030.setIDNo(localSoapObject.getProperty(7).toString());
                                response3030.setPatType(localSoapObject.getProperty(8).toString());
                                response3030.setMobilePhone(localSoapObject.getProperty(9).toString());
                                response3030.setCardNo(localSoapObject.getProperty(10).toString());
                                response3030.setCardTypeCode(localSoapObject.getProperty(11).toString());
                                response3030.setPAPMIID(localSoapObject.getProperty(12).toString());
                                Log.i("finalre3030", response3030.toString());
                            } else if (localSoapObject.getPropertyCount() == 15) {
                                response3030 = new Response3030();
                                response3030.setResultCode(localSoapObject.getProperty(0).toString());
                                response3030.setPatientID(localSoapObject.getProperty(1).toString());
                                response3030.setPatientName(localSoapObject.getProperty(2).toString());
                                response3030.setSex(localSoapObject.getProperty(3).toString());
                                response3030.setSexCode(localSoapObject.getProperty(4).toString());
                                response3030.setDOB(localSoapObject.getProperty(5).toString());
                                response3030.setDocumentID(localSoapObject.getProperty(6).toString());
                                response3030.setAddress(localSoapObject.getProperty(7).toString());
                                response3030.setIDTypeCode(localSoapObject.getProperty(8).toString());
                                response3030.setIDNo(localSoapObject.getProperty(9).toString());
                                response3030.setPatType(localSoapObject.getProperty(10).toString());
                                response3030.setMobilePhone(localSoapObject.getProperty(11).toString());
                                response3030.setCardNo(localSoapObject.getProperty(12).toString());
                                response3030.setCardTypeCode(localSoapObject.getProperty(13).toString());
                                response3030.setPAPMIID(localSoapObject.getProperty(14).toString());
                                Log.i("finalre3030", response3030.toString());
                            }

                            Message msg = MainActivity.this.handler.obtainMessage();
                            msg.obj = response3030;
                            msg.what = 1;
                            MainActivity.this.handler.sendMessage(msg);


                        } else {
                            Log.i("SOAPtest", "object==null");
                            Message msg = handler.obtainMessage();
                            msg.what = 0;
                            msg.obj = " 无此病人信息";
                            handler.sendMessage(msg);
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();

                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getOPAllocAutoReport(final String paramString) {
        Log.i("SOAPtest", "getOPAllocAutoReport");
        new Thread(new Runnable() {
            public void run() {
                SoapObject soapObject = new SoapObject(nameSpace2, methodName2);
                soapObject.addProperty("XMLRequest", paramString);
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(100);
                envelope.bodyOut = soapObject;
                envelope.dotNet = true;
                envelope.setOutputSoapObject(soapObject);
                HttpTransportSE transport = new HttpTransportSE(MainActivity.endPoint2);
                try {
                    transport.call(MainActivity.soapAction2, envelope);
                    if ((envelope.bodyIn instanceof SoapFault)) {
                        String errorstring = ((SoapFault) envelope.bodyIn).faultstring;
                        Log.i("envelope.faultstring", errorstring + ":::" + envelope.bodyIn);
                        Message msg = Message.obtain();
                        msg.what = 1301;
                        MainActivity.this.handler.sendMessage(msg);
                        return;
                    } else {
                        SoapObject localSoapObject = (SoapObject) envelope.bodyIn;
                        Object localObject3 = (SoapObject) localSoapObject.getProperty(0);
//                        envelope = null;
                        Log.i("SOAPtest", "object2.getPropertyCount()：" + ((SoapObject) localObject3).getPropertyCount());
                        Log.i("SOAPtest", "object2.getPropertyCount()2：" + localSoapObject.getProperty(0).toString());
                        if (((SoapObject) localObject3).getPropertyCount() == 5) {
                            response1301 = new Response1301();
                            response1301.setResultCode(((SoapObject) localObject3).getProperty(0).toString());
                            response1301.setTip(((SoapObject) localObject3).getProperty(1).toString());
                            response1301.setPatientID(((SoapObject) localObject3).getProperty(2).toString());
                            response1301.setName(((SoapObject) localObject3).getProperty(3).toString());
                            response1301.setSex(((SoapObject) localObject3).getProperty(4).toString());
                        } else if (((SoapObject) localObject3).getPropertyCount() == 2) {
                            Message msg = MainActivity.this.handler.obtainMessage();
                            msg.obj = ((SoapObject) localObject3).getProperty(1).toString();
                            msg.what = 6;
                            MainActivity.this.handler.sendMessage(msg);
                            return;
                        }
                        Message msg = handler.obtainMessage();
                        msg.obj = response1301;
                        msg.what = 5;
                        MainActivity.this.handler.sendMessage(msg);

                    }
                } catch (IOException e) {
                    e.printStackTrace();

                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.mar);
        if (this.test) {
            getSharedPreferences("TR", 0).edit().putString("name", "XQ001").putString("text", "请先检查视力后报到").commit();
        }

            main = getLayoutInflater().inflate(R.layout.mar, null);
            main.setSystemUiVisibility(2);
        init();
        request1301List = new ArrayList();
        request3030List = new ArrayList();

        getSharedPreferences("TR", 0).edit().putString("text", "请先检查视力后报到").commit();

    }

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mUsbReceiver);
    }


    public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
        Log.i("test", "onkeydown" + paramInt + ":" + paramKeyEvent);
        if (this.fangdoutime < 3) {
            Log.i("test", "fangdoutime" + this.fangdoutime);
        } else {
            Log.i("test", "fangdoutime" + this.fangdoutime);
            String keystring = KeyEvent.keyCodeToString(paramKeyEvent.getKeyCode());
            Log.i("test", "getkeycode:" + keystring);
            if (TextUtils.equals(keystring, "KEYCODE_ENTER")) {
                if (!zantingfuwu) {
                    Log.i("jialei", "zantingfuwu=true");
                    this.t.setText("打印机缺纸，请联系相关人员换纸后重启！");
                    if (this.popuWindow.isShowing()) {
                        this.popuWindow.dismiss();
                    }
                    this.dialogtime = 5;
                    this.popuWindow.showAtLocation(this.main, Gravity.CENTER, 0, 0);

                } else {
                    fangdoutime = 0;
                    if (!TextUtils.equals(this.twocode2, "")) {
                        Log.i("扫码2", this.twocode2);
                        this.request3030List.clear();
                        Request3030 request3030 = new Request3030();
                        request3030.setTradeCode("3030");
                        request3030.setPatientCard(this.twocode2);
                        this.request3030List.add(request3030);
                        try {

                            String save3300string = pullpuser.save3300(this.request3030List);
                            this.request3030List.clear();
                            save3300string = save3300string.replace("<?xml version='1.0' encoding='UTF-8' standalone='yes' ?>", "");
                            Log.i("扫码2", "pull2" + save3300string);
                            if (save3300string != null && !TextUtils.equals(save3300string, "")) {
                                getGetPatLnfo(save3300string);
                            } else {
                                Log.i("saomiao error:", "save3300string==null|save3300string=kong");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else {
                        this.t.setText("卡片无效,请重新刷卡!");
                        if (this.popuWindow.isShowing()) {
                            this.popuWindow.dismiss();
                        }
                        this.dialogtime = 5;
                        this.popuWindow.showAtLocation(this.main, 17, 0, 0);
                    }
                }

            } else if (!TextUtils.equals(keystring, "KEYCODE_NUM_LOCK")) {
                String str = keystring.substring(keystring.length() - 1, keystring.length());
                this.twocode2 += str;
            }
        }
        return super.onKeyDown(paramInt, paramKeyEvent);

    }


    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                break;
            case MotionEvent.ACTION_UP:
                if ((int) event.getX() - this.downX > 500) {
                    this.houmen = getSharedPreferences("TR", Context.MODE_PRIVATE).getInt("hm", 0);
                    if (this.houmen == 0) {
                        showCustomizeDialog();
                    }
                }
                downX = (int) event.getX();
                break;
        }
        return false;
    }



    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
        }
    }
}

