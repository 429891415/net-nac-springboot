package com.phvob.util;

import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.util.FileManager;

/**
 * @author phvob
 * @email hbuphvob@163.com
 * @create 2021-04-16 16:32
 */

public class OwlToTdb{
    private static String source="NetNAC.owl";
    private static String DIRECTORY="src/main/resources/NACTDB";
    public static void transform(){
        Dataset dataset = TDBFactory.createDataset(DIRECTORY);
        Model model = dataset.getDefaultModel();
        FileManager.get().readModel(model,source);
        model.commit();
        model.close();
        dataset.close();
        System.out.println("ok\n");
    }
}
