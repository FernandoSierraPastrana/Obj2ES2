package com.fernandosierra.obj2es2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * This class provides the capability of parse an obj file to {@link GlEs2Model} object.
 *
 * @author Jesús Fernando Sierra Pastrana
 * @version 1.0
 * @since 21/12/15.
 */
public class Converter {

    private static final String VERTEX_TYPE = "v ";
    private static final String SPACE = " ";
    private static final String TEXEL_TYPE = "vt ";
    private static final String NORMAL_TYPE = "vn ";
    private static final String FACE_TYPE = "f ";
    private static final String FACE_SPLITTER = "/";
    private final Vector<Float[]> positions;
    private final Vector<Float[]> texels;
    private final Vector<Float[]> normals;
    private final Vector<Integer[]> faces;

    /**
     * Constructor
     */
    public Converter() {
        positions = new Vector<>();
        texels = new Vector<>();
        normals = new Vector<>();
        faces = new Vector<>();
    }

    /**
     * Takes a {@link InputStreamReader} and retrieves a {@link GlEs2Model} object with all the information contained in the obj file.
     *
     * @param objFile Stream to the obj file to be parsed.
     * @return A new instance of {@link GlEs2Model} filled with the information from the obj. Can be null.
     * @throws IOException It will trigger if some error occurs during the parsing.
     */
    public GlEs2Model parseObjFile(InputStream objFile) throws IOException {
        if (objFile == null) {
            return null;
        } else {
            BufferedReader reader = new BufferedReader(new InputStreamReader(objFile));
            // Linear read of the file.
            String line;
            while ((line = reader.readLine()) != null) {
                readLine(line);
            }
            return new GlEs2Model(positions, texels, normals, faces);
        }
    }

    /**
     * Reads the line and determinate what will do with that line.
     *
     * @param line Line to be processed.
     */
    private void readLine(String line) {
        if (line.startsWith(VERTEX_TYPE)) {
            extractVertex(line);
        } else if (line.startsWith(TEXEL_TYPE)) {
            extractTexel(line);
        } else if (line.startsWith(NORMAL_TYPE)) {
            extractNormal(line);
        } else if (line.startsWith(FACE_TYPE)) {
            extractFace(line);
        }
    }

    /**
     * Takes the group of indexes and extract the values.
     *
     * @param line Line with the indexes(v/vt/vn).
     */
    private void extractFace(String line) {
        StringTokenizer tokenizer = new StringTokenizer(line.substring(FACE_TYPE.length()), SPACE);
        Integer[] array = new Integer[9];
        for (int i = 0; i < 3; i++) {
            String group = tokenizer.nextToken();
            String[] values = group.split(FACE_SPLITTER);
            for (int j = 0; j < values.length; j++) {
                array[(i * 3) + j] = Integer.parseInt(values[j]);
            }
        }
        faces.add(array);
    }

    /**
     * Takes the line and extract the normal values.
     *
     * @param line Line with the normal values (X Y Z).
     */
    private void extractNormal(String line) {
        StringTokenizer tokenizer = new StringTokenizer(line.substring(NORMAL_TYPE.length()), SPACE);
        Float[] array = {
                Float.parseFloat(tokenizer.nextToken()),
                Float.parseFloat(tokenizer.nextToken()),
                Float.parseFloat(tokenizer.nextToken()),
        };
        normals.add(array);
    }

    /**
     * Takes the line and extract the texel values.
     *
     * @param line Line with the texel values (U V).
     */
    private void extractTexel(String line) {
        StringTokenizer tokenizer = new StringTokenizer(line.substring(TEXEL_TYPE.length()), SPACE);
        Float[] array = {
                Float.parseFloat(tokenizer.nextToken()),
                Float.parseFloat(tokenizer.nextToken())
        };
        texels.add(array);
    }

    /**
     * Takes the line and extract the vertex values.
     *
     * @param line Line with the vertex values (X Y Z);
     */
    private void extractVertex(String line) {
        StringTokenizer tokenizer = new StringTokenizer(line.substring(VERTEX_TYPE.length()), SPACE);
        Float[] array = {
                Float.parseFloat(tokenizer.nextToken()),
                Float.parseFloat(tokenizer.nextToken()),
                Float.parseFloat(tokenizer.nextToken())
        };
        positions.add(array);
    }
}
