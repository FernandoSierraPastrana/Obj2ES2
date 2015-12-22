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

    /**
     * Constructor
     *
     * @param vertexes Vector with all the vertexes to be wrapped.
     * @param texels   Vector with all the texels to be wrapped.
     * @param normals  Vector with all the normals to be wrapped.
     * @param faces    Vector with all the faces to be wrapped.
     */
    public GlEs2Model(Vector<Float[]> vertexes, Vector<Float[]> texels, Vector<Float[]> normals, Vector<Integer[]> faces) {
        int size = faces.size();
        float[] vertexesArray = new float[size * 9];
        float[] texelsArray = new float[size * 6];
        float[] normalsArray = new float[size * 9];
        for (int index = 0; index < size; index++) {
            Integer[] face = faces.get(index);
            extractVertexes(vertexes, vertexesArray, index, face);
            extractTexels(texels, texelsArray, index, face);
            extractNormals(normals, normalsArray, index, face);
        }
        this.mVertexes = dumpFloatVectorIntoBuffer(vertexesArray);
        this.mTexels = dumpFloatVectorIntoBuffer(texelsArray);
        this.mNormals = dumpFloatVectorIntoBuffer(normalsArray);
    }

    /**
     * Extract whole the vertexes using the face indexes.
     *
     * @param vertexes      Vector with all the vertexes.
     * @param vertexesArray Array where will be dumped the information.
     * @param index         Current face index.
     * @param face          Array with all the indexes.
     */
    private void extractVertexes(Vector<Float[]> vertexes, float[] vertexesArray, int index, Integer[] face) {
        // Indexes to extract Vertexes values.
        int firstVertex = face[0] - 1;
        int secondVertex = face[3] - 1;
        int thirdVertex = face[6] - 1;
        // Extraction of first Vertex
        Float[] vertex = vertexes.get(firstVertex);
        vertexesArray[(index * 9)] = vertex[0];
        vertexesArray[(index * 9) + 1] = vertex[1];
        vertexesArray[(index * 9) + 2] = vertex[2];
        // Extraction of second Vertex
        vertex = vertexes.get(secondVertex);
        vertexesArray[(index * 9) + 3] = vertex[0];
        vertexesArray[(index * 9) + 4] = vertex[1];
        vertexesArray[(index * 9) + 5] = vertex[2];
        // Extraction of third Vertex
        vertex = vertexes.get(thirdVertex);
        vertexesArray[(index * 9) + 6] = vertex[0];
        vertexesArray[(index * 9) + 7] = vertex[1];
        vertexesArray[(index * 9) + 8] = vertex[2];
    }

    /**
     * Extract whole the texels using the face indexes.
     *
     * @param texels      Vector with all the texels.
     * @param texelsArray Array where will be dumped the information.
     * @param index       Current face index.
     * @param face        Array with all the indexes.
     */
    private void extractTexels(Vector<Float[]> texels, float[] texelsArray, int index, Integer[] face) {
        // Indexes to extract Texels values.
        int firstTexel = face[1] - 1;
        int secondTexel = face[4] - 1;
        int thirdTexel = face[7] - 1;
        // Extraction of first Vertex
        Float[] texel = texels.get(firstTexel);
        texelsArray[(index * 6)] = texel[0];
        texelsArray[(index * 6) + 1] = texel[1];
        // Extraction of second Vertex
        texel = texels.get(secondTexel);
        texelsArray[(index * 6) + 2] = texel[0];
        texelsArray[(index * 6) + 3] = texel[1];
        // Extraction of third Vertex
        texel = texels.get(thirdTexel);
        texelsArray[(index * 6) + 4] = texel[0];
        texelsArray[(index * 6) + 5] = texel[1];
    }

    /**
     * Extract whole the normals using the face indexes.
     *
     * @param normals      Vector with all the normals.
     * @param normalsArray Array where will be dumped the information.
     * @param index        Current face index.
     * @param face         Array with all the indexes.
     */
    private void extractNormals(Vector<Float[]> normals, float[] normalsArray, int index, Integer[] face) {
        // Indexes to extract Normals values.
        int firstNormal = face[2] - 1;
        int secondNormal = face[5] - 1;
        int thirdNormal = face[8] - 1;
        // Extraction of first Vertex
        Float[] normal = normals.get(firstNormal);
        normalsArray[(index * 9)] = normal[0];
        normalsArray[(index * 9) + 1] = normal[1];
        normalsArray[(index * 9) + 2] = normal[2];
        // Extraction of second Vertex
        normal = normals.get(secondNormal);
        normalsArray[(index * 9) + 3] = normal[0];
        normalsArray[(index * 9) + 4] = normal[1];
        normalsArray[(index * 9) + 5] = normal[2];
        // Extraction of third Vertex
        normal = normals.get(thirdNormal);
        normalsArray[(index * 9) + 6] = normal[0];
        normalsArray[(index * 9) + 7] = normal[1];
        normalsArray[(index * 9) + 8] = normal[2];
    }

    /**
     * Takes a float array to insert all the contents into a new {@link ByteBuffer}.
     *
     * @param array Array to be dumped into the {@link ByteBuffer}.
     * @return Buffer with all the array's content.
     */
    private ByteBuffer dumpFloatVectorIntoBuffer(float[] array) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(array.length * 4);
        buffer.order(ByteOrder.nativeOrder());
        for (float value : array) {
            buffer.putFloat(value);
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
}
