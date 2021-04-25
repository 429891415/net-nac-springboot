package com.phvob.util;

import org.apache.jena.query.Dataset;
import org.apache.jena.tdb.TDBFactory;

/**
 * @author phvob
 * @email hbuphvob@163.com
 * @create 2021-04-22 18:16
 */

public class TDBConnection {
    private static final String DIRECTORY = "src/main/resources/NACTDB" ;
    private static Dataset db;
    public static Dataset getConnection() {
        db = TDBFactory.createDataset(DIRECTORY);
        return db;
    }
    public static void closeDB(){
        if(db != null){
            db.close();
        }
    }
}
