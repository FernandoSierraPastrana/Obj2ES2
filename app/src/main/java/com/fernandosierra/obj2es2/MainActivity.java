package com.fernandosierra.obj2es2;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String TAG_LOG = "Obj2ES2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssetManager assetManager = getAssets();
        Converter converter = new Converter();
        try {
            long start = System.currentTimeMillis();
            GlEs2Model model = converter.parseObjFile(assetManager.open("example.obj"));
            Log.i(TAG_LOG, "Execution time=" + (System.currentTimeMillis() - start) + "ms");
            Log.i(TAG_LOG,
                "Vertex[0] = (" + model.getVertexes().getFloat() + ", " + model.getVertexes().getFloat() + ", " + model.getVertexes()
                    .getFloat() + ")");
            Log.i(TAG_LOG,
                "Normal[0] = (" + model.getNormals().getFloat() + ", " + model.getNormals().getFloat() + ", " + model.getNormals()
                    .getFloat() + ")");
            Log.i(TAG_LOG, "Texel[0] = (" + model.getTexels().getFloat() + ", " + model.getTexels().getFloat() + ")");
            Log.i(TAG_LOG,
                "Face[0] = (" + model.getFaces().getInt() + ", " + model.getFaces().getInt() + ", " + model.getFaces().getInt() + ")");

        }
        catch (IOException e) {
            Log.e(TAG_LOG, e.getMessage(), e);
        }
    }
}
