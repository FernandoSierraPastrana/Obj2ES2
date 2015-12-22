package com.fernandosierra.obj2es2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Vector;

/**
 * Class to model the all the elements to be extracted from the obj file.
 * <p/>
 * The class contains the vertex positions(XYZ), texels(UV texture coordinates), normals(XYZ) and the triangular faces (X/Y/Z X/Y/Z X/Y/Z).
 * <p/>
 * This class only contemplate to be used with OpenGL ES 2.0 or above.
 *
 * @author Jesús Fernando Sierra Pastrana
 * @version 1.0
 * @since 21/12/15.
 */
public class GlEs2Model {

    protected ByteBuffer mVertexes;
    protected ByteBuffer mTexels;
    protected ByteBuffer mNormals;
    protected ByteBuffer mFaces;

    /**
     * Constructor
     *
     * @param vertexes
     *     Vector with all the vertexes to be wrapped.
     * @param texels
     *     Vector with all the texels to be wrapped.
     * @param normals
     *     Vector with all the normals to be wrapped.
     * @param faces
     *     Vector with all the faces to be wrapped.
     */
    public GlEs2Model(Vector<Float> vertexes, Vector<Float> texels, Vector<Float> normals, Vector<Integer> faces) {
        this.mVertexes = dumpFloatVectorIntoBuffer(vertexes);
        this.mTexels = dumpFloatVectorIntoBuffer(texels);
        this.mNormals = dumpFloatVectorIntoBuffer(normals);
        this.mFaces = dumpIntegerVectorIntoBuffer(faces);
    }

    /**
     * Takes a {@link Vector}<{@link Float}> to insert all the contents into a new {@link ByteBuffer}.
     *
     * @param vector
     *     Vector to be dumped into the {@link ByteBuffer}.
     * @return Buffer with all the vector's content.
     */
    private ByteBuffer dumpFloatVectorIntoBuffer(Vector<Float> vector) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(vector.size() * 4);
        buffer.order(ByteOrder.nativeOrder());
        for (float value : vector) {
            buffer.putFloat(value);
        }
        buffer.rewind();
        return buffer;
    }

    /**
     * Takes a {@link Vector}<{@link Integer}> to insert all the contents into a new {@link ByteBuffer}.
     *
     * @param vector
     *     Vector to be dumped into the {@link ByteBuffer}.
     * @return Buffer with all the vector's content.
     */
    private ByteBuffer dumpIntegerVectorIntoBuffer(Vector<Integer> vector) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(vector.size() * 4);
        buffer.order(ByteOrder.nativeOrder());
        for (int value : vector) {
            buffer.putInt(value);
        }
        buffer.rewind();
        return buffer;
    }

    public ByteBuffer getVertexes() {
        return mVertexes;
    }

    public ByteBuffer getTexels() {
        return mTexels;
    }

    public ByteBuffer getNormals() {
        return mNormals;
    }

    public ByteBuffer getFaces() {
        return mFaces;
    }
}
