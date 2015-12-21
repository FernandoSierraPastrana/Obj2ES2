package com.fernandosierra.obj2es2;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Obj2ES2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssetManager assetManager = getAssets();
        Converter converter = new Converter();
        try {
            long start = System.currentTimeMillis();
            GlEs2Model model = converter.parseObjFile(assetManager.open("example.obj"));
            Log.i(TAG, "Execution time=" + (System.currentTimeMillis() - start) + "ms");
            Log.i(TAG, "Vertex[0] = (" + model.getPositions()[0] + ", " + model.getPositions()[1] + ", " + model.getPositions()[2] + ")");
            Log.i(TAG, "Normal[0] = (" + model.getNormals()[0] + ", " + model.getNormals()[1] + ", " + model.getNormals()[2] + ")");
            Log.i(TAG, "Texel[0] = (" + model.getTexels()[0] + ", " + model.getTexels()[1] + ")");
            Log.i(TAG, "Face[0] = (" + model.getFaces()[0] + "/" + model.getFaces()[1] + "/" + model.getFaces()[2] +
                       ", " + model.getFaces()[3] + "/" + model.getFaces()[4] + "/" + model.getFaces()[5] +
                       ", " + model.getFaces()[6] + "/" + model.getFaces()[7] + "/" + model.getFaces()[8] + ")");

        } catch (IOException e) {
            Log.e(TAG, e.getMessage(), e);
        }
    }
}
