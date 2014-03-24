package tw.android;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class Main extends Activity {

	private static final int MENU_TAKE_PICTURE = Menu.FIRST,
							 MENU_SHOW_PICTURE = Menu.FIRST + 1;

	private Camera mCamera;
	private CameraPreview mCamPreview;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        getWindow().setFormat(PixelFormat.TRANSLUCENT); 
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
//        					 WindowManager.LayoutParams.FLAG_FULLSCREEN); 

        mCamPreview = new CameraPreview(this);
        setContentView(mCamPreview);
    }

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub

		mCamera = Camera.open();
		mCamPreview.set(this, mCamera);

		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub

		mCamera.stopPreview();

	    mCamera.release();
	    mCamera = null;

	    super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub

		menu.add(0, MENU_TAKE_PICTURE, 0, "�Ӭ�");
		menu.add(0, MENU_SHOW_PICTURE, 0, "��ܷӤ�");

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		switch (item.getItemId()) {
		case MENU_TAKE_PICTURE:
			mCamera.takePicture(camShutterCallback, camRawDataCallback, camJpegCallback);
			break;
		case MENU_SHOW_PICTURE:
			Intent it = new Intent(Intent.ACTION_VIEW);
			File file = new File("/sdcard/photo.jpg");
			it.setDataAndType(Uri.fromFile(file), "image/");
			startActivity(it);
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}

	ShutterCallback camShutterCallback = new ShutterCallback() {
		public void onShutter() {
			// �q���ϥΪ̤w�������,�Ҧp�o�X�@���n��
		}
	};

	PictureCallback camRawDataCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			// �Ψӱ�����l���v�����
		}
	};

	PictureCallback camJpegCallback = new PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			// �Ψӱ������Y��jpeg�榡���v�����

			FileOutputStream outStream = null;
			try {
				outStream = new FileOutputStream("/sdcard/photo.jpg");
				outStream.write(data);
				outStream.close();
			} catch (IOException e) {
				Toast.makeText(Main.this, "�v�����x�s���~�I", Toast.LENGTH_SHORT)
					.show();
			}

			mCamera.startPreview();
		}
	};
}