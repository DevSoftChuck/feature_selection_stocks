package com.company;

import java.io.File;

import com.google.common.primitives.Ints;
import upo.jcu.math.MathUtils;
import upo.jcu.math.MatrixUtils;
import upo.jcu.math.data.dataset.DataType;
import upo.jcu.math.stat.MultivariateStatUtils;
import upo.jml.data.dataset.ClassificationDataset;
import upo.jml.data.dataset.DatasetUtils;
import com.google.common.collect.Sets;
import java.util.Set;

/**
 *
 * @author mgarciat
 */
public class Main {
    static String path = "C:/Users/ivan/IdeaProjects/Tesis/tsla_45.arff";
    static ClassificationDataset dataset;
    static int[][] tdata;
    static int[] labels;
    static int[] nvalues;
    static int[] lvalues;
    static Set<Integer> set;
    static  Set<Set<Integer>> powerSet;

    public static void main(String[] args) throws Exception {
        dataset = DatasetUtils.loadArffDataset(new File(path), -1);

        if (dataset.getDataType().equals(DataType.NUMERICAL)) {
            dataset = DatasetUtils.dicretizeNumericalDatasetViaFayyad(dataset);
        }

        tdata = MatrixUtils.transpose(dataset.getCategoricalData());
        labels = dataset.getLabels();

        nvalues = new int[tdata.length];
        for (int f = 0; f < tdata.length; f++) {
            nvalues[f] = tdata[f][MathUtils.maxValueIndex(tdata[f])] + 1;
        }

        lvalues = dataset.getLabelIndexes();

        set = Sets.newHashSet(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                                        21, 22, 23);
        powerSet = Sets.powerSet(set);
        double vTemporal = 0.0;
        for (Set<Integer> s: powerSet) {
            int[] targetArray = Ints.toArray(s);
            if(targetArray.length >= 1){
                double value = MultivariateStatUtils.symmetricalUncertainty(tdata, nvalues, targetArray, labels, lvalues.length);
                if(vTemporal < value){
                    System.out.println(s + ": " + value);
                    vTemporal = value;
                }
            }
        }
    }
}
