package com.phvob;

import com.phvob.dao.AccessDao;
import com.phvob.dao.NetWorkDao;
import com.phvob.daoimpl.AccessDaoImpl;
import com.phvob.dao.HostDao;
import com.phvob.daoimpl.HostDaoImpl;
import com.phvob.daoimpl.NetWorkDaoImpl;
import com.phvob.util.TDBConnection;
import com.phvob.util.OwlToTdb;
import org.apache.jena.query.*;
import org.junit.jupiter.api.Test;

/**
 * @author phvob
 * @email hbuphvob@163.com
 * @create 2021-04-22 15:25
 */

public class TestKlass {
    @Test
    public void TestMain(){

    }
    //owl生成TDB数据库
    @Test
    public void create() {
        OwlToTdb.transform();
        System.out.println("transform OK!!!");
    }

    //连接TDB数据库
    @Test
    public void queryTDB() {
        Dataset dataset = TDBConnection.getConnection();
        dataset.begin(ReadWrite.READ);
        try {
            String spk = "PREFIX owl: <http://www.w3.org/2002/07/owl#>" +
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                    "PREFIX xml: <http://www.w3.org/XML/1998/namespace>" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                    "PREFIX source: <http://www.semanticweb.org/cuibo/ontologies/2021/3/untitled-ontology-9#>" +
                    "select ?sub ?obj ?access ?accesscontrol" +
                    "where{" +
                    "?sub rdf:type source:Host." +
                    "?obj rdf:type source:Host." +
                    "?accesscontrol rdf:type source:AccessControl." +
                    "?access rdf:type source:Access." +
                    "  ?access source:hasSubject ?sub." +
                    "  ?access source:hasObject ?obj." +
                    "  ?access source:hasAccessControl ?accesscontrol." +
                    "}";
            QueryExecution qExec = QueryExecutionFactory.create(spk, dataset);
            ResultSet rs = qExec.execSelect();
            //ResultSetFormatter.out(rs) ;

            while(rs.hasNext()){
                QuerySolution qs = rs.next();
                String res = qs.get("?sub").toString();
                res = res.substring(res.indexOf("#")+1);
                System.out.println(res);

            }
        } finally {
            dataset.end();
        }
    }
    @Test
    public void queryAllHostTest(){
        HostDao hostDao = new HostDaoImpl();
        System.out.println(hostDao.queryAllHost());
    }
    @Test
    public void queryAllAccessTest(){
        AccessDao accessDao = new AccessDaoImpl();
        System.out.println(accessDao.queryAllHostAccess());
    }
    @Test
    public void queryAllNetWorkTest(){
        NetWorkDao netWorkDao = new NetWorkDaoImpl();
        System.out.println(netWorkDao.queryAllNetWork());
    }
    @Test
    public void queryHostByNetWork(){
        NetWorkDao netWorkDao = new NetWorkDaoImpl();
        System.out.println(netWorkDao.queryHostByNetWork(""));
    }
    @Test
    public void queryAccessByHost(){
        AccessDao accessDao = new AccessDaoImpl();
        System.out.println(accessDao.queryAccessByHost("10.254.1.20"));
    }

}
