package org.dmartinlozano.capacitorFileChooser;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import android.Manifest;
import android.content.Intent;

import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;

@NativePlugin(
        permissions={
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        },
        requestCodes={CapacitorFileChooser.REQUEST}
)
public class CapacitorFileChooser extends Plugin {

    protected static final int REQUEST = 12345;

    @PluginMethod()
    public void picker(PluginCall call) {

        //StorageChooserView.setScSecondaryActionColor(accentcolor);
        String action = call.getString("action", "showPicker");
        String startDirectory = call.getString("startDirectory", null);

        Intent intent = new Intent("org.dmartin.capacitorFileChooser.DialogShowPicker");
        intent.putExtra("action", action);
        intent.putExtra("startDirectory", startDirectory);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        saveCall(call);
        startActivityForResult(call, intent, REQUEST);
    }

    @Override
    protected void handleOnActivityResult(int requestCode, int resultCode, Intent data) {

        super.handleOnActivityResult(requestCode, resultCode, data);
        PluginCall savedCall = getSavedCall();
        if (savedCall == null) {
            return;
        }
        if (requestCode == REQUEST) {
            JSObject ret = new JSObject();
            ret.put("paths", data.getStringExtra("paths"));
            savedCall.resolve(ret);
        }
    }

}
