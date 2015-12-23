# Obj2ES2 v0.1.3
Obj2ES2 is a library to provides the capability of import directly the obj/wavefront files exported by 3d Modeling Tools like Blender or 3DMaxStudio into your Android Application with OpenGL ES 2.0 or above.

For further information about the obj/wavefront format please read https://en.wikipedia.org/wiki/Wavefront_.obj_file.

To import Obj2ES2 in your project, you only need to add the dependency into your <b>*build.gradle*</b>:

```gradle
dependencies {
  ...
  compile 'com.fernandosierra.obj2es2:obj2es2:0.1.3'
}
```

Now you need to get your model's obj file from your 3D Modeling Tool, <b>*remember exported with normals, uv texture coordinates, and triangular faces*</b>.

Therefore to import our obj file we need to do (This code works if you put the obj under the assets folder, you can put it where you desire, the *parseObjFile* method of Converter needs a InputStream as input):

```java
import com.fernandosierra.obj2es2.Converter;
import com.fernandosierra.obj2es2.GlEs2Model;

...

Converter converter = new Converter();
try {
  Converter converter = new Converter();
  AssetManager assetManager = getAssets();
  GlEs2Model model = converter.parseObjFile(assetManager.open(filename));
} catch (IOException e) {
  Log.e("Model", e.getMessage(), e);
}
```

The GlEs2Model contain whole the information from the obj file, and it's ready to interact directly with OpenGL ES 2.0.

```java
...
GLES20.glUseProgram(shaderProgramID);
// Uses the GlEs2Model directly with OpenGL ES 2.0 framework
GLES20.glVertexAttribPointer(vertexHandle, 3, GLES20.GL_FLOAT, false, 0, model.getVertexes());
GLES20.glVertexAttribPointer(normalHandle, 3, GLES20.GL_FLOAT, false, 0, model.getNormals());
GLES20.glVertexAttribPointer(texelHandle, 2, GLES20.GL_FLOAT, false, 0, model.getTexels());
// Enable attributes
GLES20.glEnableVertexAttribArray(vertexHandle);
GLES20.glEnableVertexAttribArray(normalHandle);
GLES20.glEnableVertexAttribArray(texelHandle);
// Activate texture 0, bind it, and pass to shader
GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture.mTextureID[0]);
GLES20.glUniform1i(texSampler2DHandle, 0);
// Pass the model view matrix to the shader
GLES20.glUniformMatrix4fv(mvpMatrixHandle, 1, false, modelViewProjection, 0);
// Draw the model using the indexes contained in the GlEs2Model
GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, model.getIndexes());
// Disable the enabled arrays
GLES20.glDisableVertexAttribArray(vertexHandle);
GLES20.glDisableVertexAttribArray(normalHandle);
GLES20.glDisableVertexAttribArray(texelHandle);
...
```
