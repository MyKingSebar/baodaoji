//package com.test.ys.saomiaotest;
//
//import android.app.Activity;
//import android.app.AlertDialog;
//import android.app.AlertDialog.Builder;
//import android.content.DialogInterface;
//import android.content.DialogInterface.OnClickListener;
//import android.content.SharedPreferences;
//import android.content.SharedPreferences.Editor;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class NewMain
//  extends Activity
//{
//  int downX = 0;
//  int downY = 0;
//  String name = "";
//  String text = "";
//  TextView tv_text;
//
//  private void showCustomizeDialog()
//  {
//    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
//    final View localView = LayoutInflater.from(this).inflate(2130968629, null);
//    localBuilder.setTitle("设置");
//    localBuilder.setView(localView);
//    localBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener()
//    {
//      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
//      {
//        paramAnonymousDialogInterface = (EditText)localView.findViewById(2131492992);
//        EditText localEditText = (EditText)localView.findViewById(2131492993);
//        if (localEditText.getText().toString() == null) {}
//        for (paramAnonymousInt = 1; (paramAnonymousInt | TextUtils.equals("", localEditText.getText().toString())) != 0; paramAnonymousInt = 0)
//        {
//          Toast.makeText(NewMain.this, "计算机名称不能为空！", 1).show();
//          return;
//        }
//        NewMain.this.getSharedPreferences("TR", 0).edit().putString("name", localEditText.getText().toString()).putString("text", paramAnonymousDialogInterface.getText().toString()).commit();
//        Toast.makeText(NewMain.this, "保存成功！", 0).show();
//        NewMain.this.tv_text.setText(paramAnonymousDialogInterface.getText().toString());
//      }
//    });
//    localBuilder.show();
//  }
//
//  protected void onCreate(@Nullable Bundle paramBundle)
//  {
//    super.onCreate(paramBundle);
//    setContentView(2130968607);
//    this.tv_text = ((TextView)findViewById(2131492965));
//    paramBundle = getSharedPreferences("TR", 0);
//    this.name = paramBundle.getString("name", "");
//    this.text = paramBundle.getString("text", "");
//    this.tv_text.setText(this.text);
//  }
//
//  protected void onResume()
//  {
//    super.onResume();
//  }
//
//  public boolean onTouchEvent(MotionEvent paramMotionEvent)
//  {
//    switch (paramMotionEvent.getAction())
//    {
//    }
//    for (;;)
//    {
//      return super.onTouchEvent(paramMotionEvent);
//      this.downX = ((int)paramMotionEvent.getX());
//      continue;
//      if ((int)paramMotionEvent.getX() - this.downX > 500) {
//        showCustomizeDialog();
//      }
//    }
//  }
//}
//
