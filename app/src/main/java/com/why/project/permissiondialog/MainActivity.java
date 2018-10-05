package com.why.project.permissiondialog;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.why.project.permissiondialog.dialog.PermissionDialog;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.btn_showdialog).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				boolean hasShow = PreferencesUtils.getBoolean(MainActivity.this,PreferencesUtils.HAS_SHOW_PREMISSION_DIALOG,false);
				if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ! hasShow){
					PermissionDialog permissionDialog = new PermissionDialog(MainActivity.this);
					permissionDialog.setOnCertainButtonClickListener(new PermissionDialog.OnCertainButtonClickListener() {
						@Override
						public void onCertainButtonClick() {
							//搭配SharedPreferences 将状态值记录下来，实现APP首次打开的时候才会弹出这个对话框
							PreferencesUtils.putBoolean(MainActivity.this,PreferencesUtils.HAS_SHOW_PREMISSION_DIALOG,true);
							//调用运行时权限申请框架
						}
					});
					permissionDialog.show();
				}else{
					//调用运行时权限申请框架
				}
			}
		});
	}
}
