package com.phvob.daoimpl;

import com.phvob.dao.NetWorkDao;
import com.phvob.util.TDBConnection;
import org.apache.jena.query.*;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @author phvob
 * @email hbuphvob@163.com
 * @create 2021-04-26 18:37
 */

public class NetWorkDaoImpl implements NetWorkDao {
    @Override
    public List<String> queryAllNetWork() {
        List<String> networks = new ArrayList<String>();
        Dataset dataset = TDBConnection.getConnection();
        dataset.begin(ReadWrite.READ);
        try {
            String spk = "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX xml: <http://www.w3.org/XML/1998/namespace>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX source: <http://www.semanticweb.org/cuibo/ontologies/2021/3/untitled-ontology-9#>\n" +
                    "select  ?network\n" +
                    "where{\n" +
                    "  \t?network  rdf:type source:NetWork.\n" +
                    "}";
            QueryExecution qExec = QueryExecutionFactory.create(spk, dataset);
            ResultSet rs = qExec.execSelect();
            //ResultSetFormatter.out(rs) ;
            while (rs.hasNext()) {
                QuerySolution qs = rs.next();
                String res = qs.get("?network").toString();
                res = res.substring(res.indexOf("#")+1);
                networks.add(res);
            }
        } finally {
            dataset.end();
        }
        return networks;
    }
    @Override
    public List<String> queryHostByNetWork(String network) {
        List<String> hosts = new ArrayList<String>();
        Dataset dataset = TDBConnection.getConnection();
        dataset.begin(ReadWrite.READ);
        try {
            String spk = "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                    "PREFIX xml: <http://www.w3.org/XML/1998/namespace>\n" +
                    "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                    "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                    "PREFIX source: <http://www.semanticweb.org/cuibo/ontologies/2021/3/untitled-ontology-9#>\n" +
                    "select  ?host\n" +
                    "where{\n" +
                    "  \t?host  rdf:type source:Host.\n" +
                    "  \tsource:"+network.replace("/","\\/")+" source:hasHost ?host.\n" +
                    "}";
            QueryExecution qExec = QueryExecutionFactory.create(spk, dataset);
            ResultSet rs = qExec.execSelect();
            //ResultSetFormatter.out(rs) ;
            while (rs.hasNext()) {
                QuerySolution qs = rs.next();
                String res = qs.get("?host").toString();
                res = res.substring(res.indexOf("#")+1);
                hosts.add(res);
            }
        } finally {
            dataset.end();
        }
        return hosts;
    }
}
