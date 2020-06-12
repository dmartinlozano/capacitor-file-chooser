package org.dmartinlozano.capacitorFileChooser;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.nononsenseapps.filepicker.FilePickerActivity;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import android.content.Intent;
import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;

import org.json.JSONArray;

@NativePlugin(
        permissions={
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        },
        requestCodes={CapacitorFileChooser.REQUEST}
)
public class CapacitorFileChooser extends Plugin {

    protected static final int REQUEST = 1;

    @PluginMethod()
    public void picker(PluginCall call) {

        String mode = call.getString("mode", "showPicker");
        String initPath = call.getString("initPath", null);

        Intent i = new Intent(getContext(), FilePickerActivity.class);
        i.putExtra(FilePickerActivity.EXTRA_ALLOW_CREATE_DIR, true);
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        if(initPath.equals(null)){
            i.putExtra(FilePickerActivity.EXTRA_START_PATH, Environment.getExternalStorageDirectory().getPath());
        }else{
            i.putExtra(FilePickerActivity.EXTRA_START_PATH, initPath);
        }

        if ("showPicker".equals(mode)) {
            i.putExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false);
            i.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_FILE);
        }else if("showMultiFilepicker".equals(mode)){
            i.putExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, true);
            i.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_FILE);
        }else if("showFolderpicker".equals(mode)){
            i.putExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false);
            i.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_DIR);
        }else if("showMultiFolderpicker".equals(mode)){
            i.putExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, true);
            i.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_DIR);
        }else if("showMixedPicker".equals(mode)){
            i.putExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, true);
            i.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_FILE_AND_DIR);
        }else if("showCreatefile".equals(mode)){
            i.putExtra(FilePickerActivity.EXTRA_MODE, FilePickerActivity.MODE_NEW_FILE);
        }
        startActivityForResult(call, i, REQUEST);
    }

    @Override
    protected void handleOnActivityResult(int requestCode, int resultCode, Intent data) {
        super.handleOnActivityResult(requestCode, resultCode, data);
        JSONArray jsonArray = new JSONArray();
        if (requestCode == REQUEST && resultCode == Activity.RESULT_OK) {
            if (data.getBooleanExtra(FilePickerActivity.EXTRA_ALLOW_MULTIPLE, false)) {
                    ClipData clip = data.getClipData();
                    if (clip != null) {
                        for (int i = 0; i < clip.getItemCount(); i++) {
                            jsonArray.put(clip.getItemAt(i).getUri().toString());
                        }
                    }
            } else {
                jsonArray.put(data.getData().toString());
            }
        }
        PluginCall savedCall = getSavedCall();
        if (savedCall == null) {
            return;
        }
        if (requestCode == REQUEST) {
            JSObject ret = new JSObject();
            ret.put("paths", jsonArray.toString());
            savedCall.resolve(ret);
        }
    }
}
