package com.fernandosierra.obj2es2;

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

    protected Float[] mPositions;
    protected Float[] mTexels;
    protected Float[] mNormals;
    protected Integer[] mFaces;

    /**
     * Constructor
     *
     * @param positions
     *     Vector with all the positions to be wrapped.
     * @param texels
     *     Vector with all the texels to be wrapped.
     * @param normals
     *     Vector with all the normals to be wrapped.
     * @param faces
     *     Vector with all the faces to be wrapped.
     */
    public GlEs2Model(Vector<Float> positions, Vector<Float> texels, Vector<Float> normals, Vector<Integer> faces) {
        this.mPositions = new Float[positions.size()];
        this.mTexels = new Float[texels.size()];
        this.mNormals = new Float[normals.size()];
        this.mFaces = new Integer[faces.size()];
        // Dump the contents into the object.
        positions.copyInto(mPositions);
        texels.copyInto(mTexels);
        normals.copyInto(mNormals);
        faces.copyInto(mFaces);
    }

    public Float[] getPositions() {
        return mPositions;
    }

    public Float[] getTexels() {
        return mTexels;
    }

    public Float[] getNormals() {
        return mNormals;
    }

    public Integer[] getFaces() {
        return mFaces;
    }
}
